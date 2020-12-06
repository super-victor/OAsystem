!<!-- SendFileManagement -->
<template>
  <div class='SendFileManagement' @click="mainBoxClick">
    <router-view />
  </div>
</template>

<script>
  import TheBreadcrumb from '@/components/control/TheBreadcrumb';
  import {mapMutations} from 'vuex';
  export default {
    components: {
      TheBreadcrumb
    },
    data() {
      return {
        breadcrumbItem:[]
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
      childrenBread(textArr){
        this.breadcrumbItem = ['日程安排',...textArr];
      }
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .SendFileManagement{
    height: 100%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部子路由组件高度不超过该组件高度，不然会出现bug
    width: 100%;
    padding: 30px;
    font-size: 0.2rem;
    box-sizing: border-box;
    background-color: @lighterBackground;
  }
</style>