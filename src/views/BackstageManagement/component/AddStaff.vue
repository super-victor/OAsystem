!<!-- AddStaff -->
<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      title="添加用户"
      :visible.sync="addStaffDialogVisible"
      width="10rem"
      style="margin-top:-70px;"
      :before-close="closeAddStaff">
      <el-form :model="formData" :rules="rules" ref="formData" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name" style="height:40px;width:4rem;">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex" style="height:40px;width:4rem;">
              <el-select v-model="formData.sex" placeholder="请选择性别">
                <el-option label="男" value="m"></el-option>
                <el-option label="女" value="f"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="电话号码" prop="phone" style="height:40px;width:8.65rem">
          <el-input v-model.number="formData.phone"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email" style="height:40px;width:8.65rem">
          <el-input v-model="formData.email"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard" style="height:40px;width:8.65rem">
          <el-input v-model="formData.idCard"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门" prop="departmentName" style="height:40px;width:4rem">
              <el-select v-model="formData.departmentName" placeholder="请选择部门">
                <el-option
                v-for="item in departmentArr"
                :key="item.departmentId"
                :label="item.name"
                :value="item.name"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位" prop="position" style="height:40px;width:4rem">
              <el-select v-model="formData.position" placeholder="请选择职位">
                <el-option label="部门总监" value="部门总监"></el-option>
                <el-option label="部门经理" value="部门经理"></el-option>
                <el-option label="办公室主任" value="办公室主任"></el-option>
                <el-option label="部门主管" value="部门主管"></el-option>
                <el-option label="职员" value="职员"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="家庭住址" prop="homeAddress" style="height:40px;width:8.65rem">
          <el-input v-model="formData.homeAddress"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="closeAddStaff">取 消</el-button>
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
      addStaffDialogVisible:{
        type:Boolean,
        required:true,
        default:false
      },
      departmentArr:{
        type:Array,
        required:true
      }
    },
    data() {
      return {
        buttonLoading:false,
        formData:{
          name:'',
          sex:'',
          phone:'',
          email:'',
          idCard:'',
          departmentName:'',
          position:'',
          homeAddress:''
        },
        rules:{
          name:[
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ],
          sex:[
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          phone:[
            { required: true, message: '请输入电话号码', trigger: 'blur' },
            { pattern: /^[1][3,4,5,7,8][0-9]{9}$/, message: '电话号码格式有误' }
          ],
          email:[
            { required: true, message: '请输入电子邮箱', trigger: 'blur' },
            { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '邮箱格式有误' }
          ],
          idCard:[
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '身份证号格式有误' }
          ],
          departmentName:[
            { required: true, message: '请选择部门', trigger: 'change' }
          ],
          position:[
            { required: true, message: '请选择职位', trigger: 'change' }
          ],
          homeAddress:[
            { required: true, message: '请输入家庭住址', trigger: 'blur' }
          ]
        }
      };
    },
    computed: {},
    watch: {},
    methods: {
      closeAddStaff(){
        this.$emit('closeAddStaff',false);
      },
      submitForm(formName){
        this.$refs[formName].validate((valid) => {
          if(valid){
            this.buttonLoading = true;
            backstageAPI.addEmployee(this.formData)
            .then(res=>{
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.buttonLoading = false;
              this.formData.name = '';
              this.formData.sex = '';
              this.formData.phone = '';
              this.formData.email = '';
              this.formData.idCard = '';
              this.formData.departmentName = '';
              this.formData.position = '';
              this.formData.homeAddress= '';
              this.$emit('closeAddStaff',false,1);
            })
            .catch(err=>{
              console.log(err)
              this.$message.error('添加失败');
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