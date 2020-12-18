!<!-- StaffManagement -->
<template>
  <div class='StaffManagement'>
    <div class="departmentBox">
      <div class="titleBox">
        <p class="title">部门结构</p>
      </div>
      <ul class="departmentList" v-loading="loading">
        <li class="listItem" :class="{currentlistItem:currentDepartmentName=='all'}" @click="switchDepartmentToAll">全部</li>
        <li
        class="listItem"
        v-for="item in departmentArr"
        :class="{currentlistItem:currentDepartmentName==item.name}"
        @click="switchDepartment(item)"
        :key="item.departmentId">{{item.name}}</li>
        <el-button round style="width:90%;margin:20px 0 0 5%;" icon="el-icon-setting" @click="$router.push('/backstage-management/department')">部门管理</el-button>
      </ul>
    </div>
    <div class="roleBox">
      <div class="topBox">
        <div class="searchBox">
          <el-input
            placeholder="搜索"
            prefix-icon="el-icon-search"
            v-model="searchText">
          </el-input>
        </div>
        <div class="importBox">
            <el-button style="width:150px;height:40px;" icon="el-icon-folder-opened" @click="batchAddtionDialogVisible=true;">批量添加用户</el-button>
            <el-button style="width:120px;height:40px;margin-left:20px;" type="primary" icon="el-icon-plus" @click="openAddStaff=true">添加用户</el-button>
        </div>
      </div>
      <div class="bottomBox">
        <div style="height:480px;width:100%">
          <el-table
            v-loading="loading"
            :data="currentTableData"
            style="width: 100%;"
            height="480"
            :default-sort = "{prop: 'employeeId'}"
            stripe
            >
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="电话号码">
                    <span>{{ props.row.phone }}</span>
                  </el-form-item>
                  <el-form-item label="电子邮箱">
                    <span>{{ props.row.email }}</span>
                  </el-form-item>
                  <el-form-item label="身份证号">
                    <span>{{ props.row.idCard }}</span>
                  </el-form-item>
                  <el-form-item label="出身日期">
                    <span>{{ props.row.birthday }}</span>
                  </el-form-item>
                  <el-form-item label="家庭住址">
                    <span>{{ props.row.homeAddress }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column
              prop="employeeId"
              label="工号"
              sortable
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              sortable
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="username"
              label="用户名"
              sortable
              width="170"
              align="center">
            </el-table-column>
            <el-table-column
              prop="sex"
              label="性别"
              sortable
              width="80"
              align="center"
              :formatter="getSex">
            </el-table-column>
            <el-table-column
              prop="departmentName"
              label="部门"
              sortable
              width="100"
              align="center">
            </el-table-column>
            <el-table-column
              prop="position"
              label="职位"
              sortable
              width="120"
              align="center">
            </el-table-column>
            <el-table-column
              prop="entryTime"
              label="入职登记时间"
              :formatter="getEntryTime"
              sortable
              align="center">
            </el-table-column>
            <el-table-column 
            label="操作"
            align="center"
            width="150">
              <template slot-scope="scope">
                <el-popover
                  placement="top-start"
                  trigger="hover"
                  :open-delay="500"
                  :content="scope.row.accountNonLocked?'冻结用户账号':'解冻用户账号'">
                  <el-button slot="reference" :style="{'backgroundColor':scope.row.accountNonLocked?'':'#9CB7F0'}" icon="el-icon-lock" circle @click="lockOrUnlockEmployee(scope.row)"></el-button>
                </el-popover>
                <el-popover
                  placement="top-start"
                  trigger="hover"
                  :open-delay="500"
                  content="修改用户所属角色">
                  <el-button slot="reference" type="primary" icon="el-icon-edit-outline" circle @click="openStaffRoleClick(scope.row)"></el-button>
                </el-popover>
                <el-popover
                  placement="top-start"
                  trigger="hover"
                  :open-delay="500"
                  content="删除用户账号">
                <el-button slot="reference" type="danger" icon="el-icon-delete" circle @click="deleteStaff(scope.row)"></el-button>
                </el-popover>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="tableConfig">
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[10,15,20,25]"
            :page-size="currentSize"
            layout="sizes, prev, pager, next"
            :total="getTableSize">
          </el-pagination>
        </div>
      </div>
    </div>
    <staff-role :userObj="userObj" :dialogVisible="openStaffRole" @closeStaffRole="closeStaffRole"></staff-role>
    <add-staff :departmentArr="departmentArr" :addStaffDialogVisible="openAddStaff" @closeAddStaff="closeAddStaff"></add-staff>
    <batch-addtion :batchAddtionDialogVisible="batchAddtionDialogVisible" @closeBatchAddtion="closeBatchAddtion"></batch-addtion>
  </div>
</template>

<script>
  import StaffRole from '../component/StaffRole';
  import backstageAPI from '@/service/BackstageManagement';
  import AddStaff from '../component/AddStaff';
  import {mapMutations} from 'vuex';
  import BatchAddtion from '../component/BatchAddtion';
  export default {
    components: {
      StaffRole,
      AddStaff,
      BatchAddtion
    },
    data() {
      return {
        openStaffRole:false,
        openAddStaff:false,
        userObj:{},
        departmentArr:[],
        searchText:'',
        currentSize:10,
        currentPage:1,
        currentTableData:[],
        tableData: [],
        loading:true,
        staffArr:[],
        currentDepartmentName:'',
        batchAddtionDialogVisible:false
      };
    },
    computed: {
      getTableSize(){
        return this.tableData.length;
      }
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      getSex(row, column) {
        return row.sex==='m'?'男':'女';
      },
      getEntryTime(row, column){
        return new Date(+new Date(new Date(row.entryTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
      },
      //每页显示条数改变
      handleSizeChange(size){
        this.currentSize = size;
        let len = this.tableData.length;
        let currentNum = this.currentSize*this.currentPage;
        let begin = this.currentSize*(this.currentPage-1);
        let end = begin + this.currentSize;
        if(currentNum<=len) this.currentTableData = this.tableData.slice(begin,end);
        if(currentNum>len && begin<len) this.currentTableData = this.tableData.slice(begin,len);
      },
      //页数改变
      handleCurrentChange(page){
        this.currentPage = page;
        let len = this.tableData.length;
        let begin = this.currentSize*(this.currentPage-1);
        let end = begin + this.currentSize;
        this.currentTableData = this.tableData.slice(begin,end<=len?end:len);
      },
      openStaffRoleClick(obj){
        let {employeeId,name} = obj;
        this.userObj = {
          employeeId,
          name
        };
        this.openStaffRole = true;
      },
      closeStaffRole(flag){
        this.openStaffRole = flag;
      },
      closeAddStaff(flag,update){
        this.openAddStaff= flag;
        if(update) this.getStaff();
      },
      lockOrUnlockEmployee(obj){
        let {employeeId,accountNonLocked} = obj;
        let flag;
        if(accountNonLocked) flag=1;
        else flag=0;
        backstageAPI.lockOrUnlockEmployee({
          employeeId,
          isAccountLocked:flag
        })
        .then(res=>{
          obj.accountNonLocked = !accountNonLocked;
          if(flag==1){
            this.$message({
              message: '锁定成功',
              type: 'success'
            });
          }else{
            this.$message({
              message: '解锁成功',
              type: 'success'
            });
          }
        })
        .catch(err=>{
          if(flag==1){
            this.$message.error('锁定失败');
          }else{
            this.$message.error('解锁失败');
          }
        })
      },
      deleteStaff(obj){
        this.$confirm('此用户将被永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          backstageAPI.deleteStaff({
            employeeId:obj.employeeId
          })
          .then(res=>{
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getStaff();
          })
          .catch(err=>{
            this.$message.error('删除失败');
          })
        }).catch(() => {});
      },
      getDepartmentAndStaff(){
        this.loading = true;
        backstageAPI.getAllDepartment()
        .then(res=>{
          this.departmentArr = res.object;
          this.getStaff();
        })
        .catch(err=>{
          this.$message.error('部门信息获取失败');
        })
      },
      getStaff(){
        this.loading = true;
        backstageAPI.getUserList()
        .then(res=>{
          this.staffArr = res.object;
          this.filterStaff();
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('员工信息获取失败');
        })
      },
      switchDepartment(item){
        this.$router.push(item.name);
        this.currentDepartmentName = item.name;
        this.filterStaff();
      },
      switchDepartmentToAll(){
        this.$router.push('all');
        this.currentDepartmentName = 'all';
        this.filterStaff();
      },
      filterStaff(){
        let filterArr = this.currentDepartmentName=='all' ? this.staffArr : this.staffArr.filter(item=>item.departmentName==this.currentDepartmentName);
        this.tableData = filterArr;
        this.currentTableData = filterArr;
      },
      closeBatchAddtion(flag){
        this.batchAddtionDialogVisible = flag;
      }
    },
    created() {
      this.currentDepartmentName = this.$route.params.department;
      this.getDepartmentAndStaff();
    },
    mounted() {
      this.UPDATE_BREAD(['后台管理','员工管理']);
      this.currentTableData = this.tableData.slice(0,this.currentSize);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .StaffManagement{
    height: 100%;
    width: 100%;
    font-size: 30px;
    display: flex;
    align-items: center;
    .departmentBox{
      height: 100%;
      width: 3.2rem;
      min-width: 170px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .titleBox{
        height: 50px;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
        .title{
          height: 100%;
          width: 80%;
          font-size: 17px;
          font-weight: bolder;
          color: @correlateColor4;
          line-height: 50px;
          display: flex;
        }
        .title::before{
          content: '';
          height: 100%;
          display: block;
          background-color: @correlateColor4;
          width: 10px;
          margin-right: 30px;
        }
      }
      .departmentList{
        height: 600px;
        width: 100%;
        border-radius: @smallBorderRadius;
        background-color: @white;
        overflow-y: scroll;
        padding: 6px 0;
        box-sizing: border-box;
        .listItem{
          height: 40px;
          width: 100%;
          font-size: 15px;
          margin-bottom: 6px;
          line-height: 40px;
          padding-left: 40px;
          box-sizing: border-box;
          cursor: pointer;
          background-color: @white;
          transition: all .3s;
          color: @secondaryText;
        }
        .listItem:hover{
          background-color: @background;
        }
        .currentlistItem{
          background-color: @correlateColor1 !important;
          color: @white;
        }
      }
      .departmentList::-webkit-scrollbar{
        display: none;
      }
    }
    .roleBox{
      margin-left: 25px;
      height: 100%;
      width: 13.2rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .topBox{
        height: 50px;
        width: 100%;
        display: flex;
        justify-content: space-between;
        .searchBox{
          height: 100%;
          width: 30%;
        }
        .importBox{
          height: 100%;
          width: 35%;
          display: flex;
          justify-content: flex-end;
          align-items: center;
        }
      }
      .bottomBox{
        height: 600px;
        width: 100%;
        border-radius: @smallBorderRadius;
        background-color: @white;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        .demo-table-expand {
          font-size: 0;
        }
        .demo-table-expand label {
          width: 90px;
          color: #99a9bf;
        }
        .demo-table-expand .el-form-item {
          margin-right: 0;
          margin-bottom: 0;
          width: 50%;
        }
        .tableConfig{
          height: 47px;
          width: 100%;
          display: flex;
          justify-content: flex-end;
        }
        ::v-deep .el-button.is-circle{
          padding: 7px;
          font-size: 5px;
          margin-left: 7px;
        }
      }
    }
  }
</style>