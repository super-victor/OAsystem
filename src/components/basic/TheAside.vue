!<!-- Aside -->
<template>
  <div class='aside' @mousewheel.prevent>
    <the-aside-menu></the-aside-menu>
  </div>
</template>

<script>
import TheAsideMenu from '@/components/control/TheAsideMenu';
  export default {
    components: {
      TheAsideMenu
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
  .aside{
    background-color: @white;
    overflow: hidden;
    height: 92.7vh;
    overflow-y: scroll;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    -khtml-user-select: none;
    user-select: none;
  }
</style>