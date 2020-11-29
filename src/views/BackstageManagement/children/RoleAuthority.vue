!<!-- RoleAuthority -->
<template>
  <div class='RoleAuthority'>
    <div class="topBox">
      <div class="titleBox">
        <p class="back" @click="back"><i class="el-icon-arrow-left" style="margin-right:5px;"></i>返回</p>
        <p class="title"><i class="el-icon-user" style="margin-right:5px;"></i>{{roleName}}</p>
      </div>
      <el-button :loading="buttonLoading" style="height:40px;width:120px;" type="primary" icon="el-icon-document-checked" @click="saveInfo">保存信息</el-button>
    </div>
    <div class="bottomBox">
      <el-table
        v-loading="loading"
        border
        :span-method="arraySpanMethod"
        :data="authorityArr"
        max-height="600"
        style="width: 100%;">
        <!-- <el-table-column label="路由" align="center"> -->
          <el-table-column
            prop="name"
            label="一级路由"
            align="center"
            width="180">
          </el-table-column>
          <el-table-column
            prop="childrenName"
            label="二级路由"
            align="center"
            width="180">
          </el-table-column>
        <!-- </el-table-column> -->
        <el-table-column
          label="功能点">
          <template slot-scope="scope">
            <div v-if="scope.row.totalAuthority.length==0">暂无功能</div>
            <div v-else>
              <el-checkbox :indeterminate="scope.row.isIndeterminate" v-model="scope.row.checkAll" @change="handleCheckAllChange($event,scope.row)">全选</el-checkbox>
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="scope.row.currentAuthority" @change="handleCheckedCitiesChange($event,scope.row)">
                <el-checkbox v-for="(item,key,index) in scope.row.role" :label="key" :key="item.name+' '+index">{{item.name}}</el-checkbox>
              </el-checkbox-group>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import backstageAPI from '@/service/BackstageManagement';
  import {mapState,mapMutations} from 'vuex';
  export default {
    components: {},
    data() {
      return {
        roleId:'',
        roleName:'',
        checkAll: false,
        isIndeterminate: true,
        authorityArr:[],
        loading:true,
        buttonLoading:false
      };
    },
    computed: {
      ...mapState(['userAuthority'])
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      handleCheckAllChange(val,obj) {
        obj.currentAuthority = val ? obj.totalAuthority : [];
        obj.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value,obj) {
        let checkedCount = value.length;
        obj.checkAll = checkedCount === obj.totalAuthority.length;
        obj.isIndeterminate = checkedCount > 0 && checkedCount < obj.totalAuthority.length;
      },
      arraySpanMethod({ row, column, rowIndex, columnIndex }) {
        if(this.authorityArr[rowIndex].status=='single'){
          if(columnIndex ===0){
            return [1,2];
          }else if(columnIndex === 1){
            return [0,0];
          }
        }else{
          if(columnIndex===0){
            if(rowIndex-1>=0 && this.authorityArr[rowIndex].name!=this.authorityArr[rowIndex-1].name){
              let name = this.authorityArr[rowIndex].name;
              let index = rowIndex+1;
              while(true){
                let nextName = this.authorityArr[index].name;
                if(nextName!=name) break;
                if(index==this.authorityArr.length-1){
                  index++;
                  break;
                }
                index++;
              }
              return [index-rowIndex,1];
            }else{
              return [0,0]
            }
          }
        }
      },
      back(){
        this.$router.push('role');
      },
      saveInfo(){
        this.buttonLoading = true;
        let codeNameList = [];
        for(let item of this.authorityArr){
          codeNameList = [...codeNameList,...item.currentAuthority];
        }
        backstageAPI.putRoleMenulist({
          roleId:this.roleId,
          codeList:codeNameList
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
      }
    },
    created() {
      let {id,name} = this.$route.query;
      this.roleId = id;
      this.roleName = name;
      let authorityArr = [];//提供表格所需格式数组
      backstageAPI.getMenuCodeList({
        roleId:this.roleId
      })
      .then(res=>{
        let showArr = res.object;
        let showObj = {};
        for(let i=0;i<showArr.length;i++){
          showObj[showArr[i]]=true;
        }
        let authority = this.userAuthority;
        for(let firstPage in authority){
          if(!authority[firstPage].children){
            let theRoles = authority[firstPage].role;
            let authorityItemObj = {};
            let totalAuthority = [];
            let currentAuthority = [];
            authorityItemObj['role'] = theRoles;
            authorityItemObj['name'] = authority[firstPage].name;
            authorityItemObj['childrenName'] = null;
            authorityItemObj['status'] = "single";
            authorityItemObj['isIndeterminate'] = true;
            authorityItemObj['checkAll'] = false;
            for(let role in theRoles){
              let theRole = theRoles[role];
              totalAuthority.push(role.toString());
              if(showObj[role]==true){
                currentAuthority.push(role.toString());
              }
            }
            authorityItemObj['totalAuthority'] = totalAuthority;
            authorityItemObj['currentAuthority'] = currentAuthority;
            authorityArr.push(authorityItemObj);
          }else{
            let theChildren = authority[firstPage].children;
            for(let secondPage in theChildren){
              let theRoles = theChildren[secondPage].role;
              let authorityItemObj = {};
              let totalAuthority = [];
              let currentAuthority = [];
              authorityItemObj['role'] = theRoles;
              authorityItemObj['name'] = authority[firstPage].name;
              authorityItemObj['childrenName'] = theChildren[secondPage].name;
              authorityItemObj['status'] = "multiple";
              authorityItemObj['isIndeterminate'] = true;
              authorityItemObj['checkAll'] = false;
              for(let role in theRoles){
                let theRole = theRoles[role];
                totalAuthority.push(role.toString());
                if(showObj[role]==true){
                  currentAuthority.push(role.toString());
                }
              }
              authorityItemObj['totalAuthority'] = totalAuthority;
              authorityItemObj['currentAuthority'] = currentAuthority;
              authorityArr.push(authorityItemObj);
            }
          }
        }
        this.authorityArr = authorityArr;
        this.loading=false;
      })
      .catch(err=>{
        this.$message.error('获取角色功能失败');
      })
    },
    mounted() {
      this.UPDATE_BREAD(['后台管理','角色功能维护']);
    }
  }
</script>
<style lang='less' scoped>
  .RoleAuthority{
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
      justify-content: space-between;
        .titleBox{
          height: 100%;
          width: 400px;
          display: flex;
          justify-content: space-between;
          align-items: center;
          .back{
            height: 70%;
            width: 70px;
            font-size: 17px;
            line-height: 35px;
            color:#606266;
            cursor: pointer;
          }
          .title{
            height: 100%;
            width: 320px;
            font-size: 20px;
            line-height: 50px;
            color: #303133;
            font-weight: bolder;
          }
        }
    }
    .bottomBox{
      height: 600px;
      width: 100%;
      background-color: white;
      border-radius: 2px;
    }
  }
</style>