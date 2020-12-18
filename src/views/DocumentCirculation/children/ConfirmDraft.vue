!<!-- ConfirmDraft -->
<template>
  <div class='ConfirmDraft' v-loading="loading">
    <img src="@/assets/Document/seal.jpg" class="img" alt="">
    <div class="documentBox">
      <the-draft :fileInfo="fileInfo"></the-draft>
    </div>
    <div class="optionBox">
      <div class="optionBoxInner">
        <div class="stepBox">
          <el-steps :space="1000" :active="1" direction="vertical" finish-status="success">
            <el-step title="编写拟稿" description="已完成对公文拟稿的编写"></el-step>
            <el-step title="确认拟稿" description="系统为您生成了公文模板，以便于您对拟稿进行确认，您可以在确认无误后提交审核，也可以返回拟稿详情再次对拟稿进行修改"></el-step>
            <el-step title="提交审核" description="向审稿人提交公文拟稿，由审稿人审核决定是否予以发布，如审核通过，您的公文将正式发布，否则，您的公文会进入[未过审拟稿]区域，您可以阅读审稿人对不予通过所给出的理由，以及对该拟稿进行修改"></el-step>
            <el-step title="公文发布" description="该公文的所有接收者会收到新公文的消息提示，并可在[公文流转]-[公文一览]中查看公文"></el-step>
          </el-steps>
        </div>
        <div class="buttonBox">
          <el-button class="button" icon="el-icon-refresh-left" @click="goBack">返回修改</el-button>
          <el-button type="primary" class="button" icon="el-icon-document-checked" @click="submitDraft">确认提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import sendFileAPI from '@/service/DocumentCirculation';
  import TheDraft from '../component/TheDraft';
  import {mapMutations} from 'vuex';
  export default {
    components: {
      TheDraft
    },
    data() {
      return {
        loading:true,
        fileInfo:{}
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      goBack(){
        this.$router.push(`/document-circulation/draft-details/${this.fileInfo.sendfileId}`);
      },
      submitDraft(){
        this.$confirm('确认要提交公文拟稿吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          sendFileAPI.commitDocument({
            sendfileId:this.fileInfo.sendfileId
          })
          .then(res=>{
            this.$notify({
              title: '提交成功',
              message: '请等待审稿人审核',
              type: 'success'
            });
            this.$router.push(`/document-circulation/check-pending-draft/${this.fileInfo.sendfileId}`);
          })
          .catch(err=>{
            this.$message.error('公文拟稿提交失败');
          })
        }).catch(() => {});
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','确认拟稿']);
      sendFileAPI.getDocument({
        sendfileId:this.$route.params.sendfileId
      })
      .then(res=>{
        this.fileInfo = res.object;
        this.loading = false;
      })
      .catch(err=>{
        if(err.toString() != 'Error: 权限认证错误') this.$message.error('获取拟稿详情失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .ConfirmDraft{
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: space-between;
    .img{
      height: 3.8rem;
      width: 3.8rem;
      bottom: 0.3rem;
      position: absolute;
      pointer-events: none;
    }
    .documentBox{
      height: calc(100vh - 160px);
      width: 72%;
      overflow-y: scroll;
      padding: 5px;
      box-sizing: border-box;
    }
    .documentBox::-webkit-scrollbar{
      display: none;
    }
    .optionBox{
      padding-top: 5px;
      box-sizing: border-box;
      height: 100%;
      width: 27%;
      .optionBoxInner{
        height: 100%;
        width: 100%;
        padding-top: 1px;
        background-color: @white;
        border-radius: @baseBorderRadius;
        ::v-deep .el-step__icon.is-text{
          top: -0.85rem;
        }
        ::v-deep .el-step:last-of-type .el-step__description, .el-step:last-of-type .el-step__main{
          padding-right: 10%;
        }
        ::v-deep .el-step__title.is-wait{
          color: @regularText;
        }
        ::v-deep .el-step__description.is-wait{
          color: @regularText;
        }
        .stepBox{
          height: calc(100vh - 240px);
          width:90%;
          margin:15px 0 0 5%;
        }
        .buttonBox{
          height: 40px;
          width: 100%;
          display: flex;
          justify-content: center;
          .button{
            width: 1.7rem;
          }
        }
      }
    }
  }
</style>