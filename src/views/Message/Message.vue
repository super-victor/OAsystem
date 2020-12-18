!<!-- SendFileManagement -->
<template>
  <div class='message' @click="mainBoxClick">
    <img src="@/assets/return.png" alt="" @click="routeTo">
    <div class="message_box">
      <el-tabs type="border-card" v-model="theLabel">
        <el-tab-pane label="所有消息" name="所有消息">
          <div class="center flex flex-col" v-loading="loading">
            <message-item
            v-for="item in messageR.slice((currentPage-1)*pageSize,currentPage*pageSize)"
            :key="item.messageId"
            :msg="item"/>
            <el-pagination
              @size-change="handleSizeChangeO"
              @current-change="handleCurrentChangeO"
              :current-page.sync="currentPage"
              :page-sizes="[5, 10, 20, 30]"
              :page-size="pageSize"
              layout="sizes, prev, pager, next"
              :total="totalCount"
              v-if="this.totalCount !== 0"
              class="pagination">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未读消息" name="未读消息">
          <div class="message_box">
            <div class="center flex flex-col" v-loading="loading">
              <message-item
              v-for="item in messageNoR.slice((currentPage2-1)*pageSize2,currentPage2*pageSize2)"
              :key="item.messageId"
              :msg="item"/>
              <el-pagination
                @size-change="handleSizeChangeT"
                @current-change="handleCurrentChangeT"
                :current-page.sync="currentPage2"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="pageSize2"
                layout="sizes, prev, pager, next"
                :total="totalCount2"
                v-if="this.totalCount2 !== 0"
                class="pagination">
              </el-pagination>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import messageApi from '@/service/Message';
  import MessageItem from './component/MessageItem';
  export default {
    components: {
      MessageItem
    },
    data() {
      return {
        messageNoR:[],
        messageR:[],
        theLabel:'所有消息',
        currentPage:1,
        pageSize:5,
        totalCount: 0,
        currentPage2:1,
        pageSize2:5,
        totalCount2: 0,
        loading:true,
      }
    },
    computed: {},
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD','MAIN_CLICK']),
      mainBoxClick(){
        this.MAIN_CLICK(false);
      },
      getMessage(){
        messageApi.getMessage()
        .then(res=>{
          this.messageR = res.object;
          // console.log(res);
          this.totalCount = this.messageR.length;
          this.loading = false;
        })
        .catch(err=>{
          console.log(err);
        })
      },
      getMessageNot(){
        messageApi.getMessageNotRead()
        .then(res=>{
          this.messageNoR = res.object;
          this.totalCount2 = this.messageNoR.length;
          // console.log(res);
          this.loading = false;
        })
        .catch(err=>{
          console.log(err);
        })
      },
      // 返回上一页
      routeTo() {
        this.$router.go(-1);
      },
      handleSizeChangeO(val) {
        this.pageSize = val;
      },
      handleCurrentChangeO(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val;
      },
      handleSizeChangeT(val) {
        this.pageSize2 = val;
      },
      handleCurrentChangeT(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage2 = val;
      }
    },
    created() {
      this.getMessage();
      this.getMessageNot();
    },
    mounted() {
      this.UPDATE_BREAD(['消息']);
    }
  }
</script>
<style lang='less' scoped>
// @import '../../style/common.less';
  .message{
    height: 100%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部子路由组件高度不超过该组件高度，不然会出现bug
    width: 100%;
    padding: 0 30px;
    box-sizing: border-box;
    background-color: #F5F7FA;
    font-size: 30px;
    .message_box {
      height: 500px;
      width: 100%;
      ::v-deep .el-tabs__nav{
        margin-top: -6px;
      }
    }
    img {
      cursor: pointer;
      width: 30px;
      height: 30px;
      margin: 10px 0;
    }
    .center{
      width: 100%;
      padding: 10px 30px;
      height: 500px;
      background-color: #fff;
      border-radius: 4px;
      justify-content: space-between;
      overflow-y: scroll;
      .pagination {
        margin: 0 auto;
      }
    }
    .center::-webkit-scrollbar{
      display: none;
    }
  }
</style>