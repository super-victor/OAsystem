!<!-- ChildrenComponent1 -->
<template>
  <div class='CreateSchedule'>
    <div class="center flex-col">
      <p class="title">新建日程</p>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="日程类型" prop="type" style="height:60px;width:500px">
          <el-select v-model="form.type">
            <el-option
            v-for="item in types"
            :key="item.name"
            :label="item.name"
            :value="item.name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日程内容" prop="content" style="height:60px;width:500px">
          <el-input v-model="form.content" class="input"></el-input>
        </el-form-item>
        <el-form-item label="日程地点" prop="location" style="height:60px;width:500px">
          <el-input v-model="form.location" class="input"></el-input>
        </el-form-item>
        <el-form-item label="参与人员" prop="joiner" style="height:60px" v-if="form.type==='公司日程'">
          <el-select v-model="form.joiner" multiple>
            <el-option
            v-for="item in userList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime" style="height:60px;width:500px">
          <el-date-picker
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime" style="height:60px;width:500px">
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="button" @click="submitForm('form')">提交信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {mapMutations,mapState} from 'vuex';
import ScheduleApi from '@/service/schedule';
import BackApi from '@/service/BackstageManagement'
  export default {
    components: {},
    data() {
      return {
        form:{
          type:'个人日程'
        },// 表单
        types:[{
          name:'个人日程'
        },{
          name:'公司日程'
        }],
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
          endTime:[
            { required: true, message: '请输入结束时间', trigger: 'blur' }
          ]
        },
        userList:[], //职工信息表
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
      submitForm(formName) {
        console.log(this.form.joiner);
        this.$refs[formName].validate((valid) => {
          if(valid){
            if (this.form.type==='个人日程') {
              ScheduleApi.addSelfSchedule({
                content:this.form.content,
                startTime:this.form.startTime,
                endTime:this.form.endTime,
                location:this.form.location,
                joiner:this.form.joiner,
                leader:this.userInfo.emploeeId,
              })
              .then(res=>{
                this.form = {};
                this.$alert('添加成功', '通知', {
                  confirmButtonText: '确定',
                });
                this.$router.push({path:'/schedule/mine'});
                console.log(res);
              })
              .catch(err=>{
                console.log(err);
              })
            } else {
              ScheduleApi.addCompanySchedule({
                content:this.form.content,
                startTime:this.form.startTime,
                endTime:this.form.endTime,
                location:this.form.location
              })
              .then(res=>{
                this.form = {};
                this.$alert('添加成功', '通知', {
                  confirmButtonText: '确定',
                });
                this.$router.push({path:'/schedule/company-schedule'});
                console.log(res);
              })
              .catch(err=>{
                console.log(err);
              })
            }
          }
        })
      },
      getUser() {
        BackApi.getUserList()
        .then(res=>{
          res.object.forEach(element => {
            this.userList.push({name:element.username,id:element.employeeId});
          });
          console.log(res);
        })
        .catch(err=>{
          console.log(err);
        })
      }
    },
    created() {
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
    height: 500px;
    width: 60%;
    font-size: 0.2rem;
    color: @regularText;
    .center {
      padding: 50px;
      border-radius: @baseBorderRadius;
      background-color: @white;
      p.title {
        color: @primaryText;
        font-weight: bold;
        font-size: 0.25rem;
        margin-bottom: 0.3rem;
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