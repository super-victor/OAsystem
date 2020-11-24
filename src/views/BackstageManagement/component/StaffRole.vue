!<!-- StaffRole -->
<template>
  <div>
    <el-dialog
      :title="name"
      :visible.sync="dialogVisible"
      width="10rem"
      @open="openBox"
      :before-close="closeStaffRole">
      <div class="StaffRole">
        <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="userInfo.checkedRole" @change="handleCheckedCitiesChange">
          <el-checkbox v-for="item in userInfo.role" :label="item" :key="item">{{item}}</el-checkbox>
        </el-checkbox-group>
      </div>
      <span slot="footer">
        <el-button @click="closeStaffRole">取 消</el-button>
        <el-button type="primary" @click="closeStaffRole">保 存</el-button>
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
        id:'',
        name:'',
        checkAll: false,
        isIndeterminate: true,
        userInfo:{
          role:['管理员','主管','经理','普通员工','产品经理'],
          checkedRole:['主管','普通员工']
        }
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeStaffRole(){
        this.$emit('closeStaffRole',false);
      },
      openBox(){
        this.id = this.userObj.employeeId;
        this.name = this.userObj.name;
        backstageAPI.employeeRolelist({
          employeeId:this.id
        })
        .then(res=>{
          console.log(res);
        })
        .catch(err=>{
          console.log(err);
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