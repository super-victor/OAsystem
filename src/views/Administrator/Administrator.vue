!<!-- Administrator -->
<template>
  <div class='Administrator'>
    <div class='header' @mousewheel.prevent>
      <div class="logoBox">
        <img src="@/assets/logo.png" class="logo" alt="">
      </div>
      <div class="headerBox">
        <p class="name">协同办公平台·控制台</p>
      </div>
    </div>
    <div class="bottomBox">
      <system-info></system-info>
      <div class="tableBox">
        <give-role></give-role>
        <check-logs></check-logs>
      </div>
      <data-visualization></data-visualization>
    </div>
  </div>
</template>

<script>
import CheckLogs from './component/CheckLogs';
import DataVisualization from './component/DataVisualization';
import GiveRole from './component/GiveRole';
import SystemInfo from './component/SystemInfo';
  export default {
    components: {
      SystemInfo,
      GiveRole,
      CheckLogs,
      DataVisualization
    },
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
  @import '../../style/common.less';
  .Administrator{
    padding-top: 1px;
    height: auto;
    width: 100vw;
    background-color: @lighterBackground;
    min-width: 1200px;
    .header{
      top: -1px;
      height: 70px;
      background-color: @white;
      box-shadow: 0 1px 1px 0 #E7EBF2;
      transform: translate3d(0, 0, 0);
      display: flex;
      justify-content: space-between;
      z-index: 9999;
      position: fixed;
      .logoBox{
        height: 100%;
        width: 120px;
        display: flex;
        align-items: center;
        justify-content: center;
        .logo{
          height: 32px;
          width: 37px;
        }
      }
      .headerBox{
        height: 100%;
        width: calc(100vw - 120px);
        min-width: 880px;
        display: flex;
        align-items: center;
        padding-right: 25px;
        box-sizing: border-box;
        position: relative;
        .name{
          height: 37px;
          width: 400px;
          margin-left: 20px;
          font-size: 22px;
          line-height: 39px;
          color: #2C3059;
          user-select: none;
        }
      }
    }
    .bottomBox{
      height: auto;
      width: 100%;
      padding: 20px;
      box-sizing: border-box;
      margin-top: 70px;
      .tableBox{
        margin-top: 20px;
        height: 600px;
        width: 100%;
        display: flex;
        justify-content: space-between;
      }
    }
  }
</style>