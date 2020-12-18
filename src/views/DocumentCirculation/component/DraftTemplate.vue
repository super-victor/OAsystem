!<!-- DraftTemplate -->
<template>
  <div>
    <el-dialog
      style="margin-top:-70px;"
      title="公文头模板"
      :visible.sync="templateDialogVisible"
      width="70%"
      :before-close="closeTemplateDialog">
      <div class="templateBox">
        <div class="headerBox">
          <p class="header">师大发展有限公司文件</p>
          <div class="headerInfo">
            <p class="headerId">师大发展发 [xxxx] xx号</p>
            <p class="sender">签发人：<span class="s1">xxxx</span></p>
          </div>
        </div>
        <div class="typeBox">{{templateInfo.type}}</div>
        <div class="titleBox">
          <p class="title">xxxxxxxxx</p>
        </div>
        <div class="contentBegin">{{templateInfo.target && templateInfo.target.split(" ")[2]}}：</div>
        <div class="content">xxxxxxxxxxxxxxxxxxxxxxx</div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  
  export default {
    components: {},
    props:{
      templateDialogVisible:{
        type:Boolean,
        required:true,
        default:false
      },
      templateInfo:{
        type:Object,
        required:true
      }
    },
    data() {
      return {
        
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeTemplateDialog(){
        this.$emit('closeTemplateDialog',false);
      }
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .templateBox{
    height: 460px;
    width: 100%;
    .headerBox{
      height: 2.5rem;
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .header{
        height: 1.8rem;
        width: 100%;
        font-size: 0.98rem;
        text-align: center;
        line-height: 1.8rem;
        color: #EA3323;
        font-family:'SourceHan-S-SC24d69ea73622004';
      }
      .headerInfo{
        height: 0.5rem;
        width: 100%;
        box-sizing: border-box;
        border-bottom: 3px solid #EA3323;
        display: flex;
        justify-content: space-between;
        .headerId{
          height: 0.4rem;
          width: 5rem;
          font-size: 0.3rem;
          line-height: 0.4rem;
          color: @black;
          font-family:'klfangsong2519f0898a22004';
        }
        .sender{
          height: 0.4rem;
          width: 6rem;
          font-size: 0.3rem;
          line-height: 0.4rem;
          color: @black;
          font-family:'klfangsong2519f0898a22004';
          text-align: right;
          .s1{
            font-family:"Hiragino Sans GB";
          }
        }
      }
    }
    .typeBox{
      margin-top: 0.1rem;
      height: 0.4rem;
      width: 20%;
      font-size: 0.25rem;
      line-height: 0.4rem;
      color: @black;
      font-family:'klfangsong2519f0898a22004';
    }
    .titleBox{
      height: 2rem;
      width: 100%;
      padding-top: 1px;
      .title{
        margin-top: 0.5rem;
        height: 0.6rem;
        width: 100%;
        font-family:"Hiragino Sans GB";
        color: @black;
        font-size: 0.5rem;
        line-height: 0.6rem;
        text-align: center;
        word-break:break-all;
      }
    }
    .contentBegin{
      height: 0.5rem;
      width: 35%;
      font-size: 0.3rem;
      line-height: 0.5rem;
      color: @black;
      font-family:'klfangsong2519f0898a22004';
    }
    .content{
      padding: 0 0.5rem;
      box-sizing: border-box;
      min-height: 3rem;
      height: auto;
      width: 100%;
      margin-top: 0.2rem;
      font-size: 0.25rem;
    }
  }
</style>