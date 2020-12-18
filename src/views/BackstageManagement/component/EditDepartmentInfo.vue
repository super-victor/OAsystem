!<!-- EditDepartmentInfo -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      title="编辑部门信息"
      :visible.sync="editDialogVisible"
      width="10rem"
      @open="openBox"
      :before-close="closeEditDepartmentInfo">
      <el-form :model="formData" style="margin-top:50px;" :rules="rules" ref="formData" label-width="100px">
        <el-form-item label="部门电话" prop="phone" style="height:60px;width:400px">
          <el-input v-model.number="formData.phone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="closeEditDepartmentInfo">取 消</el-button>
        <el-button type="primary" @click="submitForm('formData')" :loading="buttonLoading">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import backstageAPI from '@/service/BackstageManagement';
  export default {
    components: {},
    props:{
      editDialogVisible:{
        type:Boolean,
        required:true,
        default:false
      },
      departmentObj:{
        type:Object,
        required:true
      }
    },
    data() {
      return {
        buttonLoading:false,
        formData:{
          phone:''
        },
        rules:{
          phone:[
            { required: true, message: '请输入部门电话', trigger: 'blur' }
          ]
        }
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeEditDepartmentInfo(){
        this.$emit('closeEditDepartmentInfo',false);
      },
      openBox(){
        this.formData.phone = this.departmentObj.phone;
      },
      submitForm(formName){
        this.$refs[formName].validate((valid) => {
          if(valid){
            this.buttonLoading = true;
            let {phone} = this.formData;
            backstageAPI.updateDepartment({
              departmentId:this.departmentObj.departmentId,
              name:this.departmentObj.name,
              phone
            })
            .then(res=>{
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.buttonLoading = false;
              this.$emit('closeEditDepartmentInfo',false,1);
            })
            .catch(err=>{
              this.$message.error('修改失败');
              this.buttonLoading = false;
            })
          }else{
            return false;
          }
        });
      }
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>

</style>