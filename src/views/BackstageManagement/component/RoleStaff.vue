!<!-- RoleStaff -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :title="userInfo.roleName"
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
            <li class="listItem" :class="{currentlistItem:currentDepartmentName=='all'}" @click="switchDepartmentToAll">全部</li>
            <li
            class="listItem"
            v-for="item in departmentArr"
            :class="{currentlistItem:currentDepartmentName==item.name}"
            @click="switchDepartment(item)"
            :key="item.departmentId">{{item.name}}</li>
          </ul>
        </div>
        <div class="checkBox" v-loading="loading" :class="{checkBoxNodata:userInfo.role.length==0}">
          <div v-if="userInfo.role.length==0" class="noDataBox">
            <img src="@/assets/noData.png" alt="">
            <p class="noData">暂无职员</p>
          </div>
          <div v-else>
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group v-model="userInfo.checkedRole" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="item in userInfo.role" :label="item" :key="item">{{item}}</el-checkbox>
            </el-checkbox-group>
          </div>
        </div>    
      </div>
      <span slot="footer">
        <el-button @click="closeRoleStaff" :disabled="loading">取 消</el-button>
        <el-button type="primary" @click="update" :disabled="loading" :loading="buttonLoading">保 存</el-button>
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
      roleObj:{
        type:Object,
        required:true
      },
      departmentArr:{
        type:Array,
        required:true
      }
    },
    components: {},
    data() {
      return {
        loading:true,
        buttonLoading:false,
        checkAll: false,
        isIndeterminate: true,
        userInfo:{
          id:'',
          roleName:'',
          role:[],
          checkedRole:[]
        },
        modifiableEmployeeList:[],//所有拥有该角色的非管理员员工列表
        employeeList:[],//所有非管理员员工列表
        authorityList:[],//角色权限列表
        currentDepartmentName:'all'
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeRoleStaff(){
        this.$emit('closeRoleStaff',false);
      },
      openBox(){
        this.loading = true;
        this.currentDepartmentName = 'all';
        let {roleId,name,employeeList} = this.roleObj;
        this.userInfo.id = roleId;
        this.userInfo.roleName = name;
        backstageAPI.getUserList()
        .then(res=>{
          this.employeeList = res.object;
          let employeeIdArr = [];
          for(let item of this.employeeList){
            employeeIdArr.push(item.employeeId);
          }
          this.modifiableEmployeeList = employeeList.filter(item=>employeeIdArr.includes(item.employeeId));
          let authorityEmployeeIdArr = [];
          for(let item of this.modifiableEmployeeList){
            authorityEmployeeIdArr.push(item.employeeId);
          }
          this.authorityList = this.employeeList.map(item=>{
            return authorityEmployeeIdArr.includes(item.employeeId)? {...item,flag:true} : {...item,flag:false} ;
          })
          this.createSelection();
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('加载失败');
        })
      },
      handleCheckAllChange(val) {
        this.userInfo.checkedRole = val ? this.userInfo.role : [];
        this.isIndeterminate = false;
        for(let item of this.authorityList){
          if(this.currentDepartmentName=='all' || item.departmentName==this.currentDepartmentName){
            item.flag = val;
          }
        }
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.userInfo.role.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.userInfo.role.length;
        for(let item of this.authorityList){
          if(this.currentDepartmentName=='all' || item.departmentName==this.currentDepartmentName){
            item.flag = value.includes(item.name);
          }
        }
      },
      switchDepartment(item){
        this.currentDepartmentName = item.name;
        this.createSelection();
      },
      switchDepartmentToAll(){
        this.currentDepartmentName = 'all';
        this.createSelection();
      },
      createSelection(){
        let list = this.currentDepartmentName=='all' ? this.authorityList : this.authorityList.filter(item=>item.departmentName==this.currentDepartmentName);
        this.userInfo.role = list.map(item=>item.name);
        this.userInfo.checkedRole = list.filter(item=>item.flag==true).map(item=>item.name);
      },
      update(){
        this.buttonLoading = true;
        let list = [];
        for(let item of this.authorityList){
          if(item.flag) list.push(item.employeeId);
        }
        backstageAPI.updateRoleEmployeelist({
          roleId:this.userInfo.id,
          employeeIdList:list
        })
        .then(res=>{
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          this.buttonLoading = false;
          this.$emit('closeRoleStaff',false,1);
        })
        .catch(err=>{
          this.$message.error('修改失败');
          this.buttonLoading = false;
        })
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
        .currentlistItem{
          background-color: #9CB7F0 !important;
          color: white;
        }
      }
      .departmentList::-webkit-scrollbar{
        display: none;
      }
    }
    .checkBox{
      min-height: 300px;
      width: calc(100% - 5rem);
      .noDataBox{
        height: 200px;
        width: 200px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        .noData{
          height: 20px;
          width: 100px;
          text-align: center;
          color: #C0C4CC;
        }
      }
    }
    .checkBoxNodata{
      display: flex;
      justify-content: center;
      align-items: center;
    }
    ::v-deep .el-checkbox-group{
      margin-top: 30px;
    }
    ::v-deep .el-checkbox{
      margin-top: 10px;
    }
  }
</style>