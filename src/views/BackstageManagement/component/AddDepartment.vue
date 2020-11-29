!<!-- AddDepartment -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      title="新增部门"
      :visible.sync="dialogVisible"
      width="10rem"
      :before-close="closeAddDepartment">
      <el-form :model="formData" style="margin-top:50px;" :rules="rules" ref="formData" label-width="100px">
        <el-form-item label="部门名称" prop="name" style="height:60px;width:400px">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="部门电话" prop="phone" style="height:60px;width:400px">
          <el-input v-model.number="formData.phone"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="closeAddDepartment">取 消</el-button>
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
      dialogVisible:{
        type:Boolean,
        required:true,
        default:false
      }
    },
    data() {
      return {
        buttonLoading:false,
        formData:{
          name:'',
          phone:''
        },
        rules:{
          name:[
            { required: true, message: '请输入部门名称', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
          ],
          phone:[
            { required: true, message: '请输入部门电话', trigger: 'blur' }
          ]
        }
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeAddDepartment(){
        this.$emit('closeAddDepartment',false);
      },
      submitForm(formName){
        this.$refs[formName].validate((valid) => {
          if(valid){
            this.buttonLoading = true;
            let {name,phone} = this.formData;
            backstageAPI.addDepartment({
              name,
              phone
            })
            .then(res=>{
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.buttonLoading = false;
              this.formData.name = '';
              this.formData.phone = '';
              this.$emit('closeAddDepartment',false,1);
            })
            .catch(err=>{
              this.$message.error('添加失败，请检查部门名称是否重复');
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