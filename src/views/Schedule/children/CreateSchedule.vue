!<!-- ChildrenComponent1 -->
<template>
  <div class='CreateSchedule'>
    <div class="center flex-col">
      <p class="title">新建日程</p>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="日程类型" prop="type" style="height:70px;width:400px">
          <el-select v-model="form.type" size="medium" style="height:70px;width:400px">
            <el-option label="个人日程" value="个人日程" v-show="selfFlag"></el-option>
            <el-option label="公司日程" value="公司日程" v-show="companyFlag"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日程内容" prop="content" style="height:70px;width:400px">
          <el-input v-model="form.content" class="input" size="medium" style="width:400px"></el-input>
        </el-form-item>
        <el-form-item label="日程地点" prop="location" style="height:70px;width:400px">
          <el-input v-model="form.location" class="input" size="medium" style="width:400px"></el-input>
        </el-form-item>
        <el-form-item label="参与人员" prop="joiner" style="margin-bottom:20px;height:50px" v-if="form.type==='公司日程'">
          <el-select v-model="form.joiner" multiple filterable style="width:400px" size="medium">
            <el-option
            v-for="item in userList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <div class="flex flex-row">
          <el-form-item label="开始时间" prop="startTime" style="height:70px;width:500px">
            <el-date-picker
              v-model="form.startTime"
              type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择开始时间"
              size="medium"
              style="width:200px">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endTime" style="height:70px;width:300px">
            <el-date-picker
              v-model="form.endTime"
              type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择结束时间"
              size="medium"
              style="width:200px">
            </el-date-picker>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button type="primary" class="button" @click="submitForm('form')">提交信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {mapState,mapMutations} from 'vuex';
import ScheduleApi from '@/service/schedule';
import BackApi from '@/service/BackstageManagement'
  export default {
    components: {},
    data() {
      return {
        form:{
          type:'个人日程'
        },// 表单
        rules:{
          type:[
            { required: true, message: '请选择日程类型', trigger: 'blur' }
          ],
          content:[
            { required: true, message: '请输入日程内容', trigger: 'blur' }
          ],
          location:[
            { required: true, message: '请输入日程地点', trigger: 'blur' }
          ],
          startTime:[
            { required: true, message: '请输入开始时间', trigger: 'blur' }
          ],
          joiner:[
            { required: true, message: '请选择参与人员', trigger: 'blur' }
          ],
          endTime:[
            { required: true, message: '请输入结束时间', trigger: 'blur' }
          ]
        },
        userList:[], //职工信息表
        leader:0,
        companyFlag:false,
        selfFlag:false,
      };
    },
    computed: {
      ...mapState([
        'userInfo'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD','MAIN_CLICK']),
      //提交表单
      submitForm(formName) {
        console.log(this.form.joiner);
        this.$refs[formName].validate((valid) => {
          if(valid){
            if (this.form.type==='个人日程') { // 新建个人日程
              ScheduleApi.addSelfSchedule({
                content:this.form.content,
                startTime:this.form.startTime,
                endTime:this.form.endTime,
                location:this.form.location,
              })
              .then(res=>{
                this.form = {};
                this.$message.success('添加成功');
                this.$router.push({path:'/schedule/mine'}); // 跳转个人日程页面
                console.log(res);
              })
              .catch(err=>{
                console.log(err);
                this.$message.error('添加失败');
              })
            } else { // 添加公司日程
              ScheduleApi.addCompanySchedule({
                content:this.form.content,
                startTime:this.form.startTime,
                leader:this.leader,
                joiner:this.form.joiner,
                endTime:this.form.endTime,
                location:this.form.location
              })
              .then(res=>{
                this.form = {};
                this.$message.success('添加成功');
                this.$router.push({path:'/schedule/company-schedule'}); // 跳转公司日程页面
                console.log(res);
              })
              .catch(err=>{
                console.log(err);
                this.$message.error('添加失败');
              })
            }
          }
        })
      },
      // 获取所有职工
      getUser() {
        BackApi.getUserList()
        .then(res=>{
          res.object.forEach(element => {
            this.userList.push({name:element.username,id:element.employeeId});
          });
          // console.log(res);
        })
        .catch(err=>{
          console.log(err);
        })
      }
    },
    created() {
      let role = this.$authority.getPageAuthority('schedule','createschedule').role;
      if (role['0011'].own) this.selfFlag = true;
      if (role['000X'].own) this.companyFlag = true;
      if (!(this.selfFlag || this.companyFlag)) {
        this.$message.error('无权限添加日程');
      }
      this.leader = this.userInfo.userinfo.employeeId;
      this.getUser();
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','新建日常']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .CreateSchedule{
    width: 100%;
    font-size: 0.2rem;
    height: 100%;
    color: @regularText;
    border-radius: @baseBorderRadius;
    background-color: @white;
    .center {
      padding: 50px;
      p.title {
        color: @primaryText;
        font-weight: bold;
        font-size: 0.25rem;
        margin-bottom: 0.7rem;
      }
      .button{
        margin-top: 30px;
        height: 40px;
        width: 100px;
      }
      ::v-deep .input {
        width: 5rem;
      }
    }
  }
</style>