!<!-- Header -->
<template>
  <div class='header' @mousewheel.prevent>
    <div class="logoBox">
      <img src="@/assets/logo.png" class="logo" alt="" @click="toHome">
    </div>
    <div class="headerBox">
      <p class="name">协同办公平台</p>
      <div class="itemBox">
        <the-header-box>
          <img src="@/assets/message.png" class="userImg" alt="" @click="toMessage">
        </the-header-box>
        <el-dropdown @command="handleCommand">
          <the-header-box>
            <img src="@/assets/user.png" class="userImg2" alt="">
          </the-header-box>
          <el-dropdown-menu slot="dropdown">
            <div class="userTop">
              <img src="@/assets/user.png" class="userImgInside" alt="">
              <div class="titleBox">
                <p class="name">Hi, {{userInfo.userinfo && userInfo.userinfo.username}} !</p>
                <p class="id">ID: {{userInfo.userinfo && userInfo.userinfo.employeeId}}</p>
              </div>
            </div>
            <el-dropdown-item
              v-for="item in userItem"
              :key="item.index"
              :command="item">
              <div class="item">
                <p class="text">{{item.text}}</p>
                <img :src="require(`@/assets/header/${item.src}.png`)" class="img" alt="">
              </div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
  import TheHeaderBox from '@/components/control/TheHeaderBox';
  import {mapState,mapMutations} from 'vuex';
  export default {
    components: {
      TheHeaderBox
    },
    data() {
      return {
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
            route:'/setting'
          },
          {
            index:3,
            text:'退出登录',
            src:'logout',
            route:'/login'
          }
        ]
      };
    },
    computed: {
      ...mapState([
        'userInfo',
        'userAuthorityTemple',
        'asideMenuTemple'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations(['GET_TOKEN','RESET_PAGE_PERMISSIONS','RESET_ASIDE_MENU','GET_USERINFO','UPDATE_USERROLE']),
      handleCommand(command) {
        if(command.index===1){
          this.$router.push(command.route);
        }else if(command.index===2){

        }else{
          this.$confirm('确认要退出登录吗', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'error'
          }).then(() => {
            this.GET_TOKEN(null);
            this.GET_USERINFO({});
            this.UPDATE_USERROLE('');
            this.RESET_PAGE_PERMISSIONS(this.userAuthorityTemple);
            this.RESET_ASIDE_MENU(this.asideMenuTemple);
            this.$router.push(command.route);
          }).catch(() => {});
        }
      },
      toMessage(){
        this.$router.push('/message');
      },
      toHome(){
        this.$router.push('/');
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
        cursor: pointer;
      }
    }
    .headerBox{
      height: 100%;
      width: calc(100vw - 120px);
      min-width: 880px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding-right: 25px;
      box-sizing: border-box;
      position: relative;
      .name{
        height: 37px;
        width: 200px;
        margin-left: 20px;
        font-size: 22px;
        line-height: 39px;
        color: #2C3059;
        user-select: none;
      }
      .itemBox{
        height: 100%;
        width: 60%;
        display: flex;
        align-items: center;
        justify-content: flex-end;
      }
      .userImg{
        height: 28px;
        width: 28px;
      }
      .userImg2{
        height: 35px;
        width: 35px;
      }
    }
  }
  ::v-deep .el-dropdown-menu__item{
    width: 240px;
    height: 50px;
  }
  ::v-deep .el-dropdown-menu__item--divided:before{
    width: 240px;
    margin: 0;
  }
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
  .item{
    height: 100%;
    width: 100%;
    padding: 0 25px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
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
</style>