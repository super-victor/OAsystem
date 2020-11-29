!<!-- RoleStaff -->
<template>
  <div>
    <el-dialog
      :title="userInfo.name"
      :visible.sync="dialogVisible"
      width="15rem"
      @open="openBox"
      :before-close="closeRoleStaff">
      <div class="RoleStaff">
        <div class="departmentBox">
          <div class="titleBox">
            <p class="title">部门结构</p>
          </div>
          <ul class="departmentList">
            <li
            class="listItem"
            v-for="item in departmentArr"
            :key="item.id">{{item.name}}</li>
          </ul>
        </div>
        <div class="checkBox">
          <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="userInfo.checkedRole" @change="handleCheckedCitiesChange">
            <el-checkbox v-for="item in userInfo.role" :label="item" :key="item">{{item}}</el-checkbox>
          </el-checkbox-group>
        </div>    
      </div>
      <span slot="footer">
        <el-button @click="closeStaffRole">取 消</el-button>
        <el-button type="primary" @click="closeStaffRole">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  
  export default {
    props:{
      dialogVisible:{
        type:Boolean,
        required:true,
        default:false
      }
    },
    components: {},
    data() {
      return {
        checkAll: false,
        isIndeterminate: true,
        userInfo:{
          name:'管理员',
          role:['管理员','主管','经理','普通员工','管理员dw','主管23','经理12','普通员工dw'],
          checkedRole:['主管','普通员工']
        },
        departmentArr:[
          {
            id:1,
            name:'研发'
          },
          {
            id:2,
            name:'测试'
          },
          {
            id:3,
            name:'运维'
          },
          {
            id:4,
            name:'产品'
          },
          {
            id:5,
            name:'项目'
          }
        ],
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeRoleStaff(){
        this.$emit('closeRoleStaff',false);
      },
      openBox(){
        console.log("123");
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
  .RoleStaff{
    min-height: 340px;
    width: 14rem;
    padding: 20px 10px;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    .departmentBox{
      height: 300px;
      width: 4rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .titleBox{
        height: 50px;
        width: 100%;
        border-radius: 2px;
        box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
        .title{
          height: 100%;
          width: 80%;
          font-size: 17px;
          font-weight: bolder;
          color: #3D60AD;
          line-height: 50px;
          display: flex;
        }
        .title::before{
          content: '';
          height: 100%;
          display: block;
          background-color: #3D60AD;
          width: 10px;
          margin-right: 30px;
        }
      }
      .departmentList{
        height: 230px;
        width: 100%;
        border-radius: 2px;
        overflow-y: scroll;
        padding: 6px 0;
        box-sizing: border-box;
        box-shadow: 0 0 13px 0 rgba(82,63,105,0.05);
        .listItem{
          height: 40px;
          width: 100%;
          font-size: 15px;
          margin-bottom: 6px;
          line-height: 40px;
          padding-left: 40px;
          box-sizing: border-box;
          cursor: pointer;
          transition: all .3s;
          color: #909399;
        }
        .listItem:hover{
          background-color: #EFEFEE;
        }
      }
    }
    .checkBox{
      min-height: 300px;
      width: calc(100% - 5rem);
    }
    ::v-deep .el-checkbox-group{
      margin-top: 30px;
    }
    ::v-deep .el-checkbox{
      margin-top: 10px;
    }
  }
</style>