!<!-- DepartmentManagement -->
<template>
  <div class='DepartmentManagement'>
    <div class="departmentBox">
      <div class="companyBox">
        <img class="companyImg" src="@/assets/company.png" alt="">
        <p class="companyName">师大发展有限公司</p>
      </div>
      <div class="editBox">
        <div 
        class="infoBox"
        v-for="item in companyInfo"
        :key="item.title">
        <p class="titleBox">{{item.title+"："}}</p>
        <p class="textBox">{{item.text}}</p>
        </div>
      </div>
    </div>
    <div class="addBox">
      <div class="topBox">
        <el-button :loading="buttonLoading" style="height:40px;width:120px;" type="primary" icon="el-icon-plus" @click="openAddDepartment=true">新增部门</el-button>
      </div>
      <div class="bottomBox">
        <el-table
          v-loading="loading"
          :data="tableData"
          style="width: 100%;"
          max-height="600"
          :default-sort = "{prop: 'employeeId'}"
          stripe
          >
          <el-table-column
            prop="name"
            label="部门名称"
            sortable
            width="150"
            align="center">
          </el-table-column>
          <el-table-column
            prop="phone"
            label="部门电话"
            sortable
            align="center">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
            sortable
            align="center"
            width="200">
          </el-table-column>
          <el-table-column
          label="操作"
          align="center"
          width="130">
            <template slot-scope="scope">
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="编辑部门信息">
                  <el-button slot="reference" type="primary" icon="el-icon-edit-outline" circle @click="editDepartmentInfo(scope.row)"></el-button>
              </el-popover>
              <el-popover
                placement="top-start"
                trigger="hover"
                :open-delay="500"
                content="删除部门">
                  <el-button slot="reference" type="danger" icon="el-icon-delete" circle @click="deleteDepartment(scope.row)"></el-button>
              </el-popover>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <add-department :dialogVisible="openAddDepartment" @closeAddDepartment="closeAddDepartment"></add-department>
    <edit-department-info :departmentObj="departmentObj" :editDialogVisible="openEditDepartmentInfo" @closeEditDepartmentInfo="closeEditDepartmentInfo"></edit-department-info>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import backstageAPI from '@/service/BackstageManagement';
import AddDepartment from '../component/AddDepartment';
import EditDepartmentInfo from '../component/EditDepartmentInfo';
  export default {
    components: {
      AddDepartment,
      EditDepartmentInfo
    },
    data() {
      return {
        loading:true,
        buttonLoading:false,
        openAddDepartment:false,
        openEditDepartmentInfo:false,
        departmentObj:{},
        companyInfo:[{
          title:'公司名称',
          text:'师大发展'
        },{
          title:'联系电话',
          text:'13829382938'
        },{
          title:'通讯地址',
          text:'四川省成都市锦江区'
        },{
          title:'邮政编码',
          text:'610000'
        },{
          title:'传真',
          text:'+86+010+26739123'
        },{
          title:'官网',
          text:'www.sicnufazhan.com'
        },{
          title:'内网',
          text:'192.168.43.172'
        }],
        tableData:[]
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      getDepartment(){
        this.loading = true;
        backstageAPI.getAllDepartment()
        .then(res=>{
          this.tableData = res.object;
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('部门信息获取失败');
        })
      },
      closeAddDepartment(flag,update){
        this.openAddDepartment= flag;
        if(update) this.getDepartment();
      },
      editDepartmentInfo(obj){
        this.departmentObj = obj;
        this.openEditDepartmentInfo = true;
      },
      closeEditDepartmentInfo(flag,update){
        this.openEditDepartmentInfo= flag;
        if(update) this.getDepartment();
      },
      deleteDepartment(obj){
        this.$confirm('此部门将被永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          backstageAPI.deleteDepartment({
            departmentId:obj.departmentId
          })
          .then(res=>{
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getDepartment();
          })
          .catch(err=>{
            this.$message.error('删除失败，请检查该部门下是否仍存在用户');
          })
        }).catch(() => {});
      },
    },
    created() {
      this.getDepartment();
    },
    mounted() {
      this.UPDATE_BREAD(['后台管理','部门管理']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .DepartmentManagement{
    height: 100%;
    width: 100%;
    font-size: 30px;
    display: flex;
    align-items: center;
    .departmentBox{
      height: 100%;
      width: 5.2rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .companyBox{
        height: 32%;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
        display: flex;
        align-items: center;
        justify-content: center;
        .companyImg{
          height: 0.8rem;
          width: 0.8rem;
        }
        .companyName{
          height: 30px;
          width: auto;
          color: @correlateColor5;
          font-size: 0.35rem;
          line-height: 30px;
          font-weight: bolder;
          user-select: none;
          margin-left: 10px;
        }
      }
      .editBox{
        height: 65%;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
        display: flex;
        flex-direction: column;
        justify-content: center;
        .infoBox{
          height: 55px;
          width: 100%;
          display: flex;
          justify-content: space-between;
          .titleBox{
            height: 100%;
            width: 35%;
            line-height: 55px;
            font-size: 0.22rem;
            color: @regularText;
            text-align: right;
          }
          .textBox{
            height: 100%;
            width: 62%;
            line-height: 55px;
            font-size: 0.22rem;
            color: @secondaryText;
          }
        }
      }
    }
    .addBox{
      margin-left: 25px;
      height: 100%;
      width: 11.2rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .topBox{
        height: 50px;
        width: 100%;
        display: flex;
        justify-content: flex-end;
        align-items: center;
      }
      .bottomBox{
        height: 600px;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
        ::v-deep .el-button.is-circle{
          padding: 7px;
          font-size: 5px;
          margin-left: 7px;
        }
      }
    }
  }
</style>