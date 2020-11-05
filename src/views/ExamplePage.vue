<!-- Example -->
<template>
  <div class='exampleBox'>
    <the-breadcrumb :breadcrumbItem="breadcrumbItem"></the-breadcrumb>
    <div class="msgBox">这里面开始书写当前页面内容，该div宽度需为100%，高度可根据页面自定</div>
  </div>
</template>

<script>
  import TheBreadcrumb from '@/components/control/TheBreadcrumb';
  export default {
    components: {
      TheBreadcrumb
    },
    data() {
      return {
        breadcrumbItem:['示例页面1','示例页面2']
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
@import '../style/common.less';
  .exampleBox{
    height: 100%;
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @background;
    .msgBox{
      height: 1000px;
      width: 100%;
      background-color: salmon;
    }
  }
</style>