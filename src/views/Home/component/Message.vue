!<!-- Message -->
<template>
  <div class='Message' v-loading="loading">
    <p class="title">未处理消息</p>
    <div v-if="message.length==0" class="noDataBox">
      <img src="@/assets/noData.png" alt="">
      <p class="noData">暂无未读消息</p>
    </div>
    <div v-else>
      <div class="messageBox">
        <div 
        class="messageItem"
        v-for="item in message"
        :key="item.messageId">
        <div class="titleBox">
          <el-tag effect="dark" class="tag" :type="item.type==0?'danger':(item.type==1?'success':(item.type==2?'':'warning'))">{{item.type==0?'危险':(item.type==1?'成功':(item.type==2?'提示':'警告'))}}</el-tag>
          <span class="titleText">{{item.title}}</span>
        </div>
        <p class="text">{{item.content}}</p></div>
      </div>
      <div class="buttonBox">
        <el-button type="primary" style="width:90%;">前往查看</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import messageApi from '@/service/Message';
  export default {
    components: {},
    data() {
      return {
        loading:true,
        message:[]
      };
    },
    computed: {
    },
    watch: {},
    methods: {
    },
    created() {
      messageApi.getMessageNotRead()
      .then(res=>{
        this.message = res.object;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('消息获取失败');
      })
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .Message{
    height: 100%;
    width: 5rem;
    margin-left: 20px;
    background-color: @white;
    box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
    border-radius: @baseBorderRadius;
    .title{
      height: 60px;
      width: 100%;
      font-size: 0.26rem;
      padding-left: 0.4rem;
      box-sizing: border-box;
      color: @correlateColor5;
      line-height: 60px;
      user-select: none;
      font-family:'jdzhonyuanjian2510280c4f22004';
    }
    .title::after{
      content: ' ●';
      font-size: 0.13rem;
      position: relative;
      color: #F56C6C;
      top: -5px;
    }
    .noDataBox{
      height: calc(100vh - 320px);
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      .noData{
        height: 20px;
        width: 100px;
        text-align: center;
        color: #d8d8d8;
        font-size: 0.12rem;
      }
    }
    .messageBox{
      height: calc(100vh - 320px);
      width: 90%;
      margin: 5px 0 0 5%;
      overflow-y: scroll;
      .messageItem{
        height: 100px;
        width: 100%;
        background-color: @lighterBorder;
        margin-top: 15px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .titleBox{
          height: 30px;
          width: 85%;
          margin-top: 5px;
          display: flex;
          align-items: center;
          text-align: center;
          .tag{
            height: 25px;
            width: 50px;
            line-height: 23px;
          }
          .titleText{
            font-size: 14px;
            margin-left: 8px;
          }
        }
        .text{
          height: 50px;
          width: 85%;
          margin-top: 7px;
          font-size: 10px;
          color: @secondaryText;
        }
      }
    }
    .messageBox::-webkit-scrollbar{
      display: none;
    }
    .buttonBox{
      height: 40px;
      width: 100%;
      margin-top: 20px;
      display: flex;
      justify-content: center;
    }
  }
</style>