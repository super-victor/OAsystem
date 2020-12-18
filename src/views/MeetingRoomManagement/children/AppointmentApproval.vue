<template>
  <div class='AppointmentApproval'>      
    <div class="msgBox" v-if="getFlag1">
    
      <div class="title">
        <p class='ptitle'>待审批会议</p>
      </div>

      <div class="table">
         <el-table
          :data="tableData" 
          max-height="550"
          v-loading='loading'
          >
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="会议室名">
                  <span>{{ props.row.meetingRoomName }}</span>
                </el-form-item>
                <el-form-item label="开始时间">
                  <span>{{ props.row.startTime.substr(0,10)+' '+props.row.startTime.substr(11,8) }}</span>
                </el-form-item>
                <el-form-item label="会议名称">
                  <span>{{ props.row.name }}</span>
                </el-form-item>
                <el-form-item label="结束时间">
                  <span>{{ props.row.endTime.substr(0,10)+' '+props.row.endTime.substr(11,8) }}</span>
                </el-form-item>
                <el-form-item label="发起人" v-if="getFlag2">
                  <span>{{employeeData.filter((item)=>{return item.employeeId == props.row.employeeId?'item.name':'';})[0].name}}</span>
                </el-form-item>
                <el-form-item label="参会人数">
                  <span>{{ props.row.peopleNum }}</span>
                </el-form-item>
                <el-form-item label="备注" style="margin-right:100%">
                  <span>{{ props.row.remark }}</span>
                </el-form-item>
                <div class="button">
                  <el-button type="danger" @click="disagreed(props.row)" v-show="getFlag3">驳回</el-button>
                  <el-button type="primary" @click="agreed(props.row)" v-show="getFlag3">同意</el-button>
                </div>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="会议室名"
            prop="meetingRoomName"
            width="300">
          </el-table-column>
          <el-table-column
            label="会议名称"
            prop="name"
            width="400">
          </el-table-column>
          <el-table-column
            label="预约日期"
            width="500"
            >
            <template slot-scope="scope">{{scope.row.startTime.substr(0,10)}}</template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import getMeetingAPI from '@/service/MeetingRoomManagement';
import SubMeetingAPI from '@/service/MeetingRoomManagement';
 import {

    mapMutations
  } from 'vuex'
export default {
    data(){
      return{
         tableData: [],
         employeeData:[],
         accept:0,
         meetingid:0,
         loading:true,
         getFlag1:false,
        getFlag2:false,
        getFlag3:false,
      }
    },
    created(){
       let role = this.$authority.getPageAuthority('meetingroommanagement','appointmentapproval').role;
      if(role['0024'].own) this.getFlag1 = true; //获取未被审批的会议
      if(role['0033'].own) this.getFlag2 = true; //获取所有员工通讯录
      if(role['0023'].own) this.getFlag3 = true; //审批会议
      if(this.getFlag1){
        this.getMeetings();
      }
    },
    watch:{
      accept(val){
                 this.loading = true;
        SubMeetingAPI.approveMeeting({
          appoinmentstatus:val,
          meetingid:this.meetingid
        }).then(getMeetingAPI.getNotApprovedMeeting()
      .then(res=>{
        
        this.tableData = res.object;
        this.$message({
            type: 'success',
            message: '操作成功!'
          });
      }).catch(err=>{
        this.$message.error('获取失败!')
      }),)
        .catch(err=>{
          this.$message.error('操作失败')
        })
     }
    },
    methods:{
       ...mapMutations(['UPDATE_BREAD']),
       //获取数据
       getMeetings(){
          getMeetingAPI.getNotApprovedMeeting()
      .then(res=>{
        this.tableData = res.object;
        this.loading=false;
      }).catch(err=>{
        if(err == "Error: 网络请求错误:没有待审批的会议") this.loading = false
        else this.$message.error('获取失败!')
      }),
      getMeetingAPI.AddressbookInfo()
      .then(res=>{
        this.employeeData = res.object;
      }).catch(err=>{
      })
       },
       //驳回
       disagreed(data){
          this.$confirm('是否要驳回此会议预约?  一旦确定无法撤回', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.accept = -1;

          this.meetingid =data.meetingId;
          this.getMeetings()
        }).catch(() => {
             
        });
       },
       //同意
       agreed(data){
         this.accept = 1;
         this.meetingid =data.meetingId;
        this.getMeetings();
        this.loading=false;
       }
    },
    computed:{
     
    },
    mounted() {
      this.UPDATE_BREAD(['会议室管理', '预约审批'])
    }
}
</script>

<style lang='less' scoped>
 @import '../../../style/common.less';

  .AppointmentApproval {
    height: 100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;

    .msgBox {
      height: 100%;
      width: 100%;
      .title{
        .ptitle{
          font-size:20px;
          color:black;
          margin-bottom: 10px;
        }
      }
      .table{
          width:100%;
          height: 100%;
          margin:0 auto;
          .el-table{
            height:100%;
              box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
              border-radius: @baseBorderRadius;
          }
          .el-table  ::-webkit-scrollbar{
        display: none;
      }
          .button {
            margin:auto;
            text-align: center;
            .el-button{   
              margin-right: 50px;
              width: 80px;
              height: 40px;
            }
          }
          .demo-table-expand {
            font-size: 0;
          }
          .demo-table-expand label {
            width: 90px;
            color: #99a9bf;
          }
          .demo-table-expand .el-form-item {
            margin-right: 0;
            margin-bottom: 0;
            width: 50%;
          }
      }
    }
  }

  
</style>