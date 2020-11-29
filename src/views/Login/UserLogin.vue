!<!-- UserLogin -->
<template>
  <div class='UserLogin'>
    <div class="nav">
      <img src="@/assets/logo.png" alt="" class="img">
      <p class="name">协同办公平台</p>
    </div>
    <div class="loginBox">
      <p class="title">登录</p>
      <el-input class="input" v-model="userName" placeholder="请输入用户名"></el-input>
      <el-input class="input" v-model="passWord" placeholder="请输入密码" show-password @keyup.enter.native="login"></el-input>
      <el-link class="forgetPwd" type="primary">忘记密码</el-link>
      <el-button :disabled="!flag" class="submit" type="primary" round @click="login">提交</el-button>
    </div>
  </div>
</template>

<script>
  import loginAPI from '@/service/userLogin';
  import {mapMutations} from 'vuex';
  export default {
    components: {},
    data() {
      return {
        userName:'',
        passWord:'',
        flag:false
      };
    },
    computed: {
      inputStatus(){
        return this.userName!='' && this.passWord!='';
      }
    },
    watch: {
      inputStatus(val){
        this.flag = val;
      }
    },
    methods: {
      ...mapMutations(['GET_USERINFO','UPDATE_PAGE_PERMISSIONS']),
      login(){
        if(!this.flag){
          this.$message({
            message: '请补全登录信息',
            type: 'warning'
          });
          return;
        }
        loginAPI.loginRequest({
          username:this.userName,
          password:this.passWord
        })
        .then(res=>{
          this.GET_USERINFO(res.object);
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          this.pagePermissions(res.object.usershow);
          this.$router.replace(this.$route.query.redirect);
        })
        .catch(err=>{
          this.$message.error('登录失败');
        })
      },
      //初始化页面权限
      pagePermissions(showArr){
        let showObj = {};
        for(let i=0;i<showArr.length;i++){
          showObj[showArr[i]]=true;
        }
        this.UPDATE_PAGE_PERMISSIONS(showObj);
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
  .UserLogin{
    height: 100vh;
    width: 100vw;
    background-color:@background;
    display: flex;
    align-items: center;
    justify-content: center;
    user-select: none;
    .nav{
      height: 60px;
      width: 100%;
      top: 0;
      position: absolute;
      box-shadow: 0px 10px 10px -10px #E7EBF2;
      background-color: @lighterBackground;
      display: flex;
      align-items: center;
      .img{
        height: 37px;
        width: 40px;
        margin-left: 30px;
      }
      .name{
        height: 37px;
        width: 200px;
        margin-left: 20px;
        font-size: 22px;
        line-height: 39px;
        color: #2C3059;
      }
    }
    .loginBox{
      height: 470px;
      width: 400px;
      background-color: @white;
      border-radius: @baseBorderRadius;
      box-shadow: 0 0.75rem 1.5rem rgba(18, 38, 63, 0.03);
      display: flex;
      flex-direction: column;
      align-items: center;
      box-sizing: border-box;
      padding: 60px 25px;
      cursor: default !important;
      .title{
        height: 30px;
        width: 80%;
        letter-spacing: 8px;
        font-size: 20px;
        text-align: center;
        line-height: 30px;
        font-weight: bold;
        color: @primaryText;
      }
      .input{
        height: 30px;
        width: 80%;
        margin-top: 40px;
      }
      .forgetPwd{
        margin: 30px 0 0 225px;
      }
      .submit{
        margin-top: 84px;
        width: 130px;
      }
    }
  }
</style>