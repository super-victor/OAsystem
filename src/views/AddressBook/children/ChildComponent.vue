<template>
  <div class='addressBook'>
    <div class="msgBox">
      <!--  筛选  column-key="sex"
              :filters="[{text: '男', value: '男'}, {text: '女', value: '女'}]"
            :filter-method="filterSexHandler" -->
      <div class="content" style="width:100%">
        <!-- 部门筛选 -->
        <div class="chooses">
          <div class="choose">
            <el-input placeholder="请输入部门名称搜索" prefix-icon="el-icon-search" v-model="department" @input = "filter()" @blur="message()">
            </el-input>
          </div>
          <el-card class="box-card" >
            <div v-for="(data,index) in departmentfilterres" :key="index" class="text item" @click="handleClick(index)" style="cursor:pointer">
              {{data}}
            </div>
          </el-card>
        </div>

        <!-- 表格 -->
        <div class="table">

          <!-- <button @click="handleClick()">test</button> -->
          <div class="search">
            <!-- 搜索框 -->
            <el-input placeholder="请输入姓名搜索" v-model="input">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <el-button type="info" icon="el-icon-edit" @click="reset">重置</el-button>
          </div>

          <el-table style="width:100%;" :data="tablefilterData"  stripe>
            <el-table-column prop="name" label="姓名" width="150px" sortable>
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="150px" sortable>
            </el-table-column>
            <el-table-column prop="phone" label="电话号码"  width="200px" sortable>
            </el-table-column>
            <el-table-column prop="email" label="邮箱"  width="250px" sortable>
            </el-table-column>
            <el-table-column prop="department.name" label="部门" width="150px" sortable>
            </el-table-column>
            <el-table-column prop="position" label="职位" width="150px" sortable>
            </el-table-column>
          </el-table>

          <!-- 分页 -->
        <div class="block" style="margin:20px 0 0 auto">
          
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
          :page-sizes="[1,5,10,15,20]"
            :current-page.sync="currentPage3" :page-size="1" layout="sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>
        </div>
        </div>
      </div>

        

    </div>
  </div>
</template>

<script>
  import dataAPI from '@/service/AddressBook';  
  import departmentAPI from '@/service/AddressBook';
  import pageAPI from '@/service/AddressBook';
  import {
    mapMutations
  } from 'vuex';
  import TheBreadcrumb from '@/components/control/TheBreadcrumb';
  export default {
    components: {
      TheBreadcrumb
    },
    data() {
      return {
        tableData: [],
        tablefilterData:[],
        sex: '',
        dpm: '',
        department: '',
        currentPage3: 1,
        state: '',
        input: '',
        isTrue: false,
        breadcrumbItem: ['示例页面1', '示例页面2'],
        departmentres:[],
        departmentfilterres:[],
        pageSize:1,
        currentPage:1,
        total:10,

      }
    },

    created() {
      pageAPI.paginationRequest({
            currentPageNum:1,
            pageSize:this.pageSize,
        }).then(res=>{
            console.log(res.object)
            this.tablefilterData = res.object.currentPageList;
            this.total = res.object.totalPageNum;
            console.log(this.total)
          }).catch(err => {
            this.$message.error('读取失败');
            console.log(err)
            
          })
      //  dataAPI.dataRequest()
      //     .then(res =>{
      //       console.log(res.object);
      //       this.tableData = res.object;
      //       this.tablefilterData = this.tableData;
      //       this.tableData.map((item,index)=>{
      //         if(item.sex == 1 || !item.sex) item.sex = '男';
      //         else item.sex = '女';
      //       })
      //     })
       departmentAPI.departmentRequest()
          .then(res => {
            // console.log(res.object);
            this.departmentres = res.object;
            this.departmentfilterres = this.departmentres;
          })
          .catch(err => {
            this.$message.error('读取失败');
            console.log(err)
          })
    },
    methods: {
      message(){
        if(!this.department) this.tablefilterData = this.tableData;
      },
      search(){
        this.tablefilterData = [];
        this.tableData.map((item,index)=>{
          if(this.input == this.tableData[index].name || this.tableData[index].name.indexOf(this.input)>-1 ) this.tablefilterData.push(item);
          else if(!this.input) this.tablefilterData=this.tableData;
        })
      },
      reset(){
        this.tablefilterData = this.tableData;
      },
      handleClick(index){
        // console.log(this.departmentfilterres[index]);
        this.department = this.departmentfilterres[index];
        this.tablefilterData = [];
        this.tableData.map((item,index2)=>{
          if(item.department.name == this.departmentfilterres[index]) this.tablefilterData.push(item);
        })
      },
        // handleClick(idx){
        //    this.tableData.map((item,index)=>{
        //      if(item.department.name == this.departmentfilterres[idx]) this.tablefilterData;
        //      else console.log(item.department);
        //    })
        // },
      filter(){
        this.departmentfilterres = this.departmentres.filter(item=>item.indexOf(this.department)>-1);
      },
      ...mapMutations([
        'MAIN_CLICK'
      ]),
      mainBoxClick() {
        this.MAIN_CLICK(false);
      },
      filterHandler(){
        const property = column['property'];
        return row[property] === value;
      },


      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      //改变页面显示条数
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pageSize = val;
        console.log(this.pageSize)
        pageAPI.paginationRequest({
            currentPageNum:this.currentPage,
            pageSize:this.pageSize,
        }).then(res=>{
            console.log(res.object)
            this.tableData = res.object.currentPageList;
            this.tablefilterData = this.tableData;
            this.total = res.object.totalPageNum;
            console.log(this.total)
          }).catch(err => {
            this.$message.error('读取失败');
            console.log(err)
            
          })
      },
      //切换页数
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
        pageAPI.paginationRequest({
            currentPageNum:this.currentPage,
            pageSize:this.pageSize,
        }).then(res=>{
            console.log(res.object)
            this.tableData = res.object.currentPageList;
            this.tablefilterData = this.tableData;
            this.total = res.object.totalPageNum;
            console.log(this.total)
          }).catch(err => {
            this.$message.error('读取失败');
            console.log(err)
            
          })
      },
    },
    mounted() {
      this.$emit('childrenBread', ['通讯录查询']);
    }
  }
</script>

<style lang='less' scoped>
  @import '../../../style/common.less';
  .content {
    height: 700px;
    width: 100%;
    overflow: hidden;
    display: flex;
    .chooses {
      .box-card{
        background:@background;
        border:none;
      }
      .choose{
        display: flex;
      }
      margin-right: 0.5rem;
    }

    .table {
      display: flex;
      flex-direction: column;
      .search {
        width: 5rem;
        margin-bottom: 0.3rem;
        display: flex;
        .el-input{
          margin-right:0.2rem;
        }
        .el-button{
          width: 180px;
        }
      }
      .block{
        align-self: flex-end;
      }
    }
  }

  .foot {
    height: 20%;
    width: 100%;
  }

  .addressBook {
    height: 100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    background-color: @background;
  }

  .msgBox {
    height: 800px;
    width: 100%;
    background-color: @background;
    font-size: 30px;

  }

  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }
</style>