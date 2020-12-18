!<!-- Document -->
<template>
  <div class='Document' v-loading="loading">
    <p class="title">个人公文统计</p>
    <div class="dataBox" id="myChart">
    </div>
  </div>
</template>

<script>
  import homeApi from '@/service/Home';
  export default {
    components: {},
    data() {
      return {
        documentInfo:{},
        loading:true
      };
    },
    computed: {},
    watch: {},
    methods: {
      darwChart(){
        let myChart = this.$echarts.init(document.getElementById('myChart'));
        myChart.setOption({
          tooltip: {
              trigger: 'axis'
          },
          legend: {
              data: this.documentInfo.textArr
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
              data: this.documentInfo.dateArr
          },
          yAxis: {
              type: 'value',
              minInterval:1
          },
          series: this.documentInfo.infoArr
        })
      },
      initDocumentInfo(data){
        let dateArr = [];
        let textArr = ['撰写拟稿数','发布公文数'];
        let infoArr = [];
        for(let item of data.selfDraftBoxDocument){
          dateArr.push(item.recordDate.substring(5));
        }
        infoArr.push({
          name:'撰写拟稿数',
          type:'line',
          stack:'总量',
          data:data.selfDraftBoxDocument.map(item=>item.num)
        })
        infoArr.push({
          name:'发布公文数',
          type:'line',
          stack:'总量',
          data:data.selfPublishDocument.map(item=>item.num)
        })
        this.documentInfo = {
          dateArr,
          textArr,
          infoArr
        }
      },
    },
    created() {
      homeApi.getPersonalInfo()
      .then(res=>{
        this.initDocumentInfo(res.object.document);
        this.darwChart();
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('个人公文信息获取失败');
      })
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .Document{
    height: calc(100vh - 392px);
    width: 100%;
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
    .dataBox{
      height: calc(100vh - 480px);
      width: 90%;
      margin-left: 5%;
    }
  }
</style>