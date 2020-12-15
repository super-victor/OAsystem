!<!-- SystemInfo -->
<template>
  <div class='SystemInfo'>
    <div class='TheTitle'><i class="el-icon-pie-chart" style="margin-right:5px;"></i>实时数据一览</div>
  </div>
</template>

<script>
  
  export default {
    components: {},
    data() {
      return {
        
      };
    },
    computed: {},
    watch: {},
    methods: {
      
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .SystemInfo{
    height: 220px;
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
  }
</style>