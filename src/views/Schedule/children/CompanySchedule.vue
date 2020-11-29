!<!-- ChildrenComponent1 -->
<template>
  <div class='CompanySchedule'>
    <Calendar/>
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
      this.UPDATE_BREAD(['日程安排','公司日程']);
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