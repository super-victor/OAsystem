!<!-- Breadcrumb -->
<template>
  <div class='breadcrumb'>
    <el-breadcrumb class="box" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item
      v-for="(item,index) in currentBread"
      :key="index">{{item}}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
  import {mapState} from 'vuex';
  export default {
    components: {},
    props:{
    },
    data() {
      return {
        
      };
    },
    computed: {
      ...mapState(['currentBread'])
    },
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
  .breadcrumb{
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    .box{
      height: 40px;
      width: 100%;
      display: flex;
      align-items: center;
    }
  }
</style>