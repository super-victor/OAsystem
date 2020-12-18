!<!-- Schedule -->
<template>
  <div class='Schedule'>
    <div class="dataBox" v-loading="loading">
      <p class="title">个人日程<el-tag style="margin-left:10px;padding:0 7px;">{{selfScheduleCount}}</el-tag></p>
<<<<<<< HEAD
      <div class="noDataBox" v-if="selfSchedule==null">
=======
      <div class="noDataBox" v-if="selfScheduleCount==0">
>>>>>>> lkx
        <img src="@/assets/noSchedule.png" alt="" class="img">
        <p class="text">暂无即将开始的日程</p>
      </div>
      <div class="scheduleBox" v-else>
        <div class="titleBox">
          <p class="smallTitle"><span style="background-color:#9CB7F0;padding:3px;">即将开始</span></p>
          <p class="titleText">
            <span style="font-size:0.23rem;height:0.4rem;line-height:0.41rem;color:#303133;">{{selfSchedule.content}}</span>
          </p>
        </div>
        <div style="height:10%;width:100%;border-bottom: 1px solid #C0C4CC;"></div>
        <p class="location"><i class="el-icon-location-information"></i> {{selfSchedule.location}}</p>
        <p class="remark"><i class="el-icon-edit-outline"></i> {{selfSchedule.remark}}</p>
        <p class="timeBox"><i class="el-icon-time"></i> {{selfSchedule.startTime}} - {{selfSchedule.endTime}}</p>
      </div>
    </div>
    <div class="dataBox" v-loading="loading">
      <p class="title">公司日程<el-tag style="margin-left:10px;padding:0 7px;">{{companyScheduleCount}}</el-tag></p>
<<<<<<< HEAD
      <div class="noDataBox" v-if="companySchedule==null">
=======
      <div class="noDataBox" v-if="companyScheduleCount==0">
>>>>>>> lkx
        <img src="@/assets/noSchedule.png" alt="" class="img">
        <p class="text">暂无即将开始的日程</p>
      </div>
      <div class="scheduleBox" v-else>
        <div class="titleBox">
          <p class="smallTitle"><span style="background-color:#9CB7F0;padding:3px;">即将开始</span></p>
          <p class="titleText">
<<<<<<< HEAD
            <span style="font-size:0.23rem;height:0.4rem;line-height:0.41rem;color:#303133;">{{companySchedule.content}}</span>
          </p>
        </div>
        <div style="height:10%;width:100%;border-bottom: 1px solid #C0C4CC;"></div>
        <p class="location"><i class="el-icon-location-information"></i> {{companySchedule.location}}</p>
        <p class="remark"><i class="el-icon-edit-outline"></i> {{companySchedule.remark}}</p>
        <p class="timeBox"><i class="el-icon-time"></i> {{companySchedule.startTime}} - {{companySchedule.endTime}}</p>
=======
            <span style="font-size:0.23rem;height:0.4rem;line-height:0.41rem;color:#303133;">{{companyScheule.content}}</span>
          </p>
        </div>
        <div style="height:10%;width:100%;border-bottom: 1px solid #C0C4CC;"></div>
        <p class="location"><i class="el-icon-location-information"></i> {{companyScheule.location}}</p>
        <p class="remark"><i class="el-icon-edit-outline"></i> {{companyScheule.remark}}</p>
        <p class="timeBox"><i class="el-icon-time"></i> {{companyScheule.startTime}} - {{companyScheule.endTime}}</p>
>>>>>>> lkx
      </div>
    </div>
  </div>
</template>

<script>
  import homeApi from '@/service/Home';
  export default {
    components: {},
    data() {
      return {
        selfScheduleCount:0,
        companyScheduleCount:0,
<<<<<<< HEAD
        selfSchedule:null,
        companySchedule:null,
=======
        selfSchedule:{},
        companyScheule:{},
>>>>>>> lkx
        loading:true
      };
    },
    computed: {},
    watch: {},
    methods: {
      
    },
    created() {
      homeApi.getScheduleCount()
      .then(res=>{
        this.selfScheduleCount = res.object.selfScheduleCount;
        this.companyScheduleCount = res.object.companyScheduleCount;
        homeApi.getSchedule()
        .then(res=>{
          this.selfSchedule = res.object.selfSchedule;
<<<<<<< HEAD
          this.companySchedule = res.object.companyScheule;
=======
          this.companyScheule = res.object.companyScheule;
>>>>>>> lkx
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('日程信息获取失败');
        })
      })
      .catch(err=>{
        this.$message.error('日程信息获取失败');
      })
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .Schedule{
    height: 212px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    .dataBox{
      height: 100%;
      width: 48.3%;
      background-color: @white;
      box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
      border-radius: @baseBorderRadius;
      .title{
        height: 60px;
        width: 100%;
        font-size: 0.26rem;
        padding-left: 0.4rem;
        box-sizing: border-box;
        color: @correlateColor5;
        line-height: 60px;
        user-select: none;
        font-family:'jdzhonyuanjian2510280c4f22004';
      }
      .noDataBox{
        height: 60%;
        width: 86%;
        margin-left: 7%;
        display: flex;
        flex-direction: column;
        align-items: center;
        .img{
          height: 0.8rem;
          width: 0.8rem;
          margin-top: 0.2rem;
        }
        .text{
          height: 0.3rem;
          width: 100%;
          color: #d8d8d8;
          font-size: 0.13rem;
          line-height:0.3rem;
          text-align: center;
        }
      }
      .scheduleBox{
        height: 60%;
        width: 94%;
        margin-left: 3%;
        padding: 10px;
        box-sizing: border-box;
        background-color: @lighterBorder;
        border-radius: @smallBorderRadius;
        .titleBox{
          height: 27%;
          width: 100%;
          display: flex;
          .smallTitle{
            height: 100%;
            width: 25%;
            font-size: 0.18rem;
            padding-left: 0.2rem;
            box-sizing: border-box;
            color: #4A76D4;
            line-height: 30px;
            user-select: none;
            font-family:'jdzhonyuanjian2510280c4f22004';
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
          }
          .titleText{
            height: 100%;
            width: 75%;
            display: flex;
            align-items: center;
          }
        }
        .location{
          height: 20%;
          width: 100%;
          font-size: 0.18rem;
          padding-left: 0.2rem;
          box-sizing: border-box;
          line-height: 25px;
          color: @regularText;
        }
        .remark{
          height: 20%;
          width: 100%;
          font-size: 0.18rem;
          padding-left: 0.2rem;
          box-sizing: border-box;
          line-height: 25px;
          color: @regularText;
        }
        .timeBox{
          height: 20%;
          width: 100%;
          font-size: 0.18rem;
          padding-left: 0.2rem;
          box-sizing: border-box;
          line-height: 25px;
          overflow: hidden;
          text-overflow:ellipsis;
          white-space: nowrap;
          color: @regularText;
        }
      }
    }
  }
</style>