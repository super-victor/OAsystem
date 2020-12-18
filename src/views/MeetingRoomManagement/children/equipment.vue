<template>
  <div class='equipment' v-show="getFlag1">      
    <div class="alert">
      <!-- 添加弹框 -->
      <el-dialog class="dialog" title="添加设备" :visible.sync="addEquipment" > 
        <el-form class="form" ref="form" :model="form" label-width="90px" :rules="rules">
          <el-form-item label="会议室号:" style="width:100%;padding-bottom:20px;" prop="roomId" >
              <el-select v-model.number="form.roomId" placeholder="请选择">
                <el-option
                  v-for="item in MeetingroomData"
                  :key="item.value"
                  :label="item.name"
                  :value="item.meetingRoomId">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="类别:" style="width:100%;padding-bottom:20px;" prop="types">
              <el-select v-model.number="form.types" placeholder="请选择">
                <el-option
                  v-for="item in ClassifyData"
                  :key="item.value"
                  :label="item.name"
                  :value="item.equipmentClassifyId">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="是否维修" style="width:100%;padding-bottom:20px;">
            <el-switch v-model="form.isMaintain" active-value="1" inactive-value="0"></el-switch>
          </el-form-item>
           <el-form-item label="设备名称:"  style="padding-bottom:20px" prop="name">
            <el-input v-model="form.name" autocomplete="off" style="width:40%"></el-input>
          </el-form-item>
          <el-form-item label="数量："  style="padding-bottom:20px" prop="num">
            <el-input v-model.number="form.num" autocomplete="off" style="width:40%"></el-input>
          </el-form-item>
           <el-form-item label="备注:" style="width:65%">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
        
        <div slot="footer" class="dialog-footer">
          <el-button @click="addEquipment = false">取 消</el-button>
          <el-button type="primary" @click="getAddEquipment('form')">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 修改弹框 -->
      <el-dialog class="updateDialog" title="修改设备信息" :visible.sync="updateEquipment" > 
        <el-form class="updateForm" ref="updateForm" :model="updateForm" label-width="90px" :rules="rules" >
          <el-form-item label="会议室号:" style="width:100%;padding-bottom:20px;" prop="roomId">
              <el-select v-model="updateForm.roomId" placeholder="请选择">
                <el-option
                  v-for="item in MeetingroomData"
                  :key="item.value"
                  :label="item.name"
                  :value="item.meetingRoomId">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="类别:" style="width:100%;padding-bottom:20px;" prop="types">
              <el-select v-model.number="updateForm.types" placeholder="请选择">
                <el-option
                  v-for="item in ClassifyData"
                  :key="item.value"
                  :label="item.name"
                  :value="item.equipmentClassifyId">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="是否维修" style="width:100%;padding-bottom:20px;">
            <el-switch v-model="updateForm.isMaintain" active-value="1" inactive-value="0"></el-switch>
          </el-form-item>
              <el-form-item label="设备名称:"  style="padding-bottom:20px" prop="name">
            <el-input v-model="updateForm.name" autocomplete="off" style="width:40%"></el-input>
          </el-form-item>
          <el-form-item label="数量："  style="padding-bottom:20px" prop="num">
            <el-input v-model.number="updateForm.num" autocomplete="off" style="width:40%"></el-input>
          </el-form-item>
           <el-form-item label="备注:" style="width:65%">
            <el-input type="textarea" v-model="updateForm.remark"></el-input>
          </el-form-item>
        </el-form>
         <div slot="footer" class="dialog-footer">
          <el-button @click="updateEquipment = false">取 消</el-button>
          <el-button type="primary" @click="UpdateEquipment('updateForm')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="msgBox">
 
      <!-- 搜索筛选 -->
      <div class="head">
        <!-- 筛选 -->
        <div class="filter">
          <!-- 类别筛选 -->
          <div class="category" v-show="getFlag3">
            <el-select v-model="category" filterable clearable placeholder="请选择设备类别" @change="getCategory()" >
              <el-option v-for="item in ClassifyData" :key="item.value" :label="item.name" :value="item.name" > 
              </el-option>
            </el-select>
          </div>
          <!-- 会议室筛选 -->
          <div class="meetingroomnum" v-show="getFlag2">
            <el-select v-model="meetingroomnum" filterable clearable placeholder="请选择会议室号" @change="getRoomnum()">
              <el-option v-for="item in MeetingroomData" :key="item.value" :label="item.name" :value="item.name">
              </el-option>
            </el-select>
          </div>
        </div>
        <!-- 重置筛选 -->
        <div class="reset">

        </div>
        <!-- 右侧搜索和添加按钮 -->
        <div class="right">
          <!-- 搜索 -->
          <div class="search">
            <el-input placeholder="请输入搜索" v-model="input">
              <i slot="prefix" class="el-input__icon el-icon-search" @click="search()" style="cursor: pointer"></i>
            </el-input>
          </div>
          <!-- 添加设备 -->
          <div class="add" v-show="addFlag">
            <el-button type="primary" icon="el-icon-plus" @click="addEquipment = true">添加设备</el-button>
          </div>
        </div>
      </div>
      
      <!-- 表格 -->
      <div class="table">
        <template>
          <el-table :data="tableFilterData" stripe style="height:550px;width: 100%;padding:0 25px" v-loading ="loading" > 
            <el-table-column prop="meetingRoomName" label="会议室号" width="" sortable v-if="getFlag2">
            </el-table-column>
            <el-table-column prop="name" label="设备名称" width="" sortable>
            </el-table-column>
            <el-table-column prop="equipmentClassifyName" label="类型" sortable v-if="getFlag3"> 
            </el-table-column>
            <el-table-column prop="num" label="数量" sortable>
            </el-table-column>
            <el-table-column label="维护状态" width="" >
              <template slot-scope="scope">
                 <el-button :type="scope.row.isMaintain == 1?'success':'danger'" size="mini" :icon="scope.row.isMaintain ==1?'el-icon-check':'el-icon-close'" circle></el-button>
              </template>
            </el-table-column>
            <el-table-column  label="操作" width="200" align="center" v-if="updateFlag || deleteFlag">
              <template slot-scope="scope">
                <el-popover
                  placement="top-start"
                  trigger="hover"
                  :open-delay="500"
                  content="编辑" v-if="updateFlag">
                <el-button type="primary" slot="reference" icon="el-icon-edit" circle size="mini" style="margin-right:5px" @click="getUpdateMessage(scope.row)"></el-button>
              </el-popover>
              <el-popover
                  placement="top-start"
                  trigger="hover"
                  :open-delay="500"
                  content="删除" v-if="deleteFlag">
                <el-button type="danger" slot="reference" icon="el-icon-delete" circle size="mini" @click="deleteMessage(scope.row.equipmentId)"></el-button>
              </el-popover>
             
              </template>
          
     
            </el-table-column>

          </el-table>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import ClassifyAPI from '@/service/MeetingRoomManagement';
import meetingroomAPI from '@/service/MeetingRoomManagement';
import equipmentInsertAPI from '@/service/MeetingRoomManagement';
import equipmentUpdateAPI from '@/service/MeetingRoomManagement';
import equipmentAPI from '@/service/MeetingRoomManagement';
import equipmentDeleteAPI from '@/service/MeetingRoomManagement';
  import {
    mapMutations
  } from 'vuex'
  export default {
    created(){
      let role = this.$authority.getPageAuthority('meetingroommanagement','equipment').role;
      if(role['000Q'].own) this.getFlag1 = true; //获取所有的设备信息
      if(role['000S'].own) this.getFlag2 = true; //获取所有的会议室信息
      if(role['000K'].own) this.getFlag3 = true; //获取所有设备分类信息
      if(role['000P'].own) this.deleteFlag = true; //删除设备
      if(role['000N'].own) this.updateFlag = true; //修改设备 
      if(role['000M'].own) this.addFlag = true; //增加设备
      if(this.getFlag1){
        equipmentAPI.getEquipmentRequest(
      ).then(res=>{
        this.loading = false
        this.tableData = res.object ;
        this.tableFilterData = this.tableData;
          
        }).catch(err => {
          this.$message.error('读取失败');
        })
      }

      if(this.getFlag2){
        meetingroomAPI.getAllMeetingRoom(
      ).then(res=>{
          this.MeetingroomData = res.object;
        }).catch(err => {
        this.$message.error('读取失败');
      })
      }

     if(this.getFlag3){
        ClassifyAPI.getAllEquipmentClassify(
      ).then(res=>{
        this.ClassifyData = res.object;
      }).catch(err => {
        this.$message.error('读取失败');
      })
     }
    },
    data() {
      var checkNum = (rule,value,callback) =>{
        if(!value){
          return callback(new Error('数量不能为空'))
        }else callback();
      }
      return {
        getFlag1:false,
        getFlag2:false,
        getFlag3:false,
        deleteFlag:false,
        updateFlag:false,
        addFlag:false,
        roomID:'',
        category: '',
        meetingroomnum: '',
        input: '',
        total: 1,
        currentPage: 1,
        getOthers:'',
        getdisabled:true,
        addEquipment:false,
        updateEquipment:false,
        form: {
          name: '',
          types:'',
          roomId:'',
          isMaintain:0,
          num:0,
          remark:''
        },
        updateForm: {
          equipmentId:'',
          name: '',
          types:'',
          roomId:'',
          isMaintain:0,
          num:0,
          remark:''
        },
        formLabelWidth: '120px',
        MeetingroomData:[],
        tableData: [],
        tableFilterData:[],
        ClassifyData:[],
        loading:true,
         rules: {
          name: [
            { required: true, message: '请输入设备名称', trigger: 'blur' },
            { min: 0, max: 8, message: '长度在 0 到 8 个字符', trigger: 'blur' }
          ],
          roomId: [
            {  required: true, message: '请选择会议室号', trigger: 'change' }
          ],
          num: [
            {  type: 'number', required: true, message: '必须为数值', trigger: 'blur' },
            {  validator : checkNum ,trigger :'blur' }
          ],
          types: [
            {required: true, message: '请选择类别', trigger: 'change' }
          ]
        }
      }
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      //搜索
      search() {
        this.tableFilterData=[]
        this.tableData.map((item, index) => {
          if ((this.tableData[index].meetingRoomName.indexOf(this.input) > -1 
          || this.tableData[index].equipmentClassifyName.indexOf(this.input) > -1 
          || this.tableData[index].name.indexOf(this.input) > -1 ) 
          && (this.meetingroomnum == this.tableData[index].meetingRoomName||!this.meetingroomnum) 
          &&(this.category == this.tableData[index].equipmentClassifyName||!this.category))
           {
            this.tableFilterData.push(item)
          };

        })
      },


      //删除操作
      deleteMessage(Id){

    this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
               this.loading = true
         equipmentDeleteAPI.deleteEquipmentRequest({
           equipmentId:Id,
         }).then(
           
           setTimeout(() => {
           
        this.getData()
        this.$message({
            type: 'success',
            message: '删除成功!'
          });
     }, 1000))
        .catch(err => {
        this.$message.error('删除失败');
      })
          
        }).catch(() => {
         
        });
      },
      //修改操作
      getUpdateMessage(mes){
        this.updateEquipment = true;
        this.updateForm.equipmentId = mes.equipmentId;
        this.updateForm.types = mes.equipmentClassifyId;
        this.updateForm.roomId = mes.meetingRoomId;
        this.updateForm.isMaintain =String( mes.isMaintain);
        this.updateForm.name = mes.name;
        this.updateForm.remark = mes.remark;
        this.updateForm.num = mes.num;
      },
      UpdateEquipment(formName){

         this.$refs[formName].validate((valid)=>{
          if(!valid){
          }else{
        this.loading = true
        this.updateEquipment = false;
        equipmentUpdateAPI.updateEquipmentRequest({
          equipmentId:this.updateForm.equipmentId,
          equipmentClassifyId:this.updateForm.types,
          meetingRoomId:this.updateForm.roomId,
          name:this.updateForm.name,
          isMaintain:this.updateForm.isMaintain,
          remark:this.updateForm.remark,
          num:this.updateForm.num,
        }).then(
          setTimeout(() => {
            this.getData()
          }, 1000)
          
          )
        .catch(err => {
        this.$message.error('修改失败');
        })
           
          }
        })
       
      },
      
      //类别筛选
      getCategory(){
          this.tableFilterData = [];
          this.tableData.map((item, index) => {
          if (this.category == this.tableData[index].equipmentClassifyName 
          && this.category 
          && ((this.meetingroomnum == this.tableData[index].meetingRoomName && this.meetingroomnum)||!this.meetingroomnum) 
          && 
          (this.tableData[index].meetingRoomName.indexOf(this.input) > -1 
          || this.tableData[index].equipmentClassifyName.indexOf(this.input) > -1 
          || this.tableData[index].name.indexOf(this.input) > -1 )) this.tableFilterData.push(item);
          else if (!this.category) this.tableFilterData = this.tableData;
        })
        
      },

      //会议室号筛选
      getRoomnum(){
    
          this.tableFilterData = [];
          this.tableData.map((item, index) => {
          if (this.meetingroomnum == this.tableData[index].meetingRoomName 
          && this.meetingroomnum 
          && ((this.category == this.tableData[index].equipmentClassifyName && this.category)||!this.category) 
          && (this.tableData[index].meetingRoomName.indexOf(this.input) > -1 
          || this.tableData[index].equipmentClassifyName.indexOf(this.input) > -1 
          || this.tableData[index].name.indexOf(this.input) > -1 )) this.tableFilterData.push(item);
          else if (!this.meetingroomnum) this.tableFilterData = this.tableData;
        })
        

      },

      //表单确认
      getAddEquipment(formName){
  
        this.$refs[formName].validate((valid)=>{
          if(!valid){

          }else{
             this.loading = true
        this.addEquipment = false;
        equipmentInsertAPI.insertEquipmentRequest({
          equipmentClassifyId:this.form.types,
          meetingRoomId:this.form.roomId,
          name:this.form.name,
          isMaintain:this.form.isMaintain,
          remark:this.form.remark,
          num:this.form.num,
        }).then(  
          setTimeout(() => {
        this.getData()
     }, 1000))
        .catch(err => {
        this.$message.error('读取失败');
      })
        
          }
        })
       
        },

      getData(){
        equipmentAPI.getEquipmentRequest(
      ).then(res=>{
        this.loading = false;
        this.tableData = res.object ;
        this.tableFilterData = this.tableData;
        }).catch(err => {
          this.$message.error('读取失败');
        })
      }

    },
    mounted() {
      this.UPDATE_BREAD(['会议室管理', '设备管理'])
    }
  }
</script>

<style lang='less' scoped>
  @import '../../../style/common.less';

  .equipment {
    height: 100%; //这里要使用百分比进行高度的设定，如果不会超出屏幕则设置为100%，并且保证内部msgBox高度不超过exampleBox高度，不然会出现bug
    width: 100%;
    padding: 30px;
    box-sizing: border-box;
    .alert{
      .dialog{
        .form{
          display: flex;
          width:100%;
          flex-wrap:wrap;
        }
      }
      .updateDialog{
        .updateForm{
          display: flex;
          width:100%;
          flex-wrap:wrap;
        }
      }
      .dialog-footer{
        .el-button{
          width:80px;
          padding:12px;
        }
      }
    }
    .msgBox {
      height: 100px;
      width: 100%;
      font-size: 30px;
      display: flex;
      flex-wrap: wrap;
      justify-content: flex-end;

      .head {
        width: 100%;
        display: flex;
        justify-content: space-between;

        .filter {
          width:100%;
          display: flex;

          .category {
            padding: 0px;
          }

          .meetingroomnum {
            padding: 0 10px;
          }

        }

        .right {
          display: flex;

          .search {
            padding: 0 10px;
          }

          .add {
            padding: 0px;
             .el-button.el-button--primary {
            padding: 10px;
          }
          }
        }
      }

      .table {
        width: 100%;
        height: 90%;
        margin-top: 20px;
        padding: 0px;
        .el-table{
          box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
              border-radius: @baseBorderRadius;
        }
      }

      .pagination {
        height: 10%;
        padding: 0 10px;
      }
    }
  }

  .el-select-dropdown__item {
    padding: 0 20px;
    font-size: 14px;
  }
</style>