!<!-- TheHeaderBox -->
<template>
  <div 
  class='TheHeaderBox'>
    <div 
    class="innerBox"
    :style="{'background':flag?'#EFEFEE':'#FFFFFF'}"
    @mouseenter="flag=!flag"
    @mouseleave="flag=!flag">
      <slot></slot>
    </div>
  </div>
</template>

<script>
  
  export default {
    components: {},
    data() {
      return {
        flag:false
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
  .TheHeaderBox{
    margin-right: 5px;
    height: 100%;
    box-sizing: border-box;
    width: auto;
    border-radius: @baseBorderRadius;
    .innerBox{
      transition: background .3s;
      cursor: pointer;
      height: 44px;
      width: auto;
      margin: 8px 0;
      padding: 0 7px;
      display: flex;
      align-items: center;
      box-sizing: border-box;
    }
  }
</style>