<template>
  <div id="app">
    <div v-if="$store.state.userToken!==null">
      <el-container class="container">
        <el-header>
          <the-header></the-header>
        </el-header>
        <el-container>
          <el-aside>
            <the-aside></the-aside>
          </el-aside>
          <the-drawer></the-drawer>
          <el-container>
            <el-main>
              <router-view/>
            </el-main>
          </el-container>
        </el-container>
      </el-container>
    </div>
    <div v-else>
      <router-view/>
    </div>
  </div>
</template>

<script>
import UserLogin from '@/views/Login/UserLogin';
import TheHeader from '@/components/basic/TheHeader';
import TheAside from '@/components/basic/TheAside';
import TheDrawer from '@/components/control/TheDrawer';
export default {
  data() {
    return {
      asideItem:{}
    };
  },
  components:{
    TheHeader,
    TheAside,
    TheDrawer,
    UserLogin
  },
  methods: {
  }
}
</script>

<style lang="less">
@import './style/common.less';
#app {
  height: 100vh;
}
.el-header{
  padding: 0;
  min-width: 1000px;
}
.el-aside{
  box-shadow: 1px 0 4px 0 #E7EBF2;
  transform: translate3d(0, 0, 0);
  width: 120px !important;
}
.el-main{
  min-width: 880px;
  padding: 0;
}
.container{
  height: 100%;
}
.el-loading-spinner i{
  font-size: 20px;
}
.el-message .el-icon-error{
  font-size: 20px;
}
.el-message .el-icon-success{
  font-size: 20px;
}
.el-message .el-icon-warning{
  font-size: 20px;
}
.el-loading-mask.is-fullscreen .el-loading-spinner{
  font-size: 15px;
}
.el-loading-spinner .el-loading-text{
  font-size: 10px;
}
</style>
