!<!-- EditRole -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      title="选择成员"
      :visible.sync="EditRoleDialogVisible"
      width="15rem"
      style="margin-top:-0.7rem"
      @open="openBox"
      :before-close="closeEditRole">
      <div class="EditRole" v-loading="loading">
        <div class="departmentBox">
          <div class="titleBox">
            <p class="title">部门结构</p>
          </div>
          <ul class="departmentList">
            <li class="listItem" :class="{currentlistItem:currentDepartmentName=='all'}" @click="switchDepartmentToAll">全部</li>
            <li
            class="listItem"
            v-for="item in departmentList"
            :class="{currentlistItem:currentDepartmentName==item.name}"
            @click="switchDepartment(item)"
            :key="item.departmentId">{{item.name}}</li>
          </ul>
        </div>
        <div class="checkBox" :class="{checkBoxNodata:userInfo.staff.length==0}">
          <div v-if="userInfo.staff.length==0" class="noDataBox">
            <img src="@/assets/noData.png" alt="">
            <p class="noData">暂无职员</p>
          </div>
          <div v-else>
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group v-model="userInfo.checkedStaff" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="item in userInfo.staff" :label="item" :key="item">{{item}}</el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
      </div>
      <span slot="footer">
        <el-button @click="closeEditRole" :disabled="loading">取 消</el-button>
        <el-button type="primary" @click="update" :disabled="loading" :loading="buttonLoading">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import sendFileAPI from '@/service/DocumentCirculation';
  import backstageAPI from '@/service/BackstageManagement';
  import administratorAPI from '@/service/Administrator';
  export default {
    props:{
      EditRoleDialogVisible:{
        type:Boolean,
        required:true,
        default:false
      },
      staffArr:{//当前选择的职员列表
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
          staff:[],
          checkedStaff:[]
        },
        currentStaffList:[],//结果列表
        currentDepartmentName:'all',
        departmentList:[],//首次加载的部门列表
        staffList:[],//首次加载的职员列表
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeEditRole(){
        this.$emit('closeEditRole',false);
      },
      openBox(){
        if(this.departmentList.length==0 && this.staffList.length==0){
          this.loading = true;
          backstageAPI.getAllDepartment()
          .then(res=>{
            this.departmentList = res.object;
            sendFileAPI.getUserList()
            .then(res=>{
              this.staffList = res.object;
              this.initData();
              this.loading = false;
            })
            .catch(err=>{
              this.$message.error('获取部门和职工信息失败');
            })
          })
          .catch(err=>{
            this.$message.error('获取部门和职工信息失败');
          })
        }else{
          this.initData();
        }
      },
      initData(){
        this.currentDepartmentName = 'all';
        let staffIdArr = [];
        for(let item of this.staffArr){
          staffIdArr.push(item.employeeId);
        }
        this.currentStaffList = this.staffList.map(item=>{
          return staffIdArr.includes(item.employeeId)? {...item,flag:true} : {...item,flag:false};
        })
        this.createSelection();
      },
      handleCheckAllChange(val) {
        this.userInfo.checkedStaff = val ? this.userInfo.staff : [];
        this.isIndeterminate = false;
        for(let item of this.currentStaffList){
          if(this.currentDepartmentName=='all' || item.departmentName==this.currentDepartmentName){
            item.flag = val;
          }
        }
      },
      handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.userInfo.staff.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.userInfo.staff.length;
        for(let item of this.currentStaffList){
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
        let list = this.currentDepartmentName=='all' ? this.currentStaffList : this.currentStaffList.filter(item=>item.departmentName==this.currentDepartmentName);
        this.userInfo.staff = list.map(item=>item.name);
        this.userInfo.checkedStaff = list.filter(item=>item.flag==true).map(item=>item.name);
      },
      update(){
        this.buttonLoading = true;
        let arr = this.currentStaffList.filter(item=>item.flag==true);
        administratorAPI.editAdmin({
          employeeIdList:this.currentStaffList.filter(item=>item.flag==true).map(item=>item.employeeId)
        })
        .then(res=>{
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.buttonLoading = false;
          this.$emit('closeEditRole',false,arr);
        })
        .catch(err=>{
          this.$message.error('保存失败');
        })
      },
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  .EditRole{
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