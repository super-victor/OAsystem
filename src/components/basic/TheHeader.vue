!<!-- Header -->
<template>
  <div class='header' @mousewheel.prevent>
    <div class="logoBox">
      <img src="@/assets/logo.png" class="logo" alt="">
    </div>
  </div>
</template>

<script>
  export default {
    components: {},
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
  .header{
    height: 100%;
    background-color: @white;
    box-shadow: 0 1px 1px 0 #E7EBF2;
    transform: translate3d(0, 0, 0);
    display: flex;
    justify-content: space-between;
    .logoBox{
      height: 100%;
      width: 120px;
      display: flex;
      align-items: center;
      justify-content: center;
      .logo{
        height: 32px;
        width: 37px;
      }
    }
  }
</style>