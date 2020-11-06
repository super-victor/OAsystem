<!-- Example -->
<template>
  <div class='exampleBox'>
    <the-breadcrumb :breadcrumbItem="breadcrumbItem"></the-breadcrumb>
    <div class="msgBox">这里面开始书写当前页面内容，该div宽度需为100%，高度可根据页面自定，这个主内容div的高度不超过外部exampleBox高度，不然会出现bug，实际书写时可使用组件名替代exampleBox作为class名</div>
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
    height: 200%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @background;
    .msgBox{
      height: 800px;
      width: 100%;
      background-color: salmon;
      font-size: 30px;
    }
  }
</style>