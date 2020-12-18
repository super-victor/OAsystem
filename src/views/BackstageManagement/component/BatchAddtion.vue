!<!-- BatchAddtion -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      title="批量添加用户"
      :visible.sync="batchAddtionDialogVisible"
      width="10rem"
      :before-close="closeBatchAddtion">
      <div class="batchAddtion">
        <div class="templateBox">
          <div class="textBox">
            <el-tooltip class="item" effect="dark" content="系统为您提供了批量导入用户信息所需要的Excel模板，请您下载后按模板格式填入信息并上传" placement="top-start">
              <i class="el-icon-question" style="cursor:pointer;"></i>
            </el-tooltip>
            <p style="margin-left:10px;">用户表格上传模板</p>
          </div>
          <div class="downloadBox">
             <el-button type="primary" plain style="height:35px;width:100px;" @click="downloadFile">点击下载</el-button>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState} from 'vuex';
  export default {
    components: {},
    props:{
      batchAddtionDialogVisible:{
        type:Boolean,
        required:true,
        default:false
      }
    },
    data() {
      return {
        
      };
    },
    computed: {
      ...mapState(['currentIP'])
    },
    watch: {},
    methods: {
      closeBatchAddtion(){
        this.$emit('closeBatchAddtion',false);
      },
      downloadFile(){
        window.open(`http://${this.currentIP}:8080/download/oasystem/批量添加用户模板.xlsx`);
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
  .batchAddtion{
    height: 300px;
    width: 100%;
    background-color: yellow;
    .templateBox{
      height: 80px;
      width: 100%;
      display: flex;
      background-color: yellowgreen;
      .textBox{
        height: 100%;
        width: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: @primaryText;
        font-size: 18px;
        background-color: violet;
      }
      .downloadBox{
        height: 100%;
        width: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }
  }
</style>