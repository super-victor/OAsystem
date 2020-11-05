<template>
  <div id="app">
    <el-container class="container">
      <el-header>
        <the-header></the-header>
      </el-header>
      <el-container>
        <el-aside>
          <the-aside></the-aside>
        </el-aside>
        <el-container>
          <el-main>
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import TheHeader from '@/components/basic/TheHeader';
import TheAside from '@/components/basic/TheAside';
export default {
  components:{
    TheHeader,
    TheAside
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
  min-width: 1200px;
}
.el-aside{
  box-shadow: 1px 0 1px 0 #E7EBF2;
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
</style>
