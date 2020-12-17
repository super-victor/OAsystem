!<!-- GiveRole -->
<template>
  <div class='GiveRole' v-loading="loading">
    <div class='TheTitle'><i class="el-icon-user" style="margin-right:5px;"></i>管理员管理</div>
    <div class="topBox">
      <el-button type="primary" class="button" icon="el-icon-setting" @click="openEditRole=true">设置管理员角色</el-button>
    </div>
    <div class="bottomBox">
      <el-table
        :data="tableData"
        style="width: 100%;"
        max-height="450"
        :default-sort = "{prop: 'employeeId'}"
        stripe
        >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="用户名">
                <span>{{ props.row.username }}</span>
              </el-form-item>
              <el-form-item label="性别">
                <span>{{ getSex(props.row.sex) }}</span>
              </el-form-item>
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
              <el-form-item label="入职登记时间">
                <span>{{ getEntryTime(props.row.entryTime) }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="employeeId"
          label="工号"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="departmentName"
          label="部门"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="position"
          label="职位"
          sortable
          align="center">
        </el-table-column>
        <el-table-column 
        label="操作"
        align="center"
        width="100">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              :content="scope.row.accountNonLocked?'冻结管理员账号':'解冻管理员账号'">
              <el-button slot="reference" :style="{'backgroundColor':scope.row.accountNonLocked?'':'#9CB7F0'}" icon="el-icon-lock" circle @click="lockOrUnlockAdmin(scope.row)"></el-button>
            </el-popover>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <edit-role :staffArr="tableData" :EditRoleDialogVisible="openEditRole" @closeEditRole="closeEditRole"></edit-role>
  </div>
</template>

<script>
  import administratorAPI from '@/service/Administrator';
  import EditRole from './EditRole';
  export default {
    components: {
      EditRole
    },
    data() {
      return {
        loading:true,
        tableData:[],
        openEditRole:false
      };
    },
    computed: {},
    watch: {},
    methods: {
      getSex(sex) {
        return sex==='m'?'男':'女';
      },
      getEntryTime(entryTime){
        return new Date(+new Date(new Date(entryTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
      },
      closeEditRole(flag,arr){
        this.openEditRole = flag;
        if(arr){
          this.tableData = arr;
        }
      },
      lockOrUnlockAdmin(obj){
        let {employeeId,accountNonLocked} = obj;
        let flag;
        if(accountNonLocked) flag=1;
        else flag=0;
        administratorAPI.lockOrUnlockAdmin({
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
    },
    created() {
      
    },
    mounted() {
      administratorAPI.getAllAdmin()
      .then(res=>{
        this.tableData = res.object;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取管理员列表失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .GiveRole{
    height: 100%;
    width: 49%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .TheTitle{
      height: 50px;
      width: 150px;
      box-sizing: border-box;
      background-color: @primaryColor;
      border-bottom-right-radius: 10px;
      color: @white;
      border-right: 3px solid @correlateColor1;
      border-bottom: 3px solid @correlateColor1;
      font-size: 18px;
      line-height: 50px;
      text-align: center;
      user-select: none;
    }
    .topBox{
      height: 80px;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      .button{
        height: 50%;
        width: 7rem;
      }
    }
    .bottomBox{
      height: 470px;
      width: 90%;
      margin-left: 5%;
      ::v-deep .el-button.is-circle{
        padding: 7px;
        font-size: 5px;
        margin-left: 7px;
      }
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
    }
  }
</style>