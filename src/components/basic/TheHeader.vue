!<!-- Header -->
<template>
  <div class='header' @mousewheel.prevent>
    <div class="logoBox">
      <img src="@/assets/logo.png" class="logo" alt="">
    </div>
    <div class="headerBox">
      <div class="messageBox">
        <el-button class="button" @click="routeTo('/message')">消息</el-button>
      </div>
      <the-header-box @clickItem="openUserBox">
        <img src="@/assets/user.png" class="userImg" alt="">
        <div class="userBox" :class="{'userBoxClick':userBoxFlag}">
          <div class="userTop">
            <img src="@/assets/user.png" class="userImgInside" alt="">
            <div class="titleBox">
              <p class="name">Hi, {{userInfo.username}}!</p>
              <p class="id">ID: {{userInfo.employeeId}}</p>
            </div>
          </div>
          <div class="userBottom">
            <div 
            class="item"
            @click.stop="routeTo(item.route)"
            v-for="item in userItem"
            :key="item.index">
              <p class="text">{{item.text}}</p>
              <img :src="require(`@/assets/header/${item.src}.png`)" class="img" alt="">
            </div>
          </div>
        </div>
      </the-header-box>
    </div>
  </div>
</template>

<script>
  import TheHeaderBox from '@/components/control/TheHeaderBox';
  import {mapState} from 'vuex';
  export default {
    components: {
      TheHeaderBox
    },
    data() {
      return {
        userBoxFlag:false,
        userItem:[
          {
            index:1,
            text:'个人信息',
            src:'profile',
            route:'/profile'
          },
          {
            index:2,
            text:'设置',
            src:'setting',
            route:'/psetting'
          },
          {
            index:3,
            text:'退出登录',
            src:'logout',
            route:null
          }
        ]
      };
    },
    computed: {
      ...mapState([
        'userInfo'
      ])
    },
    watch: {},
    methods: {
      openUserBox(){
        this.userBoxFlag = !this.userBoxFlag;
      },
      routeTo(route){
        this.userBoxFlag = false;
        if(route) this.$router.push(route);
        else console.log(111)
        
      }
    },
    created() {
    },
    mounted() {
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .header{
    height: 100%;
    background-color: @white;
    box-shadow: 0 1px 1px 0 #E7EBF2;
    transform: translate3d(0, 0, 0);
    display: flex;
    justify-content: space-between;
    .logoBox{
      height: 100%;
      width: 120px;
      display: flex;
      align-items: center;
      justify-content: center;
      .logo{
        height: 32px;
        width: 37px;
      }
    }
    .headerBox{
      height: 100%;
      width: calc(100vw - 120px);
      min-width: 880px;
      display: flex;
      align-items: center;
      justify-content: flex-end;
      padding-right: 25px;
      box-sizing: border-box;
      position: relative;
      .messageBox{
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        margin-right: 10px;
        .button {
          height: 30px;
          width: 50px;
        }
      }
      .userImg{
        height: 35px;
        width: 35px;
      }
      .userBox{
        transition: all .2s;
        height: 200px;
        width: 240px;
        position: fixed;
        top: 66px;
        right: 30px;
        background-color: @white;
        box-shadow: 1px 0 4px 0 #E7EBF2;
        box-shadow: @baseBorderRadius;
        cursor: auto !important;
        visibility: hidden;
        opacity: 0;
        .userTop{
          height: 65px;
          width: 100%;
          box-sizing: border-box;
          background-color: #FDFBFF;
          border-bottom: 1px solid #e9ecef;
          display: flex;
          align-items: center;
          justify-content: center;
          .userImgInside{
            height: 39px;
            width: 39px;
          }
          .titleBox{
            margin-left: 15px;
            height: 39px;
            width: 153.5px;
            .name{
              height: 23px;
              width: 100%;
              font-size: 13px;
              font-weight: bolder;
              line-height: 23px;
              color: @primaryText;
            }
            .id{
              height: 16px;
              width: 100%;
              font-size: 9px;
              line-height: 16px;
              color: @secondaryText;
            }
          }
        }
        .userBottom{
          height: 135px;
          width: 100%;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          .item{
            height: 40px;
            width: 100%;
            padding: 0 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-sizing: border-box;
            cursor: pointer;
            .text{
              height: 20px;
              width: 150px;
              font-size: 13px;
              line-height: 20px;
              color: @primaryText;
            }
            .img{
              height: 20px;
              width: 20px;
            }
          }
          .item:hover{
            background-color: @background;
          }
        }
      }
      .userBoxClick{
        top: 60px;
        opacity: 100;
        visibility: visible;
      }
    }
  }
</style>