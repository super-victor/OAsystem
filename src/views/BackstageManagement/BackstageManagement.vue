!<!-- BackstageManagement -->
<template>
  <div class='BackstageManagement' @click="mainBoxClick">
    <router-view />
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  export default {
    components: {
    },
    data() {
      return {
      };
    },
    computed: {},
    watch: {},
    methods: {
      ...mapMutations([
        'MAIN_CLICK'
      ]),
      mainBoxClick(){
        this.MAIN_CLICK(false);
      },
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .BackstageManagement{
    height: 100%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部子路由组件高度不超过该组件高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @lighterBackground;
  }
</style>