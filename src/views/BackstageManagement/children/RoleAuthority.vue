!<!-- RoleAuthority -->
<template>
  <div class='RoleAuthority'>
    <div class="topBox">
      <el-button style="height:40px;width:120px;" type="primary" icon="el-icon-document-checked">保存信息</el-button>
    </div>
    <div class="bottomBox">
      <el-table
        v-loading="loading"
        border
        :span-method="arraySpanMethod"
        :data="authorityArr"
        max-height="550"
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
<<<<<<< HEAD
  import {mapState,mapMutations} from 'vuex';
=======
  import {mapState} from 'vuex';
>>>>>>> lkx
  export default {
    components: {},
    data() {
      return {
        checkAll: false,
        isIndeterminate: true,
        authorityArr:[],
        loading:true
      };
    },
    computed: {
      ...mapState(['userAuthority'])
    },
    watch: {},
    methods: {
<<<<<<< HEAD
      ...mapMutations(['UPDATE_BREAD']),
=======
>>>>>>> lkx
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
        // console.log(this.authorityArr[rowIndex].status=='single' &&columnIndex)
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
    },
    created() {
      let authorityArr = [];//提供表格所需格式数组

      let showArr = ['0001','0003','000F','000I','0005','0007'];
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
      console.log(authorityArr)
      this.authorityArr = authorityArr;
    },
    mounted() {
<<<<<<< HEAD
      this.UPDATE_BREAD(['后台管理','角色功能维护']);
=======
      this.$emit('childrenBread',['角色功能维护']);
>>>>>>> lkx
      setTimeout(()=>{
        this.loading=false;
      },1000)
    }
  }
</script>
<style lang='less' scoped>
  .RoleAuthority{
<<<<<<< HEAD
    height: 100%;
=======
    height: calc(100% - 90px);
>>>>>>> lkx
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
      background-color: white;
      border-radius: 2px;
    }
  }
</style>