!<!-- ChildrenComponent1 -->
<template>
  <div class='CompanySchedule'>
    <div class="center">
      <Calendar :events="evs" :type="'company'"/>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import Calendar from '../component/Calendar';
import scheduleAPI from '@/service/schedule';
  export default {
    components: {
      Calendar
    },
    data() {
      return {
        evs:[],
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      // 获取公司日程
      getSchedule() {
        scheduleAPI.getCompanySchedule()
        .then(res=>{
          console.log(res);
          res.object.forEach(element => {
            this.evs.push({id:element.scheduleId,title:element.schedule.content,start:element.schedule.startTime,end:element.schedule.endTime,backgroundColor: '#9CB7F0',borderColor: '#9CB7F0', textColor: '#606266'})
          });
          this.$forceUpdate();
        })
        .catch(err=>{
          console.log(err);
        })
      }
    },
    created() {
      this.getSchedule();
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','公司日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .CompanySchedule{
    height: 600px;
    width: 100%;
    overflow-y: scroll;
    .center {
      padding: 50px;
      border-radius: @baseBorderRadius;
      background-color: @white;
    }
  }
  .CompanySchedule::-webkit-scrollbar{
    display: none;
  }
</style>