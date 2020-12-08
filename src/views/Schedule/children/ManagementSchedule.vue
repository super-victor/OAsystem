!<!-- ChildrenComponent1 -->
<template>
  <div class='managementSchedule'>
    <div class="table">
      <el-table
          ref="Table"
          :data="tableData"
          style="width: 100%"
          stripe>
          <el-table-column
          prop="startTime"
          label="开始时间"
          width="180">
          </el-table-column>
          <el-table-column
          prop="endTime"
          label="结束时间">
          </el-table-column>
          <el-table-column
          prop="content"
          label="内容"
          width="180">
          </el-table-column>
          <el-table-column
          prop="location"
          label="地点"
          width="180">
          </el-table-column>
          <el-table-column
          prop="leader"
          label="领导人员"
          width="180">
          </el-table-column>
          <el-table-column
          prop="joiner"
          label="参与人员"
          width="180">
          </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import ScheduleApi from '@/service/schedule';
  export default {
    components: {
    },
    data() {
      return {
        tableData:[],
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      getSchedule() {
        ScheduleApi.getCompanySchedule()
        .then(res=>{
          console.log(res);
          res.object.forEach(element => {
            this.tableData.push(element.schedule);
          });
          console.log(this.tableData);
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('获取失败');
        })
      }
    },
    created() {
      this.getSchedule();
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','管理日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .managementSchedule{
    font-size: 0.2rem;
    width: 100%;
    height: 100%;
    background-color: @white;
    border: @baseBorderRadius;
    .table {
      padding: 20px;
    }
  }
</style>