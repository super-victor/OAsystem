<template>
  <div class='MeetingMessage'> 
          <el-dialog title="待审批会议" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          
          <el-form-item label="会议名称：" >
            <p>{{form.name}}</p>
          </el-form-item>
          <el-form-item label="开始时间：" >
            <p>{{form.startTime}}</p>
          </el-form-item>
          <el-form-item label="结束时间：" >
            <p>{{form.endTime}}</p>
          </el-form-item>
          <el-form-item label="参会人数：" label-width="">
            <el-input v-model="form.peopleNum" autocomplete="off" style="width:10%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>     
    <div class="msgBox" v-if="getFlag">
        <div class="disPlay">
            <!-- 待进行会议 -->
            <div class="pending" v-loading ="loading">
                 <div class="title">
                   <p>待进行会议
                  </p>
                 </div>
                <div class="CollapsePending" >
                  <el-collapse accordion v-model="activeNames1">
                  <el-collapse-item v-for="i in CollapsePendingList.length" :key="i" style="padding-left:30px" :name = i>
                    <template slot="title">
                        {{CollapsePendingList[i-1]?CollapsePendingList[i-1].startTime.substr(0,10)+'\xa0\xa0\xa0\xa0\xa0\xa0\xa0'+CollapsePendingList[i-1].name:''}}
                    </template>
                    <div>{{"会议室："+CollapsePendingList[i-1].meetingRoomName}}</div>
                    <div>{{"开始时间："+CollapsePendingList[i-1].startTime.substr(0,10)+' '+CollapsePendingList[i-1].startTime.substr(11,8)}}</div>
                    <div>{{"结束时间："+CollapsePendingList[i-1].endTime.substr(0,10)+' '+CollapsePendingList[i-1].endTime.substr(11,8)}}</div>
                    <div>{{"参会人数："+CollapsePendingList[i-1].peopleNum}}</div>
                  </el-collapse-item>
                </el-collapse>
                </div>
            </div>
            <!-- 历史会议 -->
            <div class="history" v-loading ="loading">
                <div class="title">
                  <p>历史会议
                  </p>
                </div>
                <div class="CollapseRecord">
                  <el-collapse accordion v-model="activeNames2">
                  <el-collapse-item v-for="i in CollapseRecordList.length" :key="i" style="padding-left:30px" :name = i>
                    <template slot="title">
                        {{CollapseRecordList[i-1]?CollapseRecordList[i-1].startTime.substr(0,10)+'\xa0\xa0\xa0\xa0\xa0\xa0\xa0'+CollapseRecordList[i-1].name:''}}
                    </template>
                    <div>{{"会议室："+CollapseRecordList[i-1].meetingRoomName}}</div>
                    <div>{{"开始时间："+CollapseRecordList[i-1].startTime.substr(0,10)+' '+CollapseRecordList[i-1].startTime.substr(11,8)}}</div>
                    <div>{{"结束时间："+CollapseRecordList[i-1].endTime.substr(0,10)+' '+CollapseRecordList[i-1].endTime.substr(11,8)}}</div>
                    <div>{{"参会人数："+CollapseRecordList[i-1].peopleNum}}</div>
                  </el-collapse-item>
                </el-collapse>
                </div>
            </div>
        </div>
        <!-- 待审批会议 -->
        <div class="Appointment" v-loading ="loading">
          <el-card class="box-card">
            <div class="rightTitle">
              待审批会议
            </div>
            <div class="content">
               <el-collapse accordion  v-model="activeNames3">
                  <el-collapse-item v-for="i in CollapseAppointmentList.length" :key="i" style="padding-left:30px" :name = i>
                    <template slot="title">
                        {{CollapseAppointmentList[i-1]?CollapseAppointmentList[i-1].name:''}}
                    </template>
                    <div>{{"会议室："+CollapseAppointmentList[i-1].meetingRoomName}}</div>
                    <div>{{"开始时间："+CollapseAppointmentList[i-1].startTime.substr(0,10)+' '+CollapseAppointmentList[i-1].startTime.substr(11,8)}}</div>
                    <div>{{"结束时间："+CollapseAppointmentList[i-1].endTime.substr(0,10)+' '+CollapseAppointmentList[i-1].endTime.substr(11,8)}}</div>
                    <div>{{"参会人数："+CollapseAppointmentList[i-1].peopleNum}}</div>
                    <el-button style="float: right; padding: 3px 5px" type="text" @click="deleteorderMeeting(i-1)" v-show="deleteFlag">取消预约</el-button>
                  </el-collapse-item>
                </el-collapse>
            </div>
          </el-card>
        </div>
    </div>
  </div>
</template>

<script>
 import {
   mapState,
    mapMutations
  } from 'vuex';
  import getMeetingAPI from '@/service/MeetingManagement'
  import ApproveMeetingAPI from '@/service/MeetingManagement'
export default {
    data(){
      return {
        getFlag:false,
        deleteFlag:false,
        message:'',
        exchange:1,
        loading:true,
        form:[],
        dialogFormVisible:false,
        CollapseList:[],
        activeNames1:[1],
        activeNames2:[],
        activeNames3:[1],
        CollapsePendingList:[], //待进行会议
        CollapseAppointmentList:[], //待审批会议
        CollapseRecordList:[], //已完成的会议（历史会议）
      }
    },
    created(){
      let role = this.$authority.getPageAuthority('meetingmanagement').role;
      if(role['0020'].own) this.getFlag = true; //获取所有的会议
      if(role['0027'].own) this.deleteFlag = true; //删除未审批的预约会议
      this.getAllMeeting();
      
      
    },
    methods:{
      ...mapMutations(['UPDATE_BREAD']),
      //请求所有会议信息
      getAllMeeting(){
        getMeetingAPI.getAllMeetings()
        .then(res=>{
          this.loading = false;
          this.CollapseList = res.object.filter(item=>{
            return this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapsePendingList = res.object.filter(item=>{
            return item.appoinmentStatus == 1 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapseAppointmentList = res.object.filter(item=>{
            return item.appoinmentStatus == 0 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapseRecordList = res.object.filter(item=>{
            return item.appoinmentStatus == 2 && this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
        }).catch(err=>{
          this.$message.error('请求失败')
        })
      },
      //删除还未审批的预约会议
      deleteorderMeeting(data){
        this.$confirm('此操作将取消你该会议的预约, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true,
          this.form = this.CollapseAppointmentList[data]
        ApproveMeetingAPI.deleteOrderMeeting({
          meetingid: this.form.meetingId
        }).then(res=>{
          
          this.getAllMeeting()
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        }).catch(err=>{
          this.$message.error('删除失败！')
        })
        }).catch(() => {
              
        });
        
      },
    },
     computed: {
      ...mapState([
        'userInfo'
      ])
    },
   mounted() {
      this.UPDATE_BREAD(['会议管理', '会议信息'])
    }   
}
</script>

<style lang='less' scoped>
 @import '../../../style/common.less';
.MeetingMessage {
    height: 100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
        .msgBox {
            height: 575px;
            width: 100%;
            display: flex;
            justify-content: space-between;
            .disPlay{
                width: 68%;
                height: 100%;
                .pending{
                    height:59%;
                    max-height:59%;
                    background-color: #fff;
                    box-shadow: 0 0 20px 10px rgba(82, 63, 105, 0.05);
                    border-radius: @baseBorderRadius;
                    .title{
                      margin-left:20px;

                      p{
                        font-size:20px;
                        line-height:55px;
                        color: #3D60AD;
                        .el-button{
                          margin-top:20px;
                          padding-right:15px;
                        }
                      }
                    }
                    
                    .CollapsePending{
                      height:90%;
                      max-height:83%;
                      overflow: auto;
                      
                      .el-collapse{
                      
                    border-radius: @baseBorderRadius;
                        height:100%;
                        background:white; 
                      }
                    }
                    .CollapsePending::-webkit-scrollbar {
            display: none;
        }
                    
                }
                .history{
                    margin-top:3%;
                    height:36.8%;
                    box-shadow: 0 0 20px 10px rgba(82, 63, 105, 0.05);
                    border-radius: @baseBorderRadius;
                    background: white;
                    .title{
                      margin-left:20px;
                      height:23%;
                      p{
                        font-size:20px;
                        line-height:55px;
                        color: #3D60AD;
                        .el-button{
                          margin-top:20px;
                          padding-right:15px;
                        }
                      }
                    }
       
                    .CollapseRecord {
                      max-height: 76%;
                      overflow: auto;
                      
                    }
                    .CollapseRecord::-webkit-scrollbar {
            display: none;
        }
                }
            }   
            .Appointment{
                width:30%;
                box-shadow: 0 0 10px 0px rgba(82, 63, 105, 0.05);
                border-radius: @baseBorderRadius;
                .box-card{
                  height:100%;
                  width:100%;
                   max-height:100%;
                overflow: auto;
                      .rightTitle{
                        width:100%;
                        font-size:20px;
                        padding-bottom:10px;
                        color: #3D60AD;
                        .el-button{
                          display:block;
                          margin-left:90%;
                        }
                  }
                  .content{
                    width:100%;
                    p{
                      font-size:20px;
                    }
                  }
                 
                }
            }
        }
}
</style>