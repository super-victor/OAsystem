<template>
  <div class='card'>
    <div class="box flex-col">
      <div class="share">
        <img src="@/assets/Card/Share.png" alt="" @click="shareCard">
      </div>
      <div class="row flex-row">
        <p class="name">{{ msg.name }}</p>
        <p class="depart">/{{ msg.department }}{{ msg.position }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">电话</p>
        <p class="llabel">{{ msg.phone }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">邮箱</p>
        <p class="llabel">{{ msg.email }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">公司</p>
        <p class="llabel">{{ msg.company }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">地址</p>
        <p class="llabel">{{ msg.address }}</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ["msg"],
    data() {
      return {

      };
    },
    computed: {},
    watch: {},
    methods: {
      shareCard() {}
    },
    created() {

    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .card{
    height: 3rem;
    width: 5.6rem;
    box-sizing: border-box;
    background-color: @white;
    background: url(../../../assets/Card/cardBackground.jpg);
    border: 1px solid @baseBorder;
    padding: 10px;
    .box {
      width: 90%;
      height: 100%;
      float: right;
      background-color: @white;
      .share {
        height: 0.4rem;
        text-align: right;
        img {
          width: 0.3rem;
          cursor: pointer;
        }
      }
      p {
        font-size: 0.18rem;
      }
      .row {
        height: 0.7rem;
        margin-left: 0.5rem;
      }
      .info_row {
        height: 0.4rem;
        margin-left: 0.5rem;
      }
      p.name {
        font-size: 0.4rem;
        color: @primaryText;
      }
      p.depart {
        margin-top: 0.2rem;
        margin-left: 0.3rem;
      }
      p.rlabel {
        width: 0.7rem;
      }
    }
  }
</style>