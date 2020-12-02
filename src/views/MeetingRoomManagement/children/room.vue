<template>
  <div class='room'>
    <div class="alert">
      <!-- 弹框 -->
      <el-dialog title="会议预约" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="活动名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="活动区域" :label-width="formLabelWidth">
            <el-select v-model="form.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div class="msgBox">
      <!-- 会议室筛选 -->
      <!-- <el-tree
        :data="data"
        :props="defaultProps"
        accordion
        @node-click="handleNodeClick">
      </el-tree> -->

      <!-- 搜索筛选 -->
      <div class="head">
        <!-- 筛选 -->
        <div class="filter">
          <!-- 楼层筛选 -->
          <div class="storey">
            <el-select v-model="storey" filterable placeholder="请选择楼层">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </div>
          <!-- 状态筛选 -->
          <div class="status">
            <el-select v-model="status" filterable placeholder="请选择状态">
              <el-option v-for="item in options2" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>
        <!-- 重置筛选 -->
        <div class="reset">

        </div>
        <!-- 搜索 -->
        <div class="search">
          <el-input placeholder="请输入搜索" v-model="input">
            <i slot="prefix" class="el-input__icon el-icon-search" @click="search()" style="cursor: pointer"></i>
          </el-input>
        </div>
      </div>

      <!-- 表格 -->
      <div class="table">
        <template>
          <el-table :data="tableData" stripe style="height:550px;width: 100%;padding:0 25px">
            <el-table-column prop="meetingroomnum" label="会议室号" >
            </el-table-column>
            <el-table-column prop="status" label="状态">
            </el-table-column>
            <el-table-column prop="communicate" label="会议名称" >
            </el-table-column>
            <el-table-column prop="starttime" label="开始时间" >
            </el-table-column>
            <el-table-column prop="endtime" label="结束时间" >
            </el-table-column>
            <el-table-column prop="" label="会议预约" >
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </div>
      <!-- 分页 -->
      <div class="pagination">
        <div class="block" style="">

          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            :page-sizes="[1,5,10,15,20]" :current-page.sync="currentPage" :page-size="1"
            layout="sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
  export default {
    data() {
      return {
        options: [{
            value: '1',
            label: '1F',

          },
          {
            value: '2',
            label: '2F'
          },
          {
            value: '3',
            label: '3F'
          }
        ],
        options2: [{
            value: '1',
            label: '空闲'
          },
          {
            value: '2',
            label: '会议中'
          },
          {
            value: '3',
            label: '已预约'
          },
        ],
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        storey: '',
        status: '',
        input: '',
        total: 1,
        currentPage: 1,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        tableData: [{
            meetingroomnum: 101,
            status: "空闲",
            communicate: "",
            starttime: "",
            endtime: "",
            appointmentnum: 1
          }, {
            meetingroomnum: 102,
            status: "会议中",
            communicate: "产品设计",
            starttime: "13:00",
            endtime: "14:00",
            appointmentnum: 2
          }, {
            meetingroomnum: 103,
            status: "会议中",
            communicate: "部门设计",
            starttime: "13:50",
            endtime: "15:00",
            appointmentnum: 3
          }, {
            meetingroomnum: 104,
            status: "空闲",
            communicate: "",
            starttime: "",
            endtime: "",
            appointmentnum: 4
          },

        ]
      }
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      search() {
        console.log("receive")
      },
      handleSizeChange() {

      },
      handleCurrentChange() {

      },
      // 会议预约
      addappointment(index) {

      }
    },
    mounted() {
      this.UPDATE_BREAD(['会议室管理','房间管理'])
    }
  }
</script>

<style lang='less' scoped>
  @import '../../../style/common.less';

  .room {
    height: 40%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;

    .alert {
      ::v-deep .el-button {
        width: 98px;
        height: 40px;

      }
    }

    .msgBox {
      height: 800px;
      width: 100%;
      font-size: 30px;
      display: flex;
      flex-wrap: wrap;
      justify-content: flex-end;

      .head {
        width: 100%;
        display: flex;
        justify-content: space-between;

        .filter {
          height: 20%;
          display: flex;

          .storey {
            padding: 0 10px;
          }

          .status {
            padding: 0 10px;
          }

        }

        .search {
          padding: 0 10px;

        }
      }

      .table {
        width: 100%;
        height: 70%;
        margin-top: 20px;
        padding: 0 10px;
      }

      .pagination {
        height: 10%;
        padding: 0 10px;
      }
    }
  }

  .el-select-dropdown__item {
    padding: 0 20px;
    font-size: 14px;
  }
</style>