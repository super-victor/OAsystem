!<!-- StaffRole -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :title="name"
      :visible.sync="dialogVisible"
      width="10rem"
      @open="openBox"
      :before-close="closeStaffRole">
      <div class="StaffRole" v-loading="loading">
        <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="userInfo.checkedRole" @change="handleCheckedCitiesChange">
          <el-checkbox v-for="item in userInfo.role" :label="item" :key="item">{{item}}</el-checkbox>
        </el-checkbox-group>
      </div>
      <span slot="footer">
        <el-button @click="closeStaffRole" :disabled="loading">取 消</el-button>
        <el-button type="primary" @click="updateStaffRole" :disabled="loading" :loading="buttonLoading">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import backstageAPI from '@/service/BackstageManagement';
  export default {
    props:{
      dialogVisible:{
        type:Boolean,
        required:true,
        default:false
      },
      userObj:{
        type:Object,
        required:true
      }
    },
    components: {},
    data() {
      return {
        buttonLoading:false,
        loading:true,
        id:'',
        name:'',
        checkAll: false,
        isIndeterminate: true,
        userInfo:{
          role:[],
          checkedRole:[]
        },
        roleList:[]
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeStaffRole(){
        this.$emit('closeStaffRole',false);
      },
      openBox(){
        this.loading = true;
        this.id = this.userObj.employeeId;
        this.name = this.userObj.name;
        backstageAPI.getEmployeeRolelist({
          employeeId:this.id
        })
        .then(res=>{
          let {role,checkedRole} = this.userInfo;
          role.splice(0,role.length);
          checkedRole.splice(0,checkedRole.length);
          let {haveRoleList,nothaveRoleList} = res.object;
          this.roleList = nothaveRoleList;
          for(let item of haveRoleList){
            checkedRole.push(item.name);
          }
          for(let item of nothaveRoleList){
            role.push(item.name);
          }
          setTimeout(() => {
            this.loading = false;
          }, 500);
        })
        .catch(err=>{
          console.log(err);
        })
      },
      updateStaffRole(){
        this.buttonLoading = true;
        backstageAPI.putEmployeeRolelist({
          employeeId:this.id,
          roleIdList:this.roleList.filter(item=>this.userInfo.checkedRole.includes(item.name)).map(item=>item.roleId)
        })
        .then(res=>{
          this.$message({
            message: '修改成功',
            type: 'success'
          });
          this.buttonLoading = false;
        })
        .catch(err=>{
          this.$message.error('修改失败');
          this.buttonLoading = false;
        })
      },
      handleCheckAllChange(val) {
        this.userInfo.checkedRole = val ? this.userInfo.role : [];
        this.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.userInfo.role.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.userInfo.role.length;
      }
    },
    created() {
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  .StaffRole{
    min-height: 100px;
    width: 9rem;
    padding: 20px 10px;
    box-sizing: border-box;
    ::v-deep .el-checkbox-group{
      margin-top: 30px;
    }
    ::v-deep .el-checkbox{
      margin-top: 10px;
    }
  }
</style>