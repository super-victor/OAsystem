!<!-- RoleManagement -->
<template>
  <div class='RoleManagement'>
    <div class="topBox">
       <el-button :loading="buttonLoading" style="height:40px;width:120px;" type="primary" icon="el-icon-plus" @click="addRole">添加角色</el-button>
    </div>
    <div class="bottomBox">
      <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%;"
        height="600"
        :default-sort = "{prop: 'employeeId'}"
        stripe
        >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item label="完整用户列表：">
                <span v-if="props.row.employeeList.length==0">暂无用户</span>
                <div v-else>
                <el-tag
                  style="padding:0 8px;margin:0 0 3px 5px;"
                  size="small"
                  v-for="item in props.row.employeeList"
                  :key=item.employeeId>{{item.name}}</el-tag>
                </div>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="角色名称"
          sortable
          width="130"
          align="center">
        </el-table-column>
        <el-table-column
          label="用户列表"
          align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.employeeList.length==0" style="font-size:5px;">无</span>
            <div v-else>
              <el-tag
              style="padding:0 8px;margin:0 0 3px 5px;"
              size="small"
              v-for="item in (scope.row.employeeList.length>12?scope.row.employeeList.slice(0,12):scope.row.employeeList)"
              :key=item.employeeId
              type="info">{{item.name}}</el-tag>
              <span v-if="scope.row.employeeList.length>12" style="color:#303133;margin-left:10px;">...</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column 
        label="操作"
        align="center"
        width="220">
          <template slot-scope="scope">
            <span v-if="scope.row.roleId==1 || scope.row.roleId==2">无</span>
            <div v-else>
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="编辑角色功能">
                  <el-button slot="reference" type="primary" icon="el-icon-edit-outline" circle @click="goRoleAuthority(scope.row)"></el-button>
              </el-popover>
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="修改角色所对应用户">
                  <el-button slot="reference" type="primary" icon="el-icon-user" circle @click="openRoleStaffClick(scope.row)"></el-button>
              </el-popover>
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="复制角色">
                  <el-button slot="reference" type="primary" icon="el-icon-document-copy" circle @click="copyRole(scope.row)"></el-button>
              </el-popover>
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="删除角色">
                  <el-button slot="reference" type="danger" icon="el-icon-delete" circle @click="deleteRole(scope.row)"></el-button>
              </el-popover>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <role-staff :departmentArr="departmentArr" :roleObj="roleObj" :dialogVisible="openRoleStaff" @closeRoleStaff="closeRoleStaff"></role-staff>
  </div>
</template>

<script>
import RoleStaff from '../component/RoleStaff';
import {mapMutations} from 'vuex';
import backstageAPI from '@/service/BackstageManagement';
  export default {
    components: {
      RoleStaff
    },
    data() {
      return {
        loading:true,
        buttonLoading:false,
        openRoleStaff:false,
        tableData: [],
        roleObj: {},
        departmentArr:[]
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      closeRoleStaff(flag,update){
        this.openRoleStaff = flag;
        if(update) this.getRole();
      },
      goRoleAuthority(info){
        let {roleId,name} = info;
        this.$router.push({path:'role-authority',query:{id:roleId,name}});
      },
      openRoleStaffClick(obj){
        this.roleObj = obj;
        this.openRoleStaff=true;
      },
      addRole(){
        this.$prompt('请输入角色名称', '添加角色', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({ value }) => {
          this.buttonLoading = true;
          backstageAPI.addRole({
            roleName:value
          })
          .then(res=>{
            this.$message({
              message: '添加成功',
              type: 'success'
            });
            this.buttonLoading = false;
            this.getRole();
          })
          .catch(err=>{
            this.$message.error('添加失败');
            this.buttonLoading = false;
          })
        })
        .catch(err=>{})
      },
      copyRole(obj){
        this.$prompt('请输入复制后的新角色名称', '复制角色', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({ value }) => {
          backstageAPI.copyRole({
            newName:value,
            copyroleId:obj.roleId
          })
          .then(res=>{
            this.$message({
              message: '复制成功',
              type: 'success'
            });
            this.getRole();
          })
          .catch(err=>{
            this.$message.error('复制失败');
          })
        })
        .catch(err=>{})
      },
      deleteRole(obj){
        this.$confirm('此角色将被永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          backstageAPI.deleteRole({
            roleId:obj.roleId
          })
          .then(res=>{
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getRole();
          })
          .catch(err=>{
            this.$message.error('删除失败，请检查该角色下是否仍存在用户');
          })
        }).catch(() => {});
      },
      getDepartmentAndRole(){
        this.loading = true;
        backstageAPI.getAllDepartment()
        .then(res=>{
          this.departmentArr = res.object;
          this.getRole();
        })
        .catch(err=>{
          this.$message.error('部门信息获取失败');
        })
      },
      getRole(){
        this.loading = true;
        backstageAPI.getAllRolesWithEmployeeList()
        .then(res=>{
          this.tableData = res.object;
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('角色获取失败');
        })
      }
    },
    created() {
      this.getDepartmentAndRole();
    },
    mounted() {
      this.UPDATE_BREAD(['后台管理','角色管理']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .RoleManagement{
    height: 100%;
    width: 100%;
    font-size: 30px;
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: space-between;
    .topBox{
      height: 50px;
      width: 100%;
      display: flex;
      justify-content: flex-end;
    }
    .bottomBox{
      height: 600px;
      width: 100%;
      background-color: @white;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      ::v-deep .el-button.is-circle{
        padding: 7px;
        font-size: 5px;
        margin-left: 7px;
      }
    }
  }
</style>