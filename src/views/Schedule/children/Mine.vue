!<!-- ChildrenComponent1 -->
<template>
  <div class='mine'>
    <div class="center">
      <Calendar />
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import Calendar from '../component/Calendar';
  export default {
    components: {
      Calendar
    },
    data() {
      return {
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
    },
    created() {
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','我的日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .mine{
    height: 600px;
    width: 80%;
    .center {
      margin-left: 10%;
      padding: 50px;
      border-radius: @baseBorderRadius;
      background-color: @white;
    }
  }
</style>