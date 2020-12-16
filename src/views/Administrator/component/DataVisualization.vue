!<!-- DataVisualization -->
<template>
  <div class='DataVisualization'>
    <div class='TheTitle'><i class="el-icon-data-line" style="margin-right:5px;"></i>系统数据可视化</div>
    <div class="dataBox" id="myChart1" style="margin-top:20px;"></div>
    <div class="dataBox" id="myChart2"></div>
    <div class="dataBox" id="myChart3"></div>
    <div class="dataBox" id="myChart4"></div>
  </div>
</template>

<script>
  import administratorAPI from '@/service/Administrator';
  export default {
    components: {},
    data() {
      return {
        scheduleInfo:{},
        meetingInfo:{},
        documentDraftInfo:{},
        documentPublishInfo:{}
      };
    },
    computed: {},
    watch: {},
    methods: {
      darwChart(){
        //日程
        let myChart1 = this.$echarts.init(document.getElementById('myChart1'));
        myChart1.setOption({
          title: {
              text: '公司日程发布',
              subtext: '近15天内公司日程发布数量统计',
              left: 'right'
          },
          xAxis: {
              type: 'category',
              data: this.scheduleInfo.dateArr
          },
          yAxis: {
              type: 'value',
              minInterval:1
          },
          series: [{
              data: this.scheduleInfo.numberArr,
              type: 'line'
          }]
        })
        //会议
        let myChart2 = this.$echarts.init(document.getElementById('myChart2'));
        myChart2.setOption({
          title: {
              text: '公司会议预约',
              subtext: '近15天内公司各部门会议预约数量统计',
              left: 'right'
          },
          tooltip: {
              trigger: 'axis'
          },
          legend: {
              data: this.meetingInfo.departmentArr
          },
          grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              data: this.meetingInfo.dateArr
          },
          yAxis: {
              type: 'value',
              minInterval:1
          },
          series: this.meetingInfo.infoArr
        })
        //公文拟稿
        let myChart3 = this.$echarts.init(document.getElementById('myChart3'));
        myChart3.setOption({
          title: {
              text: '公司拟稿撰写',
              subtext: '近15天内公司各部门撰写公文拟稿数量统计',
              left: 'right'
          },
          tooltip: {
              trigger: 'axis'
          },
          legend: {
              data: this.documentDraftInfo.departmentArr
          },
          grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              data: this.documentDraftInfo.dateArr
          },
          yAxis: {
              type: 'value',
              minInterval:1
          },
          series: this.documentDraftInfo.infoArr
        })
        //公文发布
        let myChart4 = this.$echarts.init(document.getElementById('myChart4'));
        myChart4.setOption({
          title: {
              text: '公司公文发布',
              subtext: '近15天内公司各部门过审发布公文数量统计',
              left: 'right'
          },
          tooltip: {
              trigger: 'axis'
          },
          legend: {
              data: this.documentPublishInfo.departmentArr
          },
          grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              data: this.documentPublishInfo.dateArr
          },
          yAxis: {
              type: 'value',
              minInterval:1
          },
          series: this.documentPublishInfo.infoArr
        })
      },
      initScheduleInfo(data){
        let dateArr = [];
        let numberArr = [];
        for(let item of data){
          dateArr.unshift(item.recordDate.substring(5));
          numberArr.unshift(item.num);
        }
        this.scheduleInfo = {
          dateArr,
          numberArr
        }
      },
      initMeetingInfo(data){
        let dateArr = this.scheduleInfo.dateArr;
        let departmentArr = [];
        let infoArr = [];
        for(let item in data.meeting){
          departmentArr.push(item);
          infoArr.push({
            name:item,
            type:'line',
            stack:'总量',
            data:data.meeting[item].info.map(item=>item.sum)
          })
        }
        this.meetingInfo = {
          dateArr,
          departmentArr,
          infoArr
        }
      },
      initDocumentDraftInfo(data){
        let dateArr = this.scheduleInfo.dateArr;
        let departmentArr = [];
        let infoArr = [];
        for(let item in data){
          departmentArr.push(item);
          infoArr.push({
            name:item,
            type:'line',
            stack:'总量',
            data:data[item].map(item=>item.num)
          })
        }
        this.documentDraftInfo = {
          dateArr,
          departmentArr,
          infoArr
        }
      },
      initDocumentPublishInfo(data){
        let dateArr = this.scheduleInfo.dateArr;
        let departmentArr = [];
        let infoArr = [];
        for(let item in data){
          departmentArr.push(item);
          infoArr.push({
            name:item,
            type:'line',
            stack:'总量',
            data:data[item].map(item=>item.num)
          })
        }
        this.documentPublishInfo = {
          dateArr,
          departmentArr,
          infoArr
        }
      },
    },
    created() {
      administratorAPI.getSystemInfo()
      .then(res=>{
        this.initScheduleInfo(res.object.schedule);
        this.initMeetingInfo(res.object.meeting);
        this.initDocumentDraftInfo(res.object.document.depertmentDraftBoxDocument);
        this.initDocumentPublishInfo(res.object.document.depertmentPublishDocument);
        this.darwChart();
      })
      .catch(err=>{
        this.$message.error('获取系统信息失败');
      })
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .DataVisualization{
    margin-top: 20px;
    height: 2100px;
    width: 100%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .TheTitle{
      height: 50px;
      width: 185px;
      box-sizing: border-box;
      background-color: @primaryColor;
      border-bottom-right-radius: 10px;
      color: @white;
      border-right: 3px solid @correlateColor1;
      border-bottom: 3px solid @correlateColor1;
      font-size: 18px;
      line-height: 50px;
      text-align: center;
      user-select: none;
    }
    .dataBox{
      height: 400px;
      width: 80%;
      margin-left: 10%;
      margin-top: 130px;
    }
  }
</style>