<template>
  <div class='addressBook' v-show="getFlag1">
    <div class="msgBox">
      <div class="content">
        <div class="total">
          <!-- 部门筛选 -->
          <div class="chooses" v-show="getFlag2">
            <div class="choose">
              <el-input placeholder="请输入部门名称搜索" v-model="department" @input="filter()"
                @blur="message()">
              </el-input>
            </div>
            <el-card class="box-card" v-loading="loading2">
              <p style="font-size:13px;padding-bottom:5px;border-bottom:1px dashed #EBEEF5;text-align:center">部门列表</p>
              <div v-for="(data,index) in departmentfilterres" :key="index" class="text item"
                @click="handleClick(index)" :style="{'color':getcolor && getcolor === index+1?'#5383EC':'#000000'}"
                @mouseenter="getcolor = index+1 " @mouseleave=" getcolor = null">
                {{data}}
              </div>
            </el-card>
            
          </div>

          <!-- 表格 -->
          <div class="table">
            <div class="search">
              <!-- 搜索框 -->
              <el-input placeholder="请输入姓名搜索" v-model="input">
              </el-input>
              
              <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
              <el-button type="info" icon="el-icon-edit" @click="reset">重置</el-button>
            </div>
            <!-- 表格内容 -->
            <el-table class="el_table" style="width:100%;" :data="tablefilterData" stripe v-loading ="loading" v-show="getFlag3">
              <el-table-column prop="name" label="姓名" width="120px" min-width="80px" sortable>
              </el-table-column>
              <el-table-column prop="sex" label="性别" width="100px" min-width="80px" sortable>
              </el-table-column>
              <el-table-column prop="phone" label="电话号码" min-width="80px" sortable>
              </el-table-column>
              <el-table-column prop="email" label="邮箱" width="220px" min-width="80px" sortable>
              </el-table-column>
              <el-table-column prop="department.name" width="150px" label="部门" min-width="80px" sortable>
              </el-table-column>
              <el-table-column prop="position" label="职位" min-width="80px" sortable>
              </el-table-column>
            </el-table>
          </div>

          <!-- 分页 -->
        </div>
        <div class="block" style="">
          <div class="block_page">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
              :page-sizes="[5,10,15,20]" :current-page.sync="currentPage3" :page-size="1"
              layout="sizes, prev, pager, next, jumper" :total="total">
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
        tablefilterData: [],
        sex: '',
        department: '',
        currentPage3: 1,
        state: '',
        input: '',
        isTrue: false,
        breadcrumbItem: ['示例页面1', '示例页面2'],
        departmentres: [],
        departmentfilterres: [],
        pageSize: 5,
        currentPage: 1,
        total: 1,
        getcolor: "",
        getpagesize: [],
        loading:true,
        loading2:true,
        getFlag1:false,
        getFlag2:false,
        getFlag3:false
      }
    },
    created() {
      let role = this.$authority.getPageAuthority('addressbook').role;
      if(role['000E'].own) this.getFlag1 = true; //获取所有员工的通讯录
      if(role['000F'].own) this.getFlag2 = true; //获取所有部门的部门名称
      if(role['000G'].own) this.getFlag3 = true; //分页获取员工通讯录
  
      // 初始页面展示表格
      if(this.getFlag3){
          pageAPI.paginationRequest({
          currentPageNum: 1,
          pageSize: 5,
        }).then(res => {
          this.total = res.object.totalPageNum * this.pageSize;
          this.tablefilterData = res.object.currentPageList;
          this.loading = false;
        }).catch(err => {
          if(err.toString() !='Error: 权限认证错误') this.$message.error('读取失败');
        })
      }
      //获取总数据
      if(this.getFlag1){
        dataAPI.dataRequest()
        .then(res => {
           this.tableData = res.object;
        }).catch(err => {
          if(err.toString() !='Error: 权限认证错误') this.$message.error('读取失败');
        })
      }

      //初始页面左侧部门展示数据
      if(this.getFlag2){
         departmentAPI.departmentRequest()
        .then(res => {
          this.loading2 = false,
          this.departmentres = res.object;
          this.departmentfilterres = this.departmentres;

        })
        .catch(err => {
          if(err.toString() !='Error: 权限认证错误') this.$message.error('读取失败');
        }) 
      }
      
      
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      ...mapMutations([
        'MAIN_CLICK'
      ]),
      mainBoxClick() {
        this.MAIN_CLICK(false);
      },
     

      //搜索框
      search() {
        if(this.input){
           this.tablefilterData = [];
         this.tableData.map((item, index) => {
           if ((this.input == this.tableData[index].name || this.tableData[index].name.indexOf(this.input) > -1)
           && (this.department == this.tableData[index].department.name || !this.department) && this.tablefilterData.length < this.pageSize) 
           {
             this.tablefilterData.push(item);
             this.currentPage = 1;
           }
           else if (!this.input && !this.department)  {
             this.loading = true;
             pageAPI.paginationRequest({
             currentPageNum: 1,
             pageSize: this.pageSize,
            }).then(res => {
              this.tablefilterData = res.object.currentPageList;
              this.loading = false;
            }).catch(err => {
              this.$message.error('读取失败');
            })
           }
           else if(!this.input && this.department == this.tableData[index].department.name && this.tablefilterData.length < this.pageSize )
           {
             this.currentPage = 1;
             this.tablefilterData.push(item);
           } 
           })
        }
      },
      //重置
      reset() {
        if(this.input || this.department){
           this.input = null;
         this.department = null;
         this.loading = true
         pageAPI.paginationRequest({
        currentPageNum: 1,
        pageSize: 5,
      }).then(res => {
         this.total = res.object.totalPageNum * this.pageSize;
        this.tablefilterData = res.object.currentPageList;
        this.loading = false;
      }).catch(err => {
        this.$message.error('读取失败');
      })
        }
      },
      //点击左侧部门
      handleClick(idx) {
        this.currentPage = 1;
         this.department = this.departmentfilterres[idx]; 
        this.tablefilterData = [];
        this.tableData.map((item, index) => {
          if (item.department.name == this.department && 
          (this.input == this.tableData[index].name || this.tableData[index].name.indexOf(this.input) > -1 || !this.input)
          && this.tablefilterData.length < this.pageSize) this.tablefilterData.push(item),this.currentPage = 1;

        })
      },

      //部门搜索框过滤
      filter() {
         this.departmentfilterres = this.departmentres.filter(item => item.indexOf(this.department) > -1);
      },
      //改变页面显示条数
      handleSizeChange(val) {
         this.department = null;
         this.input = null;
         this.loading = true;
         this.currentPage = 1;
         this.pageSize = val;
        pageAPI.paginationRequest({
          currentPageNum: this.currentPage,
          pageSize: this.pageSize,
        }).then(res => {
           this.tablefilterData = res.object.currentPageList;
           this.loading = false;
        }).catch(err => {
          this.$message.error('读取失败');
        })
      },
      //切换页码
      handleCurrentChange(val) {
        this.tablefilterData = [];
        this.getpagesize = [];
        this.currentPage = val;
        this.tableData.map((item, index) => {
          if(!this.input && !this.department){
            this.getpagesize.push(item);
          }
          else if((this.input == this.tableData[index].name || this.tableData[index].name.indexOf(this.input) > -1) && !this.department){
            this.getpagesize.push(item);
          }
          else if(!this.input && item.department.name == this.department){
            this.getpagesize.push(item);
          }
          else if(item.department.name == this.department && (this.input == this.tableData[index].name || this.tableData[index].name.indexOf(this.input) > -1)){
            this.getpagesize.push(item);
          }
        })
        if(this.getpagesize) {
          for(var i =0 ; i<this.pageSize ; i++){
            if(this.getpagesize[(val-1)*this.pageSize+i]){
            this.tablefilterData.push(this.getpagesize[(val-1)*this.pageSize+i])
            }
          }
        };
      },
    },
     mounted() {
        this.UPDATE_BREAD(['通讯录','通讯录查询'])
      },
  }
</script>

<style lang='less' scoped>
  @import '../../../style/common.less';
  .addressBook {
    height:100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    box-sizing: border-box;
    .msgBox {
      height: 100%;
      width: 100%;
      font-size: 30px;
      display: flex;
      justify-content: flex-start;
      .content {
        height: 100%;
        width: 100%;
        overflow: hidden;
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-end;
        .total {
          width:100%;
          display: flex;
          height: 600px;
          .chooses {
            width: 4rem;
            box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
            border-radius: @baseBorderRadius;
            .choose {
              display: flex;
                height:10%;
            }
          ::v-deep .box-card {
              height:90%;
              cursor: pointer;
              box-shadow: none;
              border:none;
              .text {
                font-size: 15px;
              
              }
              .item {
                padding: 18px 0;
                margin-top: 5px;
                text-align: center;
                border-bottom:1px solid #EBEEF5
              }
            }
            margin-right: 0.5rem;
          }
          .table {
            width: 12rem;
            display: flex;
            flex-direction: column;
            .search {
              width: 5rem;
              margin-bottom: 0.25rem;
              display: flex;
              box-shadow: 0 0 13px 10px rgba(82, 63, 105, 0.05);
              border-radius: @baseBorderRadius;
              .el-input {
                margin-right: 0.2rem;
              }
              .el-button {
                width: 180px;
              }
            }
            .el_table {
              box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
              border-radius: @baseBorderRadius;
            }
          }
        }
        .block {
          padding-top:20px;
        }
      }
    }
  }
</style>