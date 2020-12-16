<!-- HomePage -->
<template>
  <div class='HomePage' @click="mainBoxClick">
    <div class="leftBox"></div>
    <message></message>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import Message from './component/Message';
  export default {
    components: {
      Message
    },
    data() {
      return {
      };
    },
    computed: {},
    watch: {},
    methods: {
      ...mapMutations([
        'MAIN_CLICK',
        'UPDATE_BREAD'
      ]),
      mainBoxClick(){
        this.MAIN_CLICK(false);
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['协同办公平台']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .HomePage{
    height: 100%;
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @lighterBackground;
    display: flex;
    .leftBox{
      height: 100%;
      width: 13.5rem;
      background-color: cornflowerblue;
    }
  }
</style>