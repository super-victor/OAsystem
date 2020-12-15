<template>
  <div class='room' @click="currentRow2 = {};isClick =[];Timeout = false">
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
            <el-date-picker v-model="dateSelect" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="今日预约" :label-width="formLabelWidth" style="width:65%" v-if="dateSelect">
            <el-button-group>
              <el-button
                :class="(i == getSelect.filter(item=>{if(item == i) return item}))?'red':(((startTime <= i && i<= endTime)||startTime == i)?'blue':'white')"
                v-for="i in 24" :key="i" @click="getIndex(i)"
                :disabled="(i == getSelect.filter(item=>{if(item == i) return item}))?true:false" style='color:black'>
                {{i}}</el-button>
            </el-button-group>
            <div class="colorRemark">
              <div class="remarks">
                <div class="white"></div>
                <div>可预约</div>
                <div class="red"></div>
                <div>已预约</div>
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
          <el-button @click="dialogFormVisible = false ,isClick = []">取 消</el-button>
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
            <span @click="tableFilterData = tableData;isClick = []" style="cursor:pointer">
              楼层信息
            </span>
          </div>
          <div class="elTree" v-loading="loading1">
            <el-tree :data="storeyData" :props="defaultProps" accordion @node-click="handleNodeClick">
            </el-tree>

          </div>

        </div>

        <!-- 表格 -->
        <div class="table" style="width:100%">
          <template>
            <div class="btn" v-loading="loading2">
              <el-button  style="padding:50px"
                v-for='i in tableFilterData.length' :key="i" @click.stop="handleCurrentChange(i-1)">
                <img :src="require(tableFilterData[i-1].meeting?'@/assets/MeetingRoomManagement/red.png':'@/assets/MeetingRoomManagement/green.png')" alt="" v-if="!isClick[i-1]">
                <i class="el-icon-circle-plus-outline" v-show="isClick[i-1]"></i>
                <p style="padding-top:10px">{{tableFilterData[i-1].name}}</p>
              </el-button>


            </div>
          </template>
        </div>
        <div class="roomMessage" :class="{'roomMessage2':currentRow2.name}">
          <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="RoomMessage = !RoomMessage" v-if="!RoomMessage">展开</el-button> -->
          <el-card class="box-card">
            <!-- <div class='EmptyBox' v-if="!currentRow.name">
              <img src="@/assets/noData.png" alt="">
              <p class="noData">暂无内容</p>
            </div> -->
            <div :style="{'display':Timeout?'block':'none'}">
              <div slot="header" class="clearfix">
                <span>会议室信息</span>

              </div>
              <div class="text">

                <p style="margin-top:20px">会议室号为：{{this.currentRow.name}}</p>
                <p style="margin-top:20px">当前状态：{{this.currentRow.meeting?'会议中':'空闲'}}</p>
                <p style="margin:20px 0" v-show="isShow">
                  会议名称：{{this.currentRow.meeting?this.currentRow.meeting.name:''}}</p>
                <p style="margin:20px 30px 0 0" v-show='isShow'>
                  开始时间：{{this.currentRow.meeting?this.currentRow.meeting.startTime.substr(0,10)+' '+this.currentRow.meeting.startTime.substr(11,8):''}}
                </p>
                <p style="margin-top:20px" v-show='isShow'>
                  结束时间：{{this.currentRow.meeting?this.currentRow.meeting.endTime.substr(0,10)+' '+this.currentRow.meeting.endTime.substr(11,8):''}}
                </p>
                <div class="colorRemark">
                  <div class="remarks">
                    <div>注：</div>
                    <div class="red"></div>
                    <div>为会议中的会议室</div>
                  </div>
                </div>

              </div>

            </div>

          </el-card>
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
        Timeout:false,
        isClick: [],
        loading1: true,
        loading2: true,
        RoomMessage: true,
        dateSelect: '',
        startTime: -1,
        endTime: -1,
        input: '',
        total: 1,
        getSelect: [],
        currentRow: {},
        currentRow2: {},
        isShow: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        tableData: [],
        tableFilterData: [],
        DateData: [],
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
    watch: {
      dateSelect(val) {
        this.startTime = -1
        this.endTime = -1
        this.getSelect = []
        if (val) {
          let newTime = ' 00:00:00';
          console.log(val.toLocaleDateString().substr(9) ?
              val.toLocaleDateString().replace(/\//g, "-") +
              newTime : val.toLocaleDateString().replace(/\//g, "-").substr(0, 8) +
              '0' +
              val.toLocaleDateString().replace(/\//g, "-").substr(8) +
              newTime,this.currentRow.meetingRoomId)
          addOrderMeetingAPI.getAllMeetingTimeByRoomAndTime({
            date: val.toLocaleDateString().substr(9) ?
              val.toLocaleDateString().replace(/\//g, "-") +
              newTime : val.toLocaleDateString().replace(/\//g, "-").substr(0, 8) +
              '0' +
              val.toLocaleDateString().replace(/\//g, "-").substr(8) +
              newTime,
            meetingroomid: this.currentRow.meetingRoomId,
          }).then(res => {
            this.DateData = res.object
            console.log(res.object)
            this.DateData.map((item, index) => {
              if (item.startTime) {
                for (var i = 0; i < 24; i++) {
                  (item.startTime.substr(11, 2) <= i) && (i <= item.endTime.substr(11, 2)) ? this.getSelect
                    .push(i): '';

                }
                console.log(this.getSelect.filter(item => {
                  if (item == 12) return item
                }))

              }
            })
          }).catch(err => {
            this.$message.error('获取失败!')
            console.log("getAllMeetingTimeByRoomAndTime", err)
          })
        }

      }
    },
    created() {
      //获取预约成功的会议
      // getMeetingsAPI.getAllMeetings()
      // .then(res=>{
      //   console.log(res.object)  
      // }).catch(err=>{
      //   this.$message.error('获取失败')
      //   console.log('getAllMeetings:',err)
      // }),

      //获取会议室信息
      getMeetingsAPI.getAllMeetingRoom()
        .then(res => {
          this.loading2 = false;
          console.log(res)
          this.tableData = res.object;
          this.tableFilterData = this.tableData
          this.tableData.map((item, index) => {
            console.log(item)
          })

        }).catch(err => {
          this.$message.error('获取失败')
          console.log('getAllMeetingRoom', err)
        }),
        //获取左侧树的信息
        getMeetingsAPI.getAllMeetingRoomByStorey()
        .then(res => {
          this.loading1 = false;
          console.log("storeyData:", res.object)
          this.storeyData = res.object;
        }).catch(err => {
          this.$message.error('获取失败')
          console.log('getAllMeetingRoomByStorey', err)
        })
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      search() {
        console.log("receive", this.userInfo)
      },
      //重选
      reselect() {
        this.startTime = -1;
        this.endTime = -1;
      },
      //选取会议室
      handleCurrentChange(res) {
        // if(val !=null){
        //   this.currentRow = val;
        // }
        // else val == null;
        // if(this.currentRow.meeting) this.isShow = true;
        // else this.isShow = false
        //console.log(res)
        console.log(this.currentRow)
        if (this.isClick[res]) this.addappointment();
        for (var i = 0; i < this.tableFilterData.length; i++) {
          i == res ? this.isClick[i] = true : this.isClick[i] = false;
        }
        setTimeout(()=>{
          this.Timeout = true;  
        },500)
        this.currentRow = this.tableFilterData[res]
        this.currentRow2 = this.tableFilterData[res]
        console.log(this.currentRow)
        if (this.currentRow.meeting) this.isShow = true;
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
        this.isClick = []
        this.tableFilterData = this.tableData.filter((item, index) => {
          return (item.place == data.name || item.name == data.name)
        })

      },
      test() {
        console.log("123")
      },
      getIndex(i) {
        if (this.startTime == -1) {
          this.startTime = i;
        } else if (this.startTime != -1 && (this.endTime == -1 || this.endTime >= this.startTime)) {
          this.endTime = i
          if (this.endTime > this.startTime) {
            this.getSelect.map((item, index) => {
              (this.startTime < item) && (this.endTime > item) ? (this.startTime = i, this.endTime = -1) : ''
            })
          }
          if (this.endTime < this.startTime) this.startTime = i, this.endTime = -1

        }
      },
      //会议预约
      meetingComfirm() {
        this.dialogFormVisible = false;
        this.isClick = [];
        console.log(this.currentRow.meetingRoomId, typeof (this.currentRow.meetingRoomId))
        console.log(this.form.remark)
        addOrderMeetingAPI.addOrderMeeting({
          employeeid: this.userInfo.userinfo.employeeId,
          meetingroomid: this.currentRow.meetingRoomId,
          name: this.form.name,
          peoplenum: this.form.num,
          startTime: this.startTime < 10 ? this.dateSelect.toLocaleDateString().replace(/\//g, '-') + ' 0' + this
            .startTime + ':00:00' : this.dateSelect.toLocaleDateString().replace(/\//g, '-') + ' ' + this
            .startTime + ':00:00',
          endtime: this.endTime < 10 ? this.dateSelect.toLocaleDateString().replace(/\//g, '-') + ' 0' + this
            .endTime + ':00:00' : this.dateSelect.toLocaleDateString().replace(/\//g, '-') + ' ' + this.endTime +
            ':00:00',
          remark: this.form.remark ? this.form.remark : '',
        }).then(res => {
          console.log('预约成功!');
        }).catch(err => {
          this.$message.error("预约失败")
          console.log('addOrderMeeting:', err)
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
      ::v-deep .el-dialog__header {
        padding: 0 30px;
        font-size: 50px;
      }

      ::v-deep .el-dialog__body {
        padding: 0px;
      }

      ::v-deep .el-button {
        width: 98px;
        height: 40px;
      }

      .red {
        background: #F56C6C;
      }

      .blue {
        background: #5383EC;
      }

      .white {
        background: #FFFFFF;
      }

      .colorRemark {
        display: flex;
        justify-content: space-evenly;

        .remarks {
          display: flex;

          .blue {
            margin: 13px 5px 0px;
            height: 15px;
            width: 15px;
          }

          .red {
            margin: 13px 5px 0px;
            height: 15px;
            width: 15px;
          }

          .white {
            margin: 12.5px 5px 0px;
            height: 13.5px;
            width: 13.5px;
            border: 0.5px solid black;
          }
        }

      }

      .el-form-item {
        padding-top: 20px;

        ::v-deep .el-button {
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

          // .el-table{
          //   height:550px;padding:0 25px;
          // }
          .btn {
            width: 100%;
            height: 550px;
            box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
            border-radius: @baseBorderRadius;
            background: white;

            .red {
              background: #F56C6C;
            }

            .blue {
              background: #5383EC;
            }

            .white {
              background: #FFFFFF;
            }
           
            ::v-deep .el-button {
              margin:20px
              
            }
            ::v-deep .el-icon-circle-plus-outline{
              font-size:90px;
            }
          }
        }

        .roomMessage {
          width: 0;
          transition: all 0.5s;
          height: 550px;
          margin-top: 20px;
          opacity: 0;
          .box-card {

            height: 550px;

            .text {
              font-size: 15px;

              .colorRemark {
                margin-top: 108%;

                .remarks {
                  display: flex;

                  .red {
                    margin: 3px;
                    height: 15px;
                    width: 15px;
                    background-color: #F56C6C;
                  }

                  .white {
                    margin: 3px;
                    height: 13.5px;
                    width: 13.5px;
                    border: 0.5px solid black;
                    background-color: #FFF;
                  }
                }

              }
            }
          }
        }
        .roomMessage2 {
          width: 40%;
          opacity: 100;
        }
      }


    }

    ;

  }

  .el-select-dropdown__item {
    padding: 0 20px;
    font-size: 14px;
  }

  .EmptyBox {
    height: 450px;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    .noData {
      height: 20px;
      width: 200px;
      font-size: 0.18rem;
      text-align: center;
      color: #C0C4CC;
    }
  }
</style>