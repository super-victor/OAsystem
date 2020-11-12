<!-- HomePage -->
<template>
  <div class='HomePage' @click="mainBoxClick">
    这是主页
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
  .HomePage{
    height: 200%;
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @lighterBackground;
  }
</style>