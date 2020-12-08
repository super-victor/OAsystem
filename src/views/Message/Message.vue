!<!-- SendFileManagement -->
<template>
  <div class='message' @click="mainBoxClick">
      <div class="return_button">
        <img src="@/assets/return.png" alt="" @click="routeTo">
      </div>
      <div class="message_box">
        <p class="title">未读消息</p>
        <el-table
            ref="multipleTable1"
            :data="tableDataUnread"
            style="width: 100%"
            tooltip-effect="dark"
            :row-class-name="tableRowClassName"
            @selection-change="handleSelectionChange">
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column
            prop="date"
            label="时间"
            width="180">
                <template slot-scope="scope">{{ scope.row.date }}</template>
            </el-table-column>
            <el-table-column
            prop="type"
            label="类型"
            width="180">
            </el-table-column>
            <el-table-column
            prop="message"
            label="消息">
            </el-table-column>
        </el-table>
        <div>
            <el-button @click="toggleSelection()" class="button" v-if="multipleSelection.length!==0">取消选择</el-button>
            <el-button type="primary" @click="selectAlready()" class="button" v-if="multipleSelection.length!==0">标记已读</el-button>
        </div>
      </div>
      <div class="message_box">
        <p class="title">已读消息</p>
        <el-table
            ref="multipleTable2"
            :data="tableDataRead"
            style="width: 100%"
            tooltip-effect="dark"
            :row-class-name="tableRowClassName">
            <el-table-column
            prop="date"
            label="时间"
            width="180">
            </el-table-column>
            <el-table-column
            prop="type"
            label="类型"
            width="180">
            </el-table-column>
            <el-table-column
            prop="message"
            label="消息">
            </el-table-column>
        </el-table>
      </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  export default {
    components: {
    },
    data() {
      return {
        tableDataUnread: [{
          date: '2020-12-07',
          type: 'success',
          message: '添加修改日程',
        }, {
          date: '2020-12-07',
          type: 'warning',
          message: '获取个人日程的参数有问题',
        },{
          date: '2020-12-07',
          type: 'error',
          message: '获取个人日程的参数处理失败',
        }, {
          date: '2020-12-07',
          type: 'info',
          message: '用户加一',
        }],
        tableDataRead: [{
          date: '2020-12-07',
          type: 'success',
          message: '添加修改名片',
        }],
        multipleSelection:[],
      }
    },
    computed: {},
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD','MAIN_CLICK']),
      mainBoxClick(){
        this.MAIN_CLICK(false);
      },
      // 渲染每行颜色
      tableRowClassName({row, rowIndex}) {
        if (row.type==='warning') {
          return 'warning-row';
        } else if (row.type==='success') {
          return 'success-row';
        } else if (row.type==='info') {
            return 'info-row';
        } else if (row.type==='error') {
            return 'danger-row';
        }
        return '';
      },
      // 选择
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 取消选择
      toggleSelection() {
        this.$refs.multipleTable1.clearSelection();
      },
      // 返回上一页
      routeTo() {
        this.$router.go(-1);
      },
      selectAlready(){},
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['消息','消息']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .message{
    height: 100%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部子路由组件高度不超过该组件高度，不然会出现bug
    width: 100%;
    background-color: @lighterBackground;
    .return_button{
        img {
            cursor: pointer;
            width: 40px;
            height: 40px;
        }
    }
    .message_box{
        width: 10rem;
        margin-left: 40px;
        background-color: @white;
        border-radius: @baseBorderRadius;
        padding: 10px;
        margin-bottom: 40px;
        p.title {
            margin: 10px;
            font-size:0.3rem;
            color: @primaryText;
        }
    }
    ::v-deep .el-table .warning-row {
        color: @warningColor;
    }
    ::v-deep .el-table .success-row {
        color: @successColor;
    }
    ::v-deep .el-table .danger-row {
        color: @dangerColor;
    }
    button {
        width: 100px;
        height: 40px;
    }
  }
</style>