!<!-- ChildrenComponent1 -->
<template>
  <div class='mine'>
    <div class="tabs">
      <el-tabs v-model="activeName" @tab-click="handleClick" class="head">
        <el-tab-pane label="未开始" name="before">
          <ListItem></ListItem>
        </el-tab-pane>
        <el-tab-pane label="正在进行" name="ing">
          <ListItem></ListItem>
        </el-tab-pane>
        <el-tab-pane label="已结束" name="end">
          <ListItem></ListItem>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import ListItem from './ScheduleListItem';
  export default {
    components: {
      ListItem
    },
    data() {
      return {
        activeName:'ing'
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    },
    created() {
      
    },
    mounted() {
      this.$emit('childrenBread',['我的日程']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .mine{
    height: auto;
    width: 100%;
    .tabs{
      width: 10rem;
      padding: 10px;
      border-radius: @baseBorderRadius;
      background-color: @white; 
      ::v-deep .head{
        margin-top: -0.6rem;
      }
    }
  }
</style>