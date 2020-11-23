<!-- ProFile -->
<template>
  <div class='ProFile' @click="mainBoxClick">
    <the-breadcrumb :breadcrumbItem="breadcrumbItem"></the-breadcrumb>
    <div class="profileBox">
      <div class="imgBox">
        <img src="@/assets/user.png" alt="" class="img">
        <div class="nameBox">
          <p class="name">{{userInfo.name}}</p>
          <p class="position">{{userInfo.position}}</p>
        </div>
      </div>
      <div class="infoBox">
        <el-form :model="formData" style="margin-top:50px;" :rules="rules" ref="formData" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户名" style="height:60px;width:280px">
                <el-input :disabled="true" :value="userInfo.username"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="姓名" style="height:60px;width:280px">
                <el-input :disabled="true" :value="userInfo.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="性别" style="height:60px;width:150px">
                <el-input :disabled="true" :value="userInfo.sex=='f'?'女':'男'"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="身份证号" style="height:60px;width:280px">
                <el-input :disabled="true" :value="userInfo.idCard"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="生日" style="height:60px;width:260px">
                <el-input :disabled="true" :value="userInfo.birthday"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="部门" style="height:60px;width:260px">
                <el-input :disabled="true" :value="userInfo.department"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="职位" style="height:80px;width:260px">
                <el-input :disabled="true" :value="userInfo.position"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="电子邮箱" prop="email" style="height:60px;width:500px">
            <el-input v-model="formData.email"></el-input>
          </el-form-item>
          <el-form-item label="家庭住址" prop="homeAddress" style="height:60px;width:500px">
            <el-input v-model="formData.homeAddress"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone" style="height:60px;width:500px">
            <el-input v-model="formData.phone"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="button" @click="submitForm('formData')">保存信息</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import TheBreadcrumb from '@/components/control/TheBreadcrumb';
  import {mapState,mapMutations} from 'vuex';
  import loginAPI from '@/service/userLogin';
  export default {
    components: {
      TheBreadcrumb
    },
    data() {
      return {
        breadcrumbItem:['个人信息'],
        formData:{},
        rules:{
          email:[
            { required: true, message: '请输入电子邮箱', trigger: 'blur' },
            { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '邮箱格式有误' }
          ],
          homeAddress:[
            { required: true, message: '请输入家庭住址', trigger: 'blur' }
          ],
          phone:[
            { required: true, message: '请输入电话号码', trigger: 'blur' },
            { pattern: /^[1][3,4,5,7,8][0-9]{9}$/, message: '电话号码格式有误' }
          ]
        }
      };
    },
    computed: {
      ...mapState([
        'userInfo'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations([
        'MAIN_CLICK',
        'UPDATE_USERINFO'
      ]),
      mainBoxClick(){
        this.MAIN_CLICK(false);
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let {email,homeAddress,phone} = this.formData;
            if(email == this.userInfo.email && homeAddress == this.userInfo.homeAddress && phone == this.userInfo.phone){
              this.$message({
                message: '您尚未对信息进行修改',
                type: 'warning'
              });
              return;
            }
            let obj = {
              email,
              homeAddress,
              phone
            }
            loginAPI.updateProfile(obj)
            .then(res=>{
              this.UPDATE_USERINFO(obj);
              this.$message({
                message: '信息修改成功',
                type: 'success'
              });
            })
            .catch(err=>{
              this.$message.error('信息修改失败');
            })
          } else {
            return false;
          }
        });
      }
    },
    created() {
      let {email,homeAddress,phone} = this .userInfo;
      this.formData = {
        email,
        homeAddress,
        phone
      };
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .ProFile{
    height: 100%;//这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部子路由组件高度不超过该组件高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @lighterBackground;
    .profileBox{
      height: calc(100% - 100px);
      width: 100%;
      display: flex;
      .imgBox{
        height: 4rem;
        width: 4rem;
        min-height: 200px;
        min-width: 200px;
        background-color: @white;
        box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
        border-radius: @baseBorderRadius;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-around;
        .img{
          height: 60%;
          width: 60%;
        }
        .nameBox{
          height: 30%;
          width: 80%;
          .name{
            height: 50%;
            width: 100%;
            font-size: 0.4rem;
            color: @primaryText;
            text-align: center;
          }
          .position{
            height: 30%;
            width: 100%;
            font-size: 0.2rem;
            color: @secondaryText;
            text-align: center;
          }
        }
      }
      .infoBox{
        margin-left: 0.5rem;
        height: 100%;
        width: 12rem;
        min-width: 600px;
        background-color: @white;
        box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
        border-radius: @baseBorderRadius;
        .button{
          margin-top: 30px;
          height: 40px;
          width: 100px;
        }
      }
    }
  }
</style>