!<!-- ChildrenComponent1 -->
<template>
  <div class='mine'>
    <div class="center" v-loading="loading">
      <Calendar :events="evs" :type="'mine'"/>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import Calendar from '../component/Calendar';
import scheduleAPI from '@/service/schedule'
  export default {
    components: {
      Calendar
    },
    data() {
      return {
        evs:[],
        loading:true,
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
    getSchedule() {
      scheduleAPI.getSelfSchedule()
      .then(res=>{
        res.object.forEach(element => {
          this.evs.push({id:element.scheduleId,title:element.content,start:element.startTime,end:element.endTime,backgroundColor: '#9CB7F0',borderColor: '#9CB7F0', textColor: '#303133'})
        });
        this.$forceUpdate();
        this.loading=false;
      })
      .catch(err=>{
        console.log(err);
      })
    }
    },
    created() {
      let role = this.$authority.getPageAuthority('schedule','mine').role;
      if (role['0016'].own) {
        this.getSchedule();
      } else {
        this.$message.error('您没有获取日程的权限！');
      }
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
    width: 100%;
    overflow-y: scroll;
    .center {
      padding: 50px;
      border-radius: @baseBorderRadius;
      background-color: @white;
    }
  }
  .mine::-webkit-scrollbar{
    display: none;
  }
</style>