!<!-- SystemInfo -->
<template>
  <div class='SystemInfo' v-loading="loading">
    <div class='TheTitle'><i class="el-icon-pie-chart" style="margin-right:5px;"></i>实时数据一览</div>
    <div class="infoBox">
      <div class="numberBox">
        <div class="numberItem">
          <p class="title">总访问量</p>
          <p class="number">{{totalViews}}</p>
        </div>
        <div class="numberItem">
          <p class="title">当日访问量</p>
          <p class="number">{{dailyViews}}</p>
        </div>
      </div>
      <div class="chartBox" id="myChart"></div>
    </div>
  </div>
</template>

<script>
  import administratorAPI from '@/service/Administrator';
  export default {
    components: {},
    data() {
      return {
        dailyViews:0,
        totalViews:0,
        departmentArr:[],
        dataArr:[],
        loading:true
      };
    },
    computed: {},
    watch: {},
    methods: {
      darwChart(){
        let myChart = this.$echarts.init(document.getElementById('myChart'));
        myChart.setOption({
          title: {
              text: '公司员工组成结构',
              subtext: '各部门人员占比',
              left: 'center'
          },
          tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
              orient: 'vertical',
              left: 'left',
              data: this.departmentArr
          },
          series: [
              {
                  name: '人员占比',
                  type: 'pie',
                  radius: '55%',
                  center: ['50%', '60%'],
                  data: this.dataArr,
                  emphasis: {
                      itemStyle: {
                          shadowBlur: 10,
                          shadowOffsetX: 0,
                          shadowColor: 'rgba(0, 0, 0, 0.5)'
                      }
                  }
              }
          ]
        })
      }
    },
    created() {
      administratorAPI.getSystemViews()
      .then(res=>{
        let {dailyViews,totalViews,department} = res.object;
        this.dailyViews = dailyViews;
        this.totalViews = totalViews;
        let departmentInfo = department.eachDepartmentNum;
        for(let item in departmentInfo){
          this.departmentArr.push(item);
          this.dataArr.push({
            value:departmentInfo[item],
            name:item
          })
        }
        this.darwChart();
        this.loading = false;
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
  .SystemInfo{
    height: 330px;
    width: 100%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .TheTitle{
      height: 50px;
      width: 165px;
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
    .infoBox{
      height: 260px;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: space-between;
      .numberBox{
        height: 95%;
        width: 60%;
        display: flex;
        align-items: center;
        justify-content: space-around;
        .numberItem{
          height: 70%;
          width: 330px;
          background-color: @correlateColor1;
          border-radius: @baseBorderRadius;
          .title{
            height: 60px;
            width: 100%;
            font-size: 30px;
            font-weight: bolder;
            color: @correlateColor5;
            line-height: 60px;
            padding-left: 30px;
            box-sizing: border-box;
          }
          .number{
            height: 100px;
            width: 100%;
            color: @white;
            text-align: center;
            line-height: 100px;
            font-size: 70px;
            font-weight: 300;
          }
        }
      }
      .chartBox{
        height: 95%;
        width: 35%;
      }
    }
  }
</style>