!<!-- QueryReject -->
<template>
  <div class='QueryReject' v-loading="loading">
    <p class="title"><i class="el-icon-circle-close" style="margin-right:5px;"></i>未过审拟稿</p>
    <empty-box v-if="tableData.length==0"></empty-box>
    <el-table
      v-else
      :data="tableData"
      style="width: 100%;"
      height="340"
      :default-sort = "{prop: 'employeeId'}"
      stripe
      >
      <el-table-column
        prop="title"
        label="拟稿标题"
        sortable
        width="200"
        align="center">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="回复时间"
        sortable
        align="center">
      </el-table-column>
      <el-table-column
        v-if="editFlag"
        label="操作"
        align="center"
        width="130">
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
</template>

<script>
  import sendFileAPI from '@/service/DocumentCirculation';
  import EmptyBox from './EmptyBox';
  export default {
    components: {
      EmptyBox
    },
    data() {
      return {
        loading:true,
        tableData:[],
        editFlag:false
      };
    },
    computed: {},
    watch: {},
    methods: {
      checkDraftInfo(info){
        this.$router.push(`reject-draft-detail/${info.sendfileId}`);
      }
    },
    created() {
      let role = this.$authority.getPageAuthority('documentcirculation','querydraft').role;
      if(role['002N'].own) this.editFlag = true;
    },
    mounted() {
      sendFileAPI.getDocumementNotPassed()
      .then(res=>{
        this.tableData = res.object.map(item=>{
          item.title = item.title || '未命名';
          item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
          return item;
        });
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取未过审拟稿失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .QueryReject{
    height: 100%;
    width: 42%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .title{
      height: 40px;
      width: 190px;
      background-color: @dangerColor;
      border-bottom-right-radius: 10px;
      color: @white;
      font-size: 18px;
      border-right: 3px solid #e3b4b8;
      border-bottom: 3px solid #e3b4b8;
      line-height: 40px;
      text-align: center;
      user-select: none;
    }
    ::v-deep .el-button.is-circle{
      padding: 7px;
      font-size: 5px;
      margin-left: 7px;
    }
  }
</style>