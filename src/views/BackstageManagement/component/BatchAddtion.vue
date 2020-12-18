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
        <div class="uploadBox">
          <el-upload
          action="/file/upload"
          :show-file-list="false"
          :before-upload="beforeUpload"
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList"
          :http-request="upload">
            <div class="uploadButton">
              <el-button style="width:8rem;height:40px;" icon="el-icon-paperclip" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">文件大小不能超过 <span style="color:#ee3f4d;">5M</span> ，且只能为 <span style="color:#ee3f4d;">.xlsx</span> 格式</div>
            </div>
          </el-upload>
        </div>
      </div>
      <el-dialog
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :show-close="false"
        :visible.sync="loadingDialogVisible"
        width="50%"
        top="37vh">
        <div class="loadingBox">
          <el-progress :text-inside="true" :stroke-width="26" :percentage="progress"></el-progress>
          <p style="width:90%;margin:10px 0 0 5%;text-align:center;color:#3D60AD;">{{progress==100?'正在添加员工,请稍后...':'文件上传中...'}}</p>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex';
  import backstageAPI from '@/service/BackstageManagement';
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
        fileList:[],
        loadingDialogVisible:false,
        progress:0,
      };
    },
    computed: {
      ...mapState(['currentIP'])
    },
    watch: {
      '$store.state.userUploadProgress':function(val){
        this.progress = val;
      }
    },
    methods: {
      ...mapMutations(['UPLOAD_USER_PROGRESS']),
      closeBatchAddtion(){
        this.$emit('closeBatchAddtion',false);
      },
      downloadFile(){
        window.open(`http://${this.currentIP}:8080/download/oasystem/批量添加用户模板.xlsx`);
      },
      beforeUpload(f){
        let arr = f.name.split('.');
        if(arr[arr.length-1]!='xlsx'){
          this.$message.error('文件类型需为.xlsx');
          return false;
        }
        if(f.size/1024/1024>5){
          this.$message.error('附件大小不能超过10M');
          return false;
        }
      },
      handleExceed(){
        this.$message.warning('文件数量超出最大限制');
      },
      upload(f){
        this.UPLOAD_USER_PROGRESS(0);
        this.loadingDialogVisible = true;
        backstageAPI.uploadFile({
          multipartFile:f.file
        })
        .then(res=>{
          this.loadingDialogVisible = false;
          this.fileList.splice(0,this.fileList.length);
          this.$message({
            message: '批量导入员工信息成功',
            type: 'success'
          });
          this.$emit('closeBatchAddtion',false,1);
        })
        .catch(err=>{
          let arr = err.toString().split(":");
          let text = arr[arr.length-1];
          this.loadingDialogVisible = false;
          this.fileList.splice(0,this.fileList.length);
          this.$message.error(text);
        })
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
    .templateBox{
      height: 80px;
      width: 100%;
      display: flex;
      .textBox{
        height: 100%;
        width: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: @primaryText;
        font-size: 18px;
      }
      .downloadBox{
        height: 100%;
        width: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }
    .uploadBox{
      height: 220px;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      .uploadButton{
        height: 70px;
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
      }
    }
  }
  .loadingBox{
    height: 60px;
    width: 100%;
  }
</style>