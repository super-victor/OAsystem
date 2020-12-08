<template>
  <div class='room'>
    
    <div class="alert">
      
      <!-- 弹框 -->
      <el-dialog title="会议预约" :visible.sync="dialogFormVisible">
        <el-form :model="form" style="display:flex;flex-wrap: wrap;">
          <el-form-item label="职工id" :label-width="formLabelWidth" style="width:30%">
            <el-input v-model="this.userInfo.userinfo.employeeId" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="当前会议室号" :label-width="formLabelWidth" style="width:30%;padding-right:40%">
            <el-input v-model="this.currentRow.meetingRoomId" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="会议名称" :label-width="formLabelWidth" style="width:30%;padding-right:50%">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="参会人数" :label-width="formLabelWidth" style="width:100%">
            <el-input-number v-model="form.num" size="small" :min="1" :max="10" label="描述文字" controls-position="right"
              style="width:90px"></el-input-number>
          </el-form-item>
          <el-form-item label="选择日期" :label-width="formLabelWidth" style="width:30%;padding-right:50%">
            <el-date-picker
              v-model="dateSelect"
              type="date"
              placeholder="选择日期" >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="今日预约" :label-width="formLabelWidth" style="width:100%" v-if="dateSelect" >
            <el-button-group >
              <el-button :class="(i == getSelect.filter(item=>{if(item == i) return item}))?'red':(((startTime <= i && i<= endTime)||startTime == i)?'blue':'white')" 
              v-for="i in timeList"  :key="i" @click="getIndex(i)"
              :disabled ="(i == getSelect.filter(item=>{if(item == i) return item}))?true:false"
                 style='color:black' >{{i}}</el-button>
            </el-button-group>
            <div class="colorRemark">
              <div class="remarks">
                <div class="white"></div>
              <div >可预约</div>
              <div class="red" ></div>
              <div >已预约</div>
              </div>
              <div class="btn">
                <el-button size="small" style="width:60px" @click="reselect">重选</el-button>
              </div>
            </div>
          </el-form-item>
          <el-form-item label="备注" :label-width="formLabelWidth" style="width:80%">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="meetingComfirm()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div class="msgBox">
      <!-- 会议室筛选 -->


      
      <!-- 左侧树 -->
      <div class="content">
        <div class="tree">
          <div class="title">
            <span>
              楼层信息
            </span>
          </div>
          <div class="elTree">
            <el-tree :data="storeyData" :props="defaultProps" accordion @node-click="handleNodeClick">
            </el-tree>
          </div>
        </div>

        <!-- 表格 -->
        <div class="table" :style="!RoomMessage?'width:80%':'width:50%'">
          <template>
            <el-table :data="tableFilterData" stripe border height="550"
              highlight-current-row @current-change="handleCurrentChange">
              <el-table-column prop="name" label="会议室号" >
              </el-table-column>
              <el-table-column  label="状态">
                <template slot-scope="scope">
                  {{scope.row.meeting?'会议中':'空闲'}}
                </template>
              </el-table-column>  
              <el-table-column prop="meeting.name" label="会议名称" v-if="!RoomMessage">
              </el-table-column>
              <el-table-column  label="开始时间" v-if="!RoomMessage">
                <template slot-scope="scope">
                  {{scope.row.meeting?scope.row.meeting.startTime.substr(0,10)+' '+scope.row.meeting.startTime.substr(11,8):''}}
                </template>
              </el-table-column>
              <el-table-column  label="结束时间" v-if="!RoomMessage">
                <template slot-scope="scope">
                  {{scope.row.meeting?scope.row.meeting.endTime.substr(0,10)+' '+scope.row.meeting.endTime.substr(11,8):''}}
                </template>
              </el-table-column>
              <el-table-column prop="" label="会议预约">
                <template>
                  <el-button type="primary" icon="el-icon-plus" circle @click="addappointment()"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </div>
        <div class="roomMessage">
          <el-button style="float: right; padding: 3px 0" type="text" @click="RoomMessage = !RoomMessage" v-if="!RoomMessage">展开</el-button>
           <el-collapse-transition>
          <div v-show="RoomMessage">
            <el-card class="box-card" :style="RoomMessage?'width:100%':'width:30%'" >
              <div v-if="!RoomMessage">
                <el-button @click="RoomMessage = !RoomMessage" icon="el-icon-arrow-right" style="margin:auto"></el-button>
              </div>
           <div  v-if="RoomMessage"> 
            <div slot="header" class="clearfix">
              <span>会议室信息</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="RoomMessage = !RoomMessage">收起</el-button>
            </div>
            <div class="text">
              <p style="margin-top:20px">会议室号为：{{this.currentRow.name}}</p>
              <p style="margin-top:20px">当前状态：{{this.currentRow.meeting?'会议中':'空闲'}}</p>
              <p style="margin:20px 0" v-show="isShow">会议名称：{{this.currentRow.meeting?this.currentRow.meeting.name:''}}</p>
              <p style="margin:20px 30px 0 0" v-show='isShow'>开始时间：{{this.currentRow.meeting?this.currentRow.meeting.startTime.substr(0,10)+' '+this.currentRow.meeting.startTime.substr(11,8):''}}</p>
              <p style="margin-top:20px" v-show='isShow'>结束时间：{{this.currentRow.meeting?this.currentRow.meeting.endTime.substr(0,10)+' '+this.currentRow.meeting.endTime.substr(11,8):''}}</p>
     

            </div>
           </div>
          </el-card>
          </div>
           </el-collapse-transition>
        </div>
      </div>
      <!-- 分页 -->
      <!-- <div class="pagination">
        <div class="block" style="">

          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            :page-sizes="[1,5,10,15,20]" :current-page.sync="currentPage" :page-size="1"
            layout="sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>

        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
import getMeetingsAPI from '@/service/MeetingRoomManagement';
import addOrderMeetingAPI from '@/service/MeetingRoomManagement'
  import {
    mapState,
    mapMutations
  } from 'vuex'
  export default {
    data() {
      return {

        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          num: 0,

        },     
        RoomMessage:true,
        dateSelect:'',
        startTime:-1,
        endTime:-1, 
        input: '',
        total: 1,
        timeList:[1,2,3,4,5,6,7
        ,8,9,10,11,12,13,
        14,15,16,17,18,19,20,21,22,23],
        getSelect:[],
        currentRow: [],
        isShow:false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        tableData: [],
        tableFilterData:[],
        DateData:[],
        storeyData: [],
        defaultProps: {
          children: 'meetroom',
          label: 'name'
        }

      }
    },
    computed: {
      ...mapState([
        'userInfo'
      ])
    },
    watch:{
      dateSelect(val){
        this.startTime = -1
        this.endTime = -1
        this.getSelect = []
        if(val){
          let newTime = ' 00:00:00'
          addOrderMeetingAPI.getAllMeetingTimeByRoomAndTime({
            date:val.toLocaleDateString().substr(9)?
            val.toLocaleDateString().replace(/\//g,"-")
            +newTime:val.toLocaleDateString().replace(/\//g,"-").substr(0,8)
            +'0'
            +val.toLocaleDateString().replace(/\//g,"-").substr(8)
            +newTime,
            meetingroomid:this.currentRow.meetingRoomId,
          }).then(res=>{
            this.DateData = res.object
            console.log(res.object)
            this.DateData.map((item,index)=>{
              if(item.startTime){
                for(var i = 0;i<24;i++){
                  (item.startTime.substr(11,2)<=i)&& (i<=item.endTime.substr(11,2))?this.getSelect.push(i):'';
                 
                }
                console.log(this.getSelect.filter(item=>{if(item == 12) return item}))
                
              }
            })
          }).catch(err=>{
            this.$message.error('获取失败!')
            console.log("getAllMeetingTimeByRoomAndTime",err)
          })
        }
        
      }
    },
    created(){
      //获取预约成功的会议
      getMeetingsAPI.getAllMeetings()
      .then(res=>{
        console.log(res.object)  
      }).catch(err=>{
        this.$message.error('获取失败')
        console.log('getAllMeetings:',err)
      }),
      //获取会议室信息
      getMeetingsAPI.getAllMeetingRoom()
      .then(res=>{
        console.log(res)
        this.tableData = res.object;
        this.tableFilterData = this.tableData
        this.tableData.map((item,index)=>{
            console.log(item.name)
        })

      }).catch(err=>{
        this.$message.error('获取失败')
        console.log('getAllMeetingRoom',err)
      }),
      //获取左侧树的信息
      getMeetingsAPI.getAllMeetingRoomByStorey()
      .then(res=>{
        console.log("storeyData:",res.object)
        this.storeyData = res.object;
      }).catch(err=>{
        this.$message.error('获取失败')
        console.log('getAllMeetingRoomByStorey',err)
      })
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      search() {
        console.log("receive", this.userInfo)
      },
      //重选
      reselect(){
        this.startTime = -1;
        this.endTime = -1;
      },
      handleCurrentChange(val,oldval) {
        if(val !=null){
          this.currentRow = val;
        }
        else val == null;
        if(this.currentRow.meeting) this.isShow = true;
        else this.isShow = false
      },
      // 会议预约
      addappointment() {
        this.dialogFormVisible = true
        this.dateSelect = ''
      
      },
      //左侧筛选会议室
      handleNodeClick(data) {
        //console.log(data.label);
        // this.tableFilterData = [],
        // console.log(this.tableData)
        this.tableFilterData = this.tableData.filter((item,index)=>{
          return (item.place == data.name || item.name == data.name)
        })

      },
      getIndex(i){
        if(this.startTime == -1){
          this.startTime = i;
        }
        else if(this.startTime != -1 && (this.endTime == -1 || this.endTime >= this.startTime)){
          this.endTime = i
          if(this.endTime >this.startTime){
            this.getSelect.map((item,index)=>{
              (this.startTime < item) && (this.endTime > item)?(this.startTime = i,this.endTime =-1):''
            })
          }
          if(this.endTime < this.startTime) this.startTime = i,this.endTime= -1

        }
      },
      meetingComfirm(){
        this.dialogFormVisible = false;
        console.log(this.currentRow.meetingRoomId,typeof(this.currentRow.meetingRoomId))
        console.log(this.form.remark)
        addOrderMeetingAPI.addOrderMeeting({
          employeeid:this.userInfo.userinfo.employeeId,
          meetingroomid:this.currentRow.meetingRoomId,
          name:this.form.name,
          peoplenum:this.form.num, 
          startTime:this.startTime<10?this.dateSelect.toLocaleDateString().replace(/\//g,'-')+' 0'+this.startTime+':00:00':this.dateSelect.toLocaleDateString().replace(/\//g,'-')+' '+this.startTime+':00:00',
          endtime:this.endTime<10?this.dateSelect.toLocaleDateString().replace(/\//g,'-')+' 0'+this.endTime+':00:00':this.dateSelect.toLocaleDateString().replace(/\//g,'-')+' '+this.endTime+':00:00',
          remark:this.form.remark?this.form.remark:'',
        }).then(res=>{
          console.log('预约成功!');
        }).catch(err=>{
          this.$message.error("预约失败")
          console.log('addOrderMeeting:',err)
        })
      }
    },
    mounted() {
      this.UPDATE_BREAD(['会议室管理', '房间管理'])
    }
  }
</script>

<style lang='less' scoped>
  @import '../../../style/common.less';

  .room {
    height: 100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;

    .alert {
      .el-button {
        width: 98px;
        height: 40px;
      }
      .red{
        background:#F56C6C;
      }
      .blue{
        background: #5383EC;
      }
      .white{
        background:#FFFFFF;
      }
      .colorRemark{
        display: flex;
        justify-content: space-evenly;
        .remarks{
          display: flex;
          .blue{
          margin:13px 5px 0px;height:15px;width:15px;
        }
        .red{
          margin:13px 5px 0px;height:15px;width:15px;
        }
        .white{
          margin:12.5px 5px 0px;height:13.5px;width:13.5px;border:0.5px solid black;
        }
        }
        
      }
      .el-form-item {
        padding-top: 20px;

        .el-button {
          width: 30px;
          height: 30px;

        }
      }
    }

    .msgBox {
      height: 100%;
      width: 100%;
      font-size: 30px;
      display: flex;
      flex-wrap: wrap;

      

      .content {
        width: 100%;
        display: flex;
        // justify-content: space-evenly;
        flex-wrap: nowrap;

        .tree {
          margin-top: 20px;
          //width: 100%;
          height: 500px;
          font-weight: bolder;
          box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
          border-radius: @baseBorderRadius;
          .title {
            background: white;
            text-align: center;
            width: 100%;
            max-width: 200px;
            font-size: 17px;
            font-weight: bolder;
            color: #3D60AD;
            line-height: 50px;
          }

          .el-tree {
            width: 200px;
            height: 500px;
          }
        }

        .table {
          box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
          border-radius: @baseBorderRadius;
          margin-top: 20px;
          padding: 0 10px;
          .el-table{
            height:550px;padding:0 25px;
          }
        }

        .roomMessage {
          width: 50%;
          height: 550px;
          margin-top: 20px;

          .box-card {
            height: 550px;

            .text {
              font-size: 15px;
            }
          }
        }
      }


    }

    ;

  }

  .el-select-dropdown__item {
    padding: 0 20px;
    font-size: 14px;
  }
  
</style>