<template>
  <div class='card'>
    <div class="box flex-col">
      <div class="share">
        <img src="@/assets/Card/Share.png" alt="">
      </div>
      <div class="row flex-row">
        <p class="name">{{ msg.name }}</p>
        <p class="depart">/{{ position }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">电话</p>
        <p class="llabel">{{ msg.tel }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">邮箱</p>
        <p class="llabel">{{ msg.email }}</p>
      </div>
      <div class="info_row flex-row">
        <p class="rlabel">地址</p>
        <p class="llabel">{{ msg.location }}</p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: ["msg"],
    data() {
      return {
        name: '张菲燕',
        position: '人事部部门经理',
        tel: '028-82601686',
        email: 'Paranoid_ZH@163.com',
        location: '成龙大道二段1819号'
      };
    },
    computed: {},
    watch: {},
    methods: {
      
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
    .box {
      width: 90%;
      height: 100%;
      float: right;
      background-color: @white;
      .share {
        text-align: right;
        img {
          margin-right: 15px;
          width: 0.3rem;
          cursor: pointer;
        }
      }
      p {
        font-size: 0.18rem;
      }
      .row {
        margin-left: 0.5rem;
      }
      .info_row {
        margin: 0.3rem 0 -0.2rem 0.5rem;
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