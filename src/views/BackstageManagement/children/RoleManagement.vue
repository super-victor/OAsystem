!<!-- RoleManagement -->
<template>
  <div class='RoleManagement'>
    <div class="topBox">
       <el-button style="height:40px;width:120px;" type="primary" icon="el-icon-plus" @click="$router.push('role-authority')">添加角色</el-button>
    </div>
    <div class="bottomBox">
      <el-table
        :data="tableData"
        style="width: 100%;"
        max-height="550"
        :default-sort = "{prop: 'employeeId'}"
        stripe
        >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item label="完整用户列表：">
                <el-tag
                style="padding:0 8px;margin:0 0 3px 5px;"
                size="small"
                v-for="item in props.row.user"
                :key=item.id>{{item.name}}</el-tag>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="角色名称"
          sortable
          width="100"
          align="center">
        </el-table-column>
        <el-table-column
          label="用户列表"
          align="center">
          <template slot-scope="scope">
            <el-tag
            style="padding:0 8px;margin:0 0 3px 5px;"
            size="small"
            v-for="item in (scope.row.user.length>12?scope.row.user.slice(0,12):scope.row.user)"
            :key=item.id
            type="info">{{item.name}}</el-tag>
            <span v-if="scope.row.user.length>12" style="color:#303133;margin-left:10px;">...</span>
          </template>
        </el-table-column>
        <el-table-column 
        label="操作"
        align="center"
        width="220">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="冻结角色所有功能">
                <el-button slot="reference" class="circleButton" icon="el-icon-lock" circle @click="handleEdit(scope.$index, scope.row)"></el-button>
            </el-popover>
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="编辑角色功能">
                <el-button slot="reference" class="circleButton" type="primary" icon="el-icon-edit-outline" circle @click="handleEdit(scope.$index, scope.row)"></el-button>
            </el-popover>
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="修改角色所对应用户">
                <el-button slot="reference" class="circleButton" type="primary" icon="el-icon-user" circle @click="openRoleStaff=true"></el-button>
            </el-popover>
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="复制角色">
                <el-button slot="reference" class="circleButton" type="primary" icon="el-icon-document-copy" circle @click="handleEdit(scope.$index, scope.row)"></el-button>
            </el-popover>
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="删除角色">
                <el-button slot="reference" class="circleButton" type="danger" icon="el-icon-delete" circle @click="handleEdit(scope.$index, scope.row)"></el-button>
            </el-popover>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <role-staff :dialogVisible="openRoleStaff" @closeRoleStaff="closeRoleStaff"></role-staff>
  </div>
</template>

<script>
import RoleStaff from '../component/RoleStaff';
import {mapMutations} from 'vuex';
  export default {
    components: {
      RoleStaff
    },
    data() {
      return {
        openRoleStaff:false,
        tableData: [{
          name:"管理员",
          user:[{
            id:1,
            name:'小王12'
          },{
            id:2,
            name:'小王22'
          },{
            id:3,
            name:'小王32'
          },{
            id:4,
            name:'小王42'
          },{
            id:5,
            name:'小王5'
          },]
        }, {
          name:"主管",
          user:[{
            id:1,
            name:'小王112'
          },{
            id:2,
            name:'小王22'
          },{
            id:3,
            name:'小王32'
          },{
            id:4,
            name:'小王421'
          },{
            id:5,
            name:'小王5'
          },{
            id:1,
            name:'小王1'
          },{
            id:2,
            name:'小王23'
          },{
            id:3,
            name:'小王32'
          },{
            id:4,
            name:'小王42'
          },{
            id:5,
            name:'小王54'
          },{
            id:1,
            name:'小王134'
          },{
            id:2,
            name:'小王2443'
          },{
            id:3,
            name:'小王332'
          },{
            id:4,
            name:'小王423'
          },{
            id:5,
            name:'小王52'
          },{
            id:1,
            name:'小王112'
          },{
            id:2,
            name:'小王22'
          },{
            id:3,
            name:'小王32'
          },{
            id:4,
            name:'小王421'
          },{
            id:5,
            name:'小王5'
          },{
            id:1,
            name:'小王1'
          },{
            id:2,
            name:'小王23'
          },{
            id:3,
            name:'小王32'
          },{
            id:4,
            name:'小王42'
          },{
            id:5,
            name:'小王54'
          },{
            id:1,
            name:'小王134'
          },{
            id:2,
            name:'小王2443'
          },{
            id:3,
            name:'小王332'
          },{
            id:4,
            name:'小王423'
          },{
            id:5,
            name:'小王52'
          },]
        }, {
          name:"经理",
          user:[{
            id:1,
            name:'小王1'
          },{
            id:2,
            name:'小王2'
          },{
            id:3,
            name:'小王3'
          },{
            id:4,
            name:'小王4'
          },{
            id:5,
            name:'小王5'
          },]
        }]
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      closeRoleStaff(flag){
        this.openRoleStaff = flag;
      }
    },
    created() {
      
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
      height: 550px;
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