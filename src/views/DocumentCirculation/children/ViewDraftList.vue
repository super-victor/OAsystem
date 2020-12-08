!<!-- ViewDraftList -->
<template>
  <div class='ViewDraftList' v-loading="loading">
    <empty-box v-if="tableData.length==0"></empty-box>
    <div class="tableBox" v-else>
      <div class="buttonBox">
        <el-switch
          v-model="onlyMe"
          @change="change"
          active-text="只看自己的发文">
        </el-switch>
      </div>
      <el-table
        :data="currentTableData"
        style="width: 100%;"
        max-height="90%"
        :default-sort = "{prop: 'employeeId'}"
        stripe
        >
        <el-table-column
          prop="title"
          label="拟稿标题"
          sortable
          width="170"
          align="center">
        </el-table-column>
        <el-table-column
          prop="type"
          label="拟稿种类"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="urgent"
          label="紧急程度"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="senderDetail.name"
          label="签发人"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="subject"
          label="主题词"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="发布时间"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="80">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              trigger="hover"
              :open-delay="500"
              content="查看拟稿">
                <el-button slot="reference" type="primary" icon="el-icon-view" circle @click="checkDraftInfo(scope.row)"></el-button>
            </el-popover>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import sendFileAPI from '@/service/DocumentCirculation';
  import EmptyBox from '../component/EmptyBox';
  import {mapState,mapMutations} from 'vuex';
  export default {
    components: {
      EmptyBox
    },
    data() {
      return {
        loading:true,
        tableData:[],
        onlyMe:false,
        currentTableData:[]
      };
    },
    computed: {
      ...mapState([
        'userInfo'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      checkDraftInfo(info){
        this.$router.push(`draft-detail/${info.sendfileId}`);
      },
      change(e){
        if(e){
          this.currentTableData = this.tableData.filter(item=>item.senderId==this.userInfo.userinfo.employeeId);
        }else{
          this.currentTableData = this.tableData;
        }
      }
    },
    created() {
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','公文一览']);
      sendFileAPI.getAllPublishDocument()
      .then(res=>{
        let arr = res.object.map(item=>{
          item.title = item.title || '未命名';
          item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
          return item;
        });
        this.tableData = arr;
        this.currentTableData = arr;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取拟稿失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .ViewDraftList{
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .tableBox{
      height: 90%;
      width: 92%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      .buttonBox{
        height: 10%;
        width: 100%;
        display: flex;
        align-items: center;
      }
    }
    ::v-deep .el-button.is-circle{
      padding: 7px;
      font-size: 5px;
      margin-left: 7px;
    }
  }
</style>