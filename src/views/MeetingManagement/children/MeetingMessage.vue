<template>
  <div class='MeetingMessage'>      
    <div class="msgBox">
        <div class="disPlay">
            <!-- 待进行会议 -->
            <div class="pending" >
                <div class="btn">
                  <el-button @focus="">待进行会议</el-button>
                 
                </div>
                <div class="CollapsePending" >
                  <el-collapse accordion @change="handleChange" >
                  <el-collapse-item v-for="i in CollapsePendingList.length" :key="i" style="padding-left:30px">
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
            <div class="history">
                <div class="title">
                  <p>历史会议<el-button style="float: right;" type="text" @click='CollapseRecordList=[]'>清空</el-button></p>
                  
                </div>
                <div class="CollapseRecord">
                  <el-collapse accordion @change="handleChange" >
                  <el-collapse-item v-for="i in CollapseRecordList.length" :key="i" style="padding-left:30px">
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
        <div class="Appointment">
          <el-card class="box-card">
            <div class="rightTitle">
              待审批会议
            </div>
            <div class="content">
               <el-collapse accordion @change="handleChange">
                  <el-collapse-item v-for="i in CollapseAppointmentList.length" :key="i" style="padding-left:30px">
                    <template slot="title">
                        {{CollapseAppointmentList[i-1]?CollapseAppointmentList[i-1].name:''}}
                    </template>
                    <div>{{"会议室："+CollapseAppointmentList[i-1].meetingRoomName}}</div>
                    <div>{{"开始时间："+CollapseAppointmentList[i-1].startTime.substr(0,10)+' '+CollapseAppointmentList[i-1].startTime.substr(11,8)}}</div>
                    <div>{{"结束时间："+CollapseAppointmentList[i-1].endTime.substr(0,10)+' '+CollapseAppointmentList[i-1].endTime.substr(11,8)}}</div>
                    <div>{{"参会人数："+CollapseAppointmentList[i-1].peopleNum}}</div>
                    <el-button style="float: right; padding: 3px 5px" type="text">取消预约</el-button>
                    <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
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
  import getMeetingAPI from '@/service/MeetingRoomManagement'
export default {
    data(){
      return {
        message:'',
        exchange:1,
        activeNames:'',
        CollapseList:[],
        CollapsePendingList:[], //待进行会议
        CollapseAppointmentList:[], //待审批会议
        CollapseRecordList:[], //已完成的会议（历史会议）
      }
    },
    created(){
      this.getAllMeeting()
    },
    methods:{
      ...mapMutations(['UPDATE_BREAD']),
      //请求所有会议信息
      getAllMeeting(){
        getMeetingAPI.getAllMeetings()
        .then(res=>{
          console.log(res.object)
          this.CollapseList = res.object.filter(item=>{
            return this.userInfo.userinfo.employeeId == item.employeeId?item:''
          })
          this.CollapsePendingList = res.object.filter(item=>{
            return item.appoinmentStatus == 1?item:''
          })
          this.CollapseAppointmentList = res.object.filter(item=>{
            return item.appoinmentStatus == 0?item:''
          })
          this.CollapseRecordList = res.object.filter(item=>{
            return item.appoinmentStatus == 2?item:''
          })
        }).catch(err=>{
          this.$message.error('请求失败')
          console.log("getAllMeetings",err)
        })
      },
      handleChange(val){
        console.log(val)
      }
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
            height: 100%;
            width: 100%;
            display: flex;
            justify-content: space-between;
            .disPlay{
                width: 68%;
                height: 100%;
                .pending{
                    height:61%;
                    box-shadow: 0 0 20px 10px rgba(82, 63, 105, 0.05);
                    border-radius: @baseBorderRadius;
                    .btn{
                      height:10%;
                      display:flex;
                      ::v-deep .el-button{
                        display: block;
                        width:150px;
                        height:100%;
                        color: #3D60AD;
                        margin:0px;
                        border-color:rgba(82, 63, 105, 0.05);
                        border-bottom:none;
                        border-radius: @baseBorderRadius;
                      }
                    }
                    .CollapsePending{
                      height:90%;
                      max-height:100%;
                      overflow: auto;
                      
                      .el-collapse{
                      
                    border-radius: @baseBorderRadius;
                        border-top:none;
                        height:100%;
                        background:white; 
                      }
                    }
                    
                }
                .history{
                    margin-top:2%;
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
                    .CollapseRecord{
                      max-height: 70%;
                      overflow: auto;
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
                      .rightTitle{
                        width:100%;
                        font-size:20px;
                        padding-bottom:10px;
                        color: #3D60AD;
                        //  align-self: flex-end;
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