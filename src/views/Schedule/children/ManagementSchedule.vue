!<!-- ChildrenComponent1 -->
<template>
  <div class='CompanySchedule'>
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
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
    },
    created() {
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','管理日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .CompanySchedule{
    font-size: 0.2rem;
    padding: 50px;
    width: 80%;
    background-color: @white;
    border: @baseBorderRadius;
  }
</style>