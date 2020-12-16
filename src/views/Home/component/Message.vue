!<!-- Message -->
<template>
  <div class='Message' v-loading="loading">
    <p class="title">未处理消息</p>
    <div class="messageBox">
      <div 
      class="messageItem"
      v-for="item in message"
      :key="item.messageId">
      <div class="titleBox"></div>
      <p class="text"></p></div>
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
    computed: {},
    watch: {},
    methods: {
      
    },
    created() {
      messageApi.getMessageNotRead()
      .then(res=>{
        console.log(res);
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
    background-color: crimson;
    margin-left: 20px;
    background-color: @white;
    box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
    border-radius: @baseBorderRadius;
    .title{
      height: 60px;
      width: 100%;
      background-color: cyan;
      font-size: 0.26rem;
      padding-left: 0.2rem;
      box-sizing: border-box;
      color: @correlateColor5;
      line-height: 60px;
      user-select: none;
      font-family:'jdzhonyuanjian2510280c4f22004';
    }
    .messageBox{
      height: calc(100vh - 320px);
      width: 90%;
      margin: 5px 0 0 5%;
      background-color: aquamarine;
      overflow-y: scroll;
      .messageItem{
        height: 100px;
        width: 100%;
        background-color: aqua;
        margin-top: 15px;
        display: flex;
        flex-direction: column;
        align-items: center;
        .titleBox{
          height: 30px;
          width: 85%;
          background-color: yellow;
          margin-top: 5px;
        }
        .text{
          height: 50px;
          width: 85%;
          background-color: yellowgreen;
          margin-top: 7px;
        }
      }
    }
    .messageBox::-webkit-scrollbar{
      display: none;
    }
  }
</style>