!<!-- Breadcrumb -->
<template>
  <div class='breadcrumb'>
    <el-breadcrumb class="box" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item
      v-for="(item,index) in breadcrumbItem"
      :key="index">{{item}}</el-breadcrumb-item>
    </el-breadcrumb>
    <p class="text">{{breadcrumbItem[breadcrumbItem.length-1]}}</p>
  </div>
</template>

<script>
  
  export default {
    components: {},
    props:{
      breadcrumbItem:{
        type:Array,
        required:true
      }
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
  .breadcrumb{
    height: 100px;
    width: 100%;
    .box{
      height: 40px;
      width: 100%;
      display: flex;
      align-items: center;
    }
    .text{
      height: 35px;
      width: 100%;
      font-size: 22px;
      line-height: 35px;
      font-weight: 450;
      color: @regularText;
    }
  }
</style>