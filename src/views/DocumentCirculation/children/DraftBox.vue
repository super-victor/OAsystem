!<!-- DraftBox -->
<template>
  <div style="height:100%;width:100%;" v-loading="loading">
    <div class="titleBox">
        <p class="back" @click="back"><i class="el-icon-arrow-left" style="margin-right:5px;"></i>返回</p>
        <p class="title"><i class="el-icon-document" style="margin-right:5px;"></i>{{name}}<span style="font-size:15px;margin-left:10px;font-weight:500;color:#606266;">{{time}}</span></p>
    </div>
    <div class='DraftBox'>
      <el-tabs type="border-card" v-model="theLabel">
        <el-tab-pane label="拟稿表单" name="拟稿表单">
          <div class="tableBox">
            <el-form :model="formData" style="margin-top:50px;" :rules="rules" ref="formData" label-width="100px">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="发文人" style="height:60px;width:5rem;">
                    <el-input :disabled="true" :value="userInfo.userinfo.name"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="发文单位" style="height:60px;width:5rem;">
                    <el-input :disabled="true" :value="userInfo.userinfo.departmentName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="紧急程度" prop="urgent" style="height:60px;width:5rem;">
                    <el-select v-model="formData.urgent" placeholder="请选择紧急程度">
                      <el-option label="正常" value="正常"></el-option>
                      <el-option label="重要" value="重要"></el-option>
                      <el-option label="紧急" value="紧急"></el-option>
                      <el-option label="特急" value="特急"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="公文种类" prop="type" style="height:60px;width:5rem;">
                  <el-select v-model="formData.type" placeholder="请选择公文种类">
                    <el-option
                      v-for="item in typeList"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name"
                      style="width:9.5rem;min-width:660px;">
                      <span style="float: left;">{{ item.name }}</span>
                      <span style="float: right; color: #8492a6; font-size: 13px;">{{ item.describe }}</span>
                    </el-option>
                  </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="16">
                  <el-form-item label="标题" prop="title" style="height:60px;width:9.05rem;">
                    <el-input v-model="formData.title" placeholder="请填写公文标题"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="审查人" prop="censorId" style="height:60px;width:5rem;">
                <el-select v-model="formData.censorId" placeholder="请选择审查者">
                    <el-option
                      v-for="item in censorList"
                      :key="item.employeeId"
                      :label="item.name"
                      :value="item.employeeId"
                      style="width:300px;height:50px;line-height:50px;">
                      <img :src="item.sex=='m'?require('../../../assets/Document/m.png'):require('../../../assets/Document/f.png')" style="height:30px;width:30px;float:left;margin-top:10px;" alt="">
                      <span style="float:left;margin-left:10px;font-size:12px;color:#303133;">{{item.name}}</span>
                      <span style="float:right;font-size:12px;color:#3D60AD;">{{item.departmentName}} <span style="color:#578BFA;">{{item.position}}</span></span>
                    </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="发布范围" prop="isPublic" style="height:auto;width:14.45rem;">
                <el-radio-group v-model="rangeLabel">
                  <el-radio-button label="全体"></el-radio-button>
                  <el-radio-button label="成员"></el-radio-button>
                </el-radio-group>
                <div class="receiveBox" v-show="rangeLabel=='成员'">
                  <el-tag
                    style="margin-right:10px;"
                    v-for="tag in staffArr"
                    :key="tag.name"
                    closable
                    @close="closeStaffTag(tag)"
                    :disable-transitions="true">
                    {{tag.name}}
                  </el-tag>
                  <el-button size="medium" icon="el-icon-plus" @click="chooseStaffDialogVisible=true">选择成员</el-button>
                </div>
              </el-form-item>
            <el-form-item label="主题词" prop="subject" style="height:auto;width:8rem;">
              <div class="subjectBox">
                <el-tag
                  style="margin-right:10px;"
                  v-for="tag in formData.subjectList"
                  :key="tag"
                  closable
                  @close="closeSubjectTag(tag)">
                  {{tag}}
                </el-tag>
                <div v-show="inputAllVisible">
                  <el-input
                    style="width: 130px;"
                    v-if="inputVisible"
                    v-model="inputValue"
                    ref="saveTagInput"
                    size="medium"
                    @keyup.enter.native="handleInputConfirm"
                    @blur="handleInputConfirm"
                  >
                  </el-input>
                  <el-button v-else size="medium" icon="el-icon-plus" @click="showInput">添加主题词</el-button>
                </div>
              </div>
            </el-form-item>
              <el-form-item label="正文" prop="content" style="height:60px;width:500px">
                <el-link type="primary" style="margin-left:10px;" @click="theLabel = '正文'">前往编辑正文</el-link>
              </el-form-item>
              <el-form-item label="附件" style="height:60px;width:500px" v-if="uploadFlag">
                <el-link type="primary" style="margin-left:10px;" @click="theLabel = '附件'">前往编辑附件</el-link>
              </el-form-item>
              <el-form-item label="备注" prop="remark" style="height:150px;width:14.45rem;">
                <el-input
                  type="textarea"
                  placeholder="输入备注(选填，仅审稿人可见)"
                  v-model="formData.remark"
                  maxlength="500"
                  show-word-limit
                  :rows="5"
                >
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="warning" class="button" icon="el-icon-upload" :loading="saveDocumentLoading" @click="saveDocument" v-show="updateFlag">保存到草稿箱</el-button>
                <el-button type="primary" class="button" icon="el-icon-s-promotion" :loading="saveDocumentLoading" @click="submitForm('formData')" style="margin-left:40px;" v-show="submitFlag">发布公文</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-tab-pane>
        <el-tab-pane label="正文" name="正文">
          <div id="text" class="textBox"></div>
        </el-tab-pane>
        <el-tab-pane label="附件" name="附件" v-if="uploadFlag">
          <div class="annexBox">
              <el-upload
              action="/file/upload"
              :before-remove="beforeRemove"
              :before-upload="beforeUpload"
              :limit="10"
              :on-preview="handleClick"
              :on-exceed="handleExceed"
              :file-list="fileList"
              :http-request="upload">
                <div class="uploadBox">
                  <el-button style="width:8rem;" icon="el-icon-paperclip" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">单个附件大小不能超过 <span style="color:#ee3f4d;">10M</span> ，最多上传 <span style="color:#ee3f4d;">10个</span> 附件</div>
                </div>
              </el-upload>
          </div>
        </el-tab-pane>
      </el-tabs>
      <choose-staff :staffArr="staffArr" :chooseStaffDialogVisible="chooseStaffDialogVisible" @closeChooseStaff="closeChooseStaff"></choose-staff>
      <el-dialog
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :show-close="false"
        :visible.sync="loadingDialogVisible"
        width="50%"
        top="37vh">
        <div class="loadingBox">
          <el-progress :text-inside="true" :stroke-width="26" :percentage="progress"></el-progress>
          <p style="width:90%;margin:10px 0 0 5%;text-align:center;color:#3D60AD;">附件上传中...</p>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex';
  import wangEditor from 'wangeditor'
  import ChooseStaff from '../component/ChooseStaff';
  import sendFileAPI from '@/service/DocumentCirculation';
  export default {
    components: {
      ChooseStaff
    },
    data() {
      return {
        name:'',
        time:'',
        loading:true,
        saveDocumentLoading:false,
        theLabel:'拟稿表单',
        chooseStaffDialogVisible:false,
        loadingDialogVisible:false,
        staffArr:[],
        formData:{
          title:'',
          type:'',
          urgent:'',
          accessEmployeeIdList:[],
          senderId:'',
          censorId:'',
          remark:'',
          content:'',
          isPublic:'',
          subject:'',
          subjectList:[]
        },
        rules:{
          title:[
            { required: true, message: '请填写公文标题', trigger: 'blur' }
          ],
          type:[
            { required: true, message: '请选择公文种类', trigger: 'change' }
          ],
          urgent:[
            { required: true, message: '请选择紧急程度', trigger: 'change' }
          ],
          censorId:[
            { required: true, message: '请选择审查者', trigger: 'change' }
          ],
          remark:[
            { max: 500, message: '长度需在500字以内' }
          ],
          content:[
            { required: true, message: '请书写正文后再次提交', trigger: 'blur' }
          ],
          isPublic:[
            { required: true, message: '请选择发布范围后再次提交', trigger: 'blur' }
          ],
          subject:[
            { required: true, message: '至少需要一个主题词', trigger: 'blur' }
          ],
        },
        rangeLabel:'',
        censorList:[],
        typeList:[{
          name:'决议',
          describe:'适用于会议讨论通过的重大决策事项'
        },{
          name:'决定',
          describe:'适用于对重要事项作出决策和部署、奖惩有关单位和人员、变更或者撤销下级机关不适当的决定事项'
        },{
          name:'命令',
          describe:'适用于公布行政法规和规章、宣布施行重大强制性措施、批准授予和晋升衔级、嘉奖有关单位和人员'
        },{
          name:'公报',
          describe:'适用于公布重要决定或者重大事项'
        },{
          name:'公告',
          describe:'适用于向国内外宣布重要事项或者法定事项'
        },{
          name:'通告',
          describe:'适用于在一定范围内公布应当遵守或者周知的事项'
        },{
          name:'意见',
          describe:'适用于对重要问题提出见解和处理办法'
        },{
          name:'通知',
          describe:'适用于发布、传达要求下级机关执行和有关单位周知或者执行的事项，批转、转发公文'
        },{
          name:'通报',
          describe:'适用于表彰先进、批评错误、传达重要精神和告知重要情况'
        },{
          name:'请示',
          describe:'适用于向上级机关请求指示、批准'
        },{
          name:'批复',
          describe:'适用于答复下级机关请示事项'
        },{
          name:'议案',
          describe:'适用于各级人民政府按照法律程序向同级人民代表大会或者人民代表大会常务委员会提请审议事项'
        },{
          name:'函',
          describe:'适用于不相隶属机关之间商洽工作、询问和答复问题、请求批准和答复审批事项'
        },{
          name:'纪要',
          describe:'适用于记载会议主要情况和议定事项'
        },{
          name:'公示',
          describe:'党政机关、企事业单位、社会团体等事先预告群众周知，用以征询意见、改善工作的一种应用文文体'
        }],
        editor: null,
        fileList:[],
        progress:0,
        fileFlag:false,
        firstUpload:true,
        canOut:false,
        inputVisible:false,
        inputAllVisible:false,
        inputValue:'',
        updateFlag:false,
        submitFlag:false,
        uploadFlag:false,
        deleteFileFlag:false
      };
    },
    computed: {
      ...mapState(['userInfo'])
    },
    watch: {
      rangeLabel:function(val){
        if(val=='全体'){
          this.formData.isPublic = 1;
        }else{
          if(val=='成员' && this.formData.accessEmployeeIdList.length!=0){
            this.formData.isPublic = 0;
          }else{
            this.formData.isPublic = '';
          }
        }
      },
      '$store.state.userUploadProgress':function(val){
        this.progress = val;
      }
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD','UPLOAD_USER_PROGRESS']),
      closeSubjectTag(tag){
        this.formData.subjectList.splice(this.formData.subjectList.indexOf(tag), 1);
        this.formData.subject = '';
        for(let item of this.formData.subjectList){
          this.formData.subject += item+' ';
        }
        this.formData.subject = this.formData.subject.substring(0,this.formData.subject.length-1);
        this.inputAllVisible = true;
      },
      showInput(){
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleInputConfirm(){
        let inputValue = this.inputValue;
        if (inputValue) {
          if(inputValue.length>10){
            this.$message.error('主题词不能超过10个字符大小');
          }else{
            if(this.formData.subjectList.some(item=>item==inputValue)){
              this.$message.error('主题词不能重复');
            }else{
              this.formData.subjectList.push(inputValue);
              this.formData.subject = '';
              for(let item of this.formData.subjectList){
                this.formData.subject += item+' ';
              }
              this.formData.subject = this.formData.subject.substring(0,this.formData.subject.length-1);
              if(this.formData.subjectList.length>=3) this.inputAllVisible = false;
              this.inputVisible = false;
              this.inputValue = '';
            }
          }
        }else{
          this.inputVisible = false;
          this.inputValue = '';
        }
      },
      back(){
        this.$router.push('/document-circulation/query-draft');
      },
      getEditorData() {
        // 通过代码获取编辑器内容
        let data = this.editor.txt.html()
        alert(data)
      },
      closeChooseStaff(flag,arr){
        this.chooseStaffDialogVisible = flag;
        if(arr){
          this.staffArr = arr;
          this.formData.accessEmployeeIdList = arr.map(item=>item.employeeId);
          if(this.rangeLabel == '成员' && this.formData.accessEmployeeIdList.length!=0){
            this.formData.isPublic = 0;
          }else{
            this.formData.isPublic = '';
          }
        }
      },
      closeStaffTag(tag){
        this.staffArr = this.staffArr.filter(item=>item.name!=tag.name);
        this.formData.accessEmployeeIdList = this.formData.accessEmployeeIdList.filter(item=>item!=tag.employeeId);
        if(this.formData.accessEmployeeIdList.length==0){
          this.formData.isPublic = '';
        }
      },
      saveDocument(){
        this.saveDocumentLoading = true;
        if(this.formData.isPublic==='') this.formData.isPublic = -1;
        sendFileAPI.updateDocument(this.formData)
        .then(res=>{
          this.$notify({
            title: '保存成功',
            message: '全部信息已为您保存到草稿箱',
            type: 'success'
          });
          this.saveDocumentLoading = false;
        })
        .catch(err=>{
          this.$message.error('保存失败');
          this.saveDocumentLoading = false;
        })
        this.formData.isPublic = '';
      },
      submitForm(formName){
        this.$refs[formName].validate((valid) => {
          if(valid){
            this.saveDocumentLoading = true;
            if(this.formData.isPublic==='') this.formData.isPublic = -1;
            sendFileAPI.updateDocument(this.formData)
            .then(res=>{
              this.$notify({
                title: '保存成功',
                message: '全部信息已为您保存到草稿箱',
                type: 'success'
              });
              this.saveDocumentLoading = false;
              this.canOut = true;
              this.$router.push(`/document-circulation/confirm-draft/${this.formData.sendfileId}`);
            })
            .catch(err=>{
              this.$message.error('保存失败');
              this.saveDocumentLoading = false;
            })
            this.formData.isPublic = '';
          }else{
            return false;
          }
        });
      },
      //文件上传
      beforeUpload(f){
        if(f.size/1024/1024>10){
          this.fileFlag = true;
          this.$message.error('附件大小不能超过10M');
          return false;
        }
        if(this.fileList.map(item=>item.name).includes(f.name)){
          this.fileFlag = true;
          this.$message.error('该文件与已上传附件名称重复，请修改文件名后上传');
          return false;
        }
      },
      handleExceed(){
        this.$message.warning('附件数量超出最大限制');
      },
      beforeRemove(f,fileList){
        if(this.fileFlag){
          this.fileFlag = false;
          return true;
        }
        if(!this.deleteFileFlag){
          this.$message.error('暂无权限');
          return false;
        }
        return new Promise((resolve,reject)=>{
          this.$confirm('确认删除此附件吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'error'
          }).then(() => {
            sendFileAPI.deleteFile({
              annexUrl:f.url,
              sendfileId:this.formData.sendfileId
            })
            .then(res=>{
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.fileList = this.fileList.filter(item=>item.url!=f.url);
              resolve();
            })
            .catch(err=>{
              this.$message.error('删除失败');
              reject();
            })
          }).catch(() => {reject()});
        })
      },
      handleClick(f){
        // window.open(`http://192.168.31.116:8080/download${f.url}`);
      },
      upload(f){
        this.UPLOAD_USER_PROGRESS(0);
        this.loadingDialogVisible = true;
        sendFileAPI.uploadFile({
          multipartFile:f.file,
          sendfileId:this.formData.sendfileId
        })
        .then(res=>{
          this.loadingDialogVisible = false;
          this.fileList.push({
            name:f.file.name,
            url:res.object
          });
          this.$message({
            message: '上传附件成功',
            type: 'success'
          });
          if(this.firstUpload){
            this.$notify({
              title: '保存成功',
              message: '附件已为您保存到草稿箱',
              type: 'success'
            });
            this.firstUpload = false;
          }
        })
        .catch(err=>{
          this.loadingDialogVisible = false;
          this.fileList.splice(0,this.fileList.length);
          this.$message.error('上传附件失败');
        })
      }
      //文件上传
    },
    created() {
      let role = this.$authority.getPageAuthority('documentcirculation','querydraft').role;
      if(role['002E'].own) this.updateFlag = true;
      if(role['002F'].own) this.submitFlag = true;
      if(role['002B'].own) this.uploadFlag = true;
      if(role['002C'].own) this.deleteFileFlag = true;
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','拟稿详情']);
      const editor = new wangEditor(`#text`)
      // 配置 onchange 回调函数，将数据同步到 vue 中
      editor.config.onchange = (newHtml) => {
        this.formData.content = newHtml;
      }
      // 创建编辑器
      editor.create()
      this.editor = editor
      window.onbeforeunload = function (e) {
          e = e || window.event;
          if (e) {
              e.returnValue = '关闭提示';
          }
          return "浏览器关闭！";
      };
      sendFileAPI.getAllCensor()
      .then(res=>{
        this.censorList = res.object;
        sendFileAPI.getDocument({
          sendfileId:this.$route.params.sendfileId
        })
        .then(res=>{
          this.formData = res.object;
          let {title,updateTime,isPublic,accessEmployeeIdList,content,subject} = res.object;
          if(subject){
            this.formData.subjectList = subject.split(' ');
            if(this.formData.subjectList.length<3){
              this.inputAllVisible = true;
            }
          }
          editor.txt.html(content);//设置正文
          this.staffArr = accessEmployeeIdList;
          this.formData.accessEmployeeIdList = this.staffArr.map(item=>item.employeeId);
          this.rangeLabel = isPublic==1?'全体':(this.rangeLabel = isPublic==0?'成员':'');
          if(isPublic==-1){
            this.formData.isPublic = '';
          }
          this.name = (title==''?'未命名':title);
          this.time = new Date(+new Date(new Date(updateTime).toJSON()) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
          for(let item in this.formData){
            if(this.formData[item]==null) this.formData[item]='';
          }
          for(let item of this.formData.annuxUrlList){
            this.fileList.push({
              name:item.split('/')[2],
              url:item
            })
          }
          this.$message({
            message: '获取拟稿详情成功',
            type: 'success'
          });
          this.loading = false;
        })
        .catch(err=>{
          this.$message.error('获取拟稿详情失败');
        })
      })
      .catch(err=>{
        this.$message.error('获取审查人失败');
      })
    },
    beforeDestroy() {
      // 调用销毁 API 对当前编辑器实例进行销毁
      this.editor.destroy()
      this.editor = null
      window.onbeforeunload = null
    },
    beforeRouteLeave(to, from, next) {
      if(this.canOut){
        next(true);
      }else{
        this.$confirm('确认离开吗？系统将不会保存您尚未保存的内容', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          next(true);
        }).catch(() => {next(false)});
      }
    },
  }
</script>
<style lang='less' scoped>
  .titleBox{
    height: 7%;
    width: 800px;
    display: flex;
    justify-content: space-between;
    .back{
      height: 70%;
      width: 70px;
      font-size: 17px;
      line-height: 35px;
      color:#606266;
      cursor: pointer;
    }
    .title{
      height: 100%;
      width: 720px;
      font-size: 20px;
      line-height: 35px;
      color: #303133;
      font-weight: bolder;
    }
  }
  .DraftBox{
    height: 93%;
    width: 100%;
    background-color: white;
    font-size: 30px;
    position: relative;
    ::v-deep .el-tabs--border-card{
      border-radius: 4px;
      overflow: hidden;
    }
    ::v-deep .el-tabs__nav{
      margin-top: -6px;
    }
    ::v-deep .el-tabs__nav-scroll{
      background-color:#EBEEF5;
    }
    ::v-deep .el-tabs--border-card>.el-tabs__content{
      height: calc(93vh - 220px);
      overflow-y: scroll;
    }
    ::v-deep .el-tabs--border-card>.el-tabs__content::-webkit-scrollbar{
      display: none;
    }
    .tableBox{
      height: 100%;
      width: 100%;
      .receiveBox{
        padding: 20px;
        box-sizing: border-box;
        min-height: 38px;
        width: auto;
        border: 1px solid #DCDFE6;
        border-radius: 4px;
        margin-top: 20px;
      }
      .subjectBox{
        padding: 20px;
        box-sizing: border-box;
        min-height: 38px;
        width: auto;
        border: 1px solid #DCDFE6;
        border-radius: 4px;
      }
    }
    .textBox{
      height: 100%;
      width: 100%;
      font-size: 15px;
    }
    ::v-deep .w-e-toolbar{
      z-index: 99 !important;
    }
    ::v-deep .w-e-text-container{
      height: calc(93vh - 265px) !important;
      z-index: 98 !important;
    }
    .annexBox{
      height: 100%;
      width: 100%;
      ::v-deep .el-upload{
        width: 100%;
      }
      ::v-deep .el-upload-list__item-name [class^=el-icon]{
        display: flex;
        align-items: center;
      }
      ::v-deep .el-upload-list__item .el-icon-close{
        font-size: 20px;
        top: 10px;
      }
      ::v-deep .el-upload-list__item .el-icon-upload-success{
        font-size: 20px;
        margin-top: 10px;
      }
      ::v-deep .el-upload-list__item-name{
        height: 40px;
        display: flex;
        align-items: center;
      }
      .uploadBox{
        height: 70px;
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
      }
    }
    .loadingBox{
      height: 60px;
      width: 100%;
    }
  }
</style>