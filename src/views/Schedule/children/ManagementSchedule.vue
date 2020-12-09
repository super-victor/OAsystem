!<!-- ChildrenComponent1 -->
<template>
  <div class='managementSchedule'>
    <div class="table">
      <el-table
          ref="Table"
          :data="tableData"
          style="width: 100%"
          stripe>
          <el-table-column
          prop="startTime"
          label="开始时间"
          width="180">
          </el-table-column>
          <el-table-column
          prop="endTime"
          label="结束时间"
          width="180">
          </el-table-column>
          <el-table-column
          prop="location"
          label="地点"
          width="150">
          </el-table-column>
          <el-table-column
          prop="leader"
          label="领导人员"
          width="120">
          </el-table-column>
          <el-table-column
          prop="joiner"
          label="参与人员"
          width="220">
          </el-table-column>
          <el-table-column
          prop="content"
          label="内容">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="Edit(scope.row)" type="text" size="small">编辑</el-button>
              <el-button type="text" size="small" @click="deleteSchedule(scope.row)">删除</el-button>
            </template>
          </el-table-column>
      </el-table>
    </div>
    <el-dialog title="日程信息" :visible.sync="dialogVisible" class="dialog">
      <el-form ref="form" :model="info" :rules="rules" label-width="80px">
        <el-form-item label="日程内容" prop="content" class="row">
          <el-input v-model="info.content" placeholder="请输入日程内容" class="input"></el-input>
        </el-form-item>
        <el-form-item label="日程地点" prop="location" class="row">
          <el-input v-model="info.location" placeholder="请输入日程地点" class="input"></el-input>
        </el-form-item>
        <el-form-item label="领导人" prop="leader" class="row">
          <el-input v-model="info.leader" :disabled="true" class="input"></el-input>
        </el-form-item>
        <el-form-item label="参与人" prop="location" class="row">
          <el-input v-model="info.joiner" :disabled="true" class="input"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime" class="row">
          <el-date-picker
            v-model="info.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime" class="row">
          <el-date-picker
            v-model="info.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSchedule()" class="button">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import ScheduleApi from '@/service/schedule';
  export default {
    components: {
    },
    data() {
      return {
        tableData:[],
        dialogVisible:false,
        info:{},
        rules:{
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
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      // 获取公司日程
      getSchedule() {
        ScheduleApi.getCompanySchedule()
        .then(res=>{
          console.log(res);
          this.tableData = res.object
          this.tableData.forEach(element => {
            element.joiner = element.joiner.join('，');
          });
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('获取失败');
        })
      },
      // 编辑公司日程
      Edit(row) {
        this.dialogVisible=true;
        this.info = row;
      },
      handleSchedule(row) {
        ScheduleApi.updateCompanySchedule({
          content:this.info.content,
          startTime:this.info.startTime,
          endTime:this.info.endTime,
          location:this.info.location,
          scheduleId:this.info.scheduleId
        })
        .then(res=>{
          this.dialogVisible=false;
          // this.$router.go(0);
          this.$message.success('修改成功');
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('修改失败');
        })
      },
      // 删除公司日程
      deleteSchedule(row) {
        ScheduleApi.deleteCompanySchedule({
          scheduleId:row.scheduleId
        })
        .then(res=>{
          console.log(res);
        })
        .catch(err=>{
          this.$message.error('删除失败');
          console.log(err);
        })
      }
    },
    created() {
      this.getSchedule();
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','管理日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .managementSchedule{
    font-size: 0.2rem;
    width: 100%;
    height: 100%;
    overflow-y: scroll;
    background-color: @white;
    border: @baseBorderRadius;
    .table {
      padding: 20px;
    }
  .dialog .el-dialog__body{
    .row {
      margin-bottom: 10px;
    }
  }
  .dialog .el-dialog__footer{
    button {
      padding: 10px;
    }
  }
  }
  .managementSchedule::-webkit-scrollbar{
    display: none;
  }
</style>