!<!-- ChildrenComponent1 -->
<template>
  <div class='list_item flex-row'>
      <img src="@/assets/Schedule/schedule.png" alt="" class="main_img">
      <div class="flex-col center">
        <p class="address">杭州市</p>
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
    computed: {
    },
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
@import '../../../style/common.less';
  .list_item{
    margin-top: 0.5rem;
    margin-left: 1rem;
    margin-bottom: 1rem;
    height: 1.6rem;
    width: 8rem;
    border-radius: @smallBorderRadius;
    background-color: @background;
    box-shadow: 2px 2px 10px @placeholderText;
    .main_img {
      width: 0.8rem;
      height: 0.8rem;
      margin-top: 0.4rem;
    }
    .center {
      padding: 0.15rem;
      .address {
        font-size: 0.3rem;
        color: @primaryText;
      }
      p {
        font-size: 0.18rem;
        color: @regularText;
      }
    }
  }
</style>