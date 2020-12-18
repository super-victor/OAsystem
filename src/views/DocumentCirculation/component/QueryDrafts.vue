!<!-- QueryDrafts -->
<template>
  <div class='QueryDrafts' v-loading="loading">
    <p class="title"><i class="el-icon-edit" style="margin-right:5px;"></i>草稿箱</p>
    <empty-box v-if="tableData.length==0"></empty-box>
    <el-table
      v-else
      :data="tableData"
      style="width: 100%;"
      max-height="340"
      :default-sort = "{prop: 'employeeId'}"
      stripe
      >
      <el-table-column
        prop="title"
        label="草稿标题"
        sortable
        width="200"
        align="center">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="最近修改时间"
        sortable
        align="center">
      </el-table-column>
      <el-table-column
      v-if="editFlag || deleteFlag"
        label="操作"
        align="center"
        width="130">
        <template slot-scope="scope">
          <el-popover
            v-show="editFlag"
            placement="top-start"
            trigger="hover"
            :open-delay="500"
            content="编辑草稿">
              <el-button slot="reference" type="primary" icon="el-icon-edit-outline" circle @click="editDraftInfo(scope.row)"></el-button>
          </el-popover>
          <el-popover
            v-show="deleteFlag"
            placement="top-start"
            trigger="hover"
            :open-delay="500"
            content="删除草稿">
              <el-button slot="reference" type="danger" icon="el-icon-delete" circle @click="deleteDraft(scope.row)"></el-button>
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
        editFlag:false,
        deleteFlag:false
      };
    },
    computed: {},
    watch: {},
    methods: {
      editDraftInfo(info){
        this.$router.push(`draft-details/${info.sendfileId}`);
      },
      deleteDraft(info){
        this.$confirm('确认要删除草稿吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          sendFileAPI.deleteDocument({
            sendfileId:info.sendfileId
          })
          .then(res=>{
            this.$notify({
              title: '删除成功',
              message: '草稿已被成功删除',
              type: 'success'
            });
            this.getDraft();
          })
          .catch(err=>{
            this.$message.error('删除失败');
          })
        }).catch(() => {});
      },
      getDraft(){
        this.loading = true;
        sendFileAPI.getDraftbox()
        .then(res=>{
          this.tableData = res.object.map(item=>{
            item.title = item.title || '未命名';
            item.updateTime = new Date(+new Date(new Date(item.updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
            return item;
          });
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('获取草稿箱失败');
        })
      }
    },
    created() {
      let role = this.$authority.getPageAuthority('documentcirculation','querydraft').role;
      if(role['002N'].own && role['002A'].own && (role['002F'].own || role['002E'].own)) this.editFlag = true;
      if(role['002I'].own) this.deleteFlag = true;
    },
    mounted() {
      this.getDraft();
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .QueryDrafts{
    height: 100%;
    width: 57%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .title{
      height: 40px;
      width: 150px;
      background-color: @primaryColor;
      border-bottom-right-radius: 10px;
      color: @white;
      font-size: 18px;
      border-right: 3px solid @correlateColor1;
      border-bottom: 3px solid @correlateColor1;
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