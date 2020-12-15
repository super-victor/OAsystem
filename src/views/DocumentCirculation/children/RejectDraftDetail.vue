!<!-- RejectDraftDetail -->
<template>
  <div class='RejectDraftDetail' v-loading="loading">
    <img src="@/assets/Document/seal.jpg" class="img" alt="">
    <div class="documentBox">
      <the-draft :fileInfo="fileInfo"></the-draft>
    </div>
    <div class="optionBox">
      <div class="optionBoxInner">
        <div class="stepBox">
          <div class="checkBox">
            <p class="title">发布失败理由：</p>
            <div class="text">
              {{fileInfo.cersorOpinion}}
            </div>
          </div>
        </div>
        <div class="buttonBox">
          <el-button class="button" style="width:1rem;" icon="el-icon-back" @click="goBack">返回</el-button>
          <el-button type="primary" class="button" style="width:1.6rem;" icon="el-icon-finished" @click="backToDraft">放回草稿箱</el-button>
          <el-button type="danger" class="button" style="width:1rem;" icon="el-icon-finished" @click="deleteDraft">删除</el-button>
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
        fileInfo:{},
        ispassed:-1,
        comment:'',
        defaultComment:'公文符合规定，审核通过'
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      goBack(){
        this.$router.push('/document-circulation/query-draft');
      },
      backToDraft(){
        sendFileAPI.putDocumentNotPassIntoDraftbox({
          sendfileId:this.fileInfo.sendfileId
        })
        .then(res=>{
          this.$notify({
            title: '放回草稿箱成功',
            message: '拟稿已被成功放回草稿箱',
            type: 'success'
          });
          this.$router.push(`/document-circulation/draft-details/${this.fileInfo.sendfileId}`);
        })
        .catch(err=>{
          this.$message.error('放回草稿箱失败');
        })
      },
      deleteDraft(){
        this.$confirm('确认要删除拟稿吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          sendFileAPI.deleteDocument({
            sendfileId:this.fileInfo.sendfileId
          })
          .then(res=>{
            this.$notify({
              title: '删除成功',
              message: '拟稿已被成功删除',
              type: 'success'
            });
            this.$router.push('/document-circulation/query-draft');
          })
          .catch(err=>{
            this.$message.error('拟稿删除失败');
          })
        }).catch(() => {});
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','失败详情']);
      sendFileAPI.getDocument({
        sendfileId:this.$route.params.sendfileId
      })
      .then(res=>{
        console.log(res)
        this.fileInfo = res.object;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取拟稿详情失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .RejectDraftDetail{
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
          .checkBox{
            height: 90%;
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
            .title{
              height: 5%;
              width: 100%;
              color: @primaryText;
              font-size: 0.26rem;
            }
            .text{
              height: 90%;
              width: 98%;
              word-break:break-all;
              color: @regularText;
              font-size: 0.18rem;
              padding: 10px;
              box-sizing: border-box;
            }
          }
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