!<!-- CheckDraftDetail -->
<template>
  <div class='CheckDraftDetail' v-loading="loading">
    <img src="@/assets/Document/seal.jpg" class="img" alt="">
    <div class="documentBox">
      <the-draft :fileInfo="fileInfo"></the-draft>
    </div>
    <div class="optionBox">
      <div class="optionBoxInner">
        <div class="stepBox">
          <div class="checkBox">
            <p class="title">审稿结论：</p>
            <div class="button">
               <el-button type="success" icon="el-icon-check" circle class="circleButton" :style="{'background-color':ispassed===1?'#67C23A':'#EFEFEE','border-color':ispassed===1?'#67C23A':'#EFEFEE'}" @click="ispassed=1"></el-button>
               <el-button type="danger" icon="el-icon-close" circle class="circleButton" :style="{'background-color':ispassed===0?'#F56C6C':'#EFEFEE','border-color':ispassed===0?'#F56C6C':'#EFEFEE'}" @click="ispassed=0"></el-button>
            </div>
          </div>
          <div class="textBox" v-show="ispassed!==-1">
            <p class="title">审稿意见：</p>
            <div class="text">
              <el-input
                :maxlength="300"
                :show-word-limit="true"
                rows="16"
                resize="none"
                type="textarea"
                :placeholder="ispassed===-1?'请选择审稿结论':(ispassed===1?'公文符合规定，审核通过':'请详细给出审稿未通过原因以及修改意见，以便于签发人自查修改')"
                v-model="comment">
              </el-input>
            </div>
          </div>
        </div>
        <div class="buttonBox">
          <el-button class="button" style="width:1.2rem;" icon="el-icon-back" @click="goBack">返回</el-button>
          <el-button type="primary" class="button" style="width:2.2rem;" icon="el-icon-finished" @click="submitDraft">提交审核结果</el-button>
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
        this.$router.push('/document-circulation/check-draft');
      },
      submitDraft(){
        let currentComment = "";
        if(this.ispassed===-1){
          this.$message.error('请先选择审稿结论');
          return;
        }
        if(this.comment=="" && this.ispassed===0){
          this.$message.error('请先填写审稿意见');
          return;
        }
        if(this.comment=="" && this.ispassed===1){
          currentComment = this.defaultComment;
        }else{
          currentComment = this.comment;
        }
        let text = this.ispassed===1?'确认要通过该拟稿审核申请并发布为公文吗':'确定要否决该拟稿申请并返还给签发人吗';
        this.$confirm(text, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          sendFileAPI.checkDocument({
            sendfileId:this.fileInfo.sendfileId,
            comment:currentComment,
            ispassed:this.ispassed
          })
          .then(res=>{
            this.$notify({
              title: '审核成功',
              message: '已完成对该公文拟稿的审核',
              type: 'success'
            });
            this.$router.push('/document-circulation/check-draft');
          })
          .catch(err=>{
            this.$message.error('公文审核结果提交失败');
          })
        }).catch(() => {});
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','待审详情']);
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
  .CheckDraftDetail{
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
            height: 20%;
            width: 100%;
            .title{
              height: 25%;
              width: 100%;
              color: @primaryText;
              font-size: 0.26rem;
            }
            .button{
              height: 75%;
              width: 100%;
              display: flex;
              align-items: center;
              justify-content: space-around;
              .circleButton{
                height:0.7rem;
                width:0.7rem;
                font-size:0.3rem;
              }
              ::v-deep .el-button.is-circle{
                padding: 0;
                display: flex;
                align-items: center;
                justify-content: center;
              }
            }
          }
          .textBox{
            height: 70%;
            width: 100%;
            margin-top: 0.1rem;
            .title{
              height: 12%;
              width: 100%;
              color: @primaryText;
              font-size: 0.26rem;
            }
            .text{
              height: 88%;
              width: 98%;
              margin-left: 1%;
              display: flex;
              align-items: flex-start;
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