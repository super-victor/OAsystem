!<!-- ChildrenComponent1 -->
<template>
  <div class='ShareCard'>
    <div class="msgBox flex-row">
      <!-- 左侧名片夹分类 -->
      <div class="flex-col">
        <!-- 新建名片 -->
        <div class="new_card flex-col">
          <el-button class="submit" round type="primary" @click="dialogVisible2 = true">+ 新建名片</el-button>
        </div>
        <div class="left">
          <CardFileList @getCard="getCard"></CardFileList>
        </div>
      </div>
      <!-- 中间名片部分 -->
      <div
        v-if="cards.length !== 0"
        class="center flex-col"
        v-loading="loading">
        <div class="card"
        v-for="(item, index) in cards.slice((currentPage-1)*pageSize,currentPage*pageSize)"
        :key="item.position">
          <Card :msg="cards[index]"></Card>
        </div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="pageSize"
          layout="sizes, prev, pager, next"
          :total="totalCount"
          v-if="this.totalCount !== 0"
          class="pagination">
        </el-pagination>
      </div>
      <div v-else>
        <p class="tip_info">该分类下暂无名片，请进行新建或导入名片操作！</p>
      </div>
    </div>
    <!-- 新建/导入名片 -->
    <el-dialog title="新建名片" :visible.sync="dialogVisible2" class="newCard" width="30%">
      <div class="step1 flex-col">
          <p>请选择您想要进行的操作:</p>
          <el-select v-model="optionType">
            <el-option label="新建" value="新建"></el-option>
            <el-option label="导入" value="导入"></el-option>
          </el-select>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false;optionType = ''" class="submit">取 消</el-button>
        <el-button type="primary" @click="innerVisible = true" v-show="optionType !== ''">下一步</el-button>
      </div>
    </el-dialog>
    <el-dialog
      width="40%"
      title="输入信息"
      :visible.sync="innerVisible"
      class="innerCard">
      <div v-if="optionType === '新建'">
      <el-form ref="form" :model="newInfo" :rules="rules" label-width="80px" :inline-message=true>
        <el-row style="height:50px">
          <el-col :span="12">
            <el-form-item label="姓名" prop="name" style="height:50px">
              <el-input v-model="newInfo.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话" prop="phone" style="height:50px">
              <el-input v-model="newInfo.phone" placeholder="请输入电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="公司" prop="company" style="height:70px">
          <el-input v-model="newInfo.company" placeholder="请输入公司" class="input"></el-input>
        </el-form-item>
        <el-row style="height:50px">
          <el-col :span="12">
            <el-form-item label="部门" prop="department" style="height:50px">
              <el-input v-model="newInfo.department" placeholder="请输入部门" class="input"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位" prop="position" style="height:50px">
              <el-input v-model="newInfo.position" placeholder="请输入职位" class="input"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="邮箱" prop="email" style="height:70px">
          <el-input v-model="newInfo.email" placeholder="请输入邮箱" class="input"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address" style="height:70px">
          <el-input v-model="newInfo.address" placeholder="请输入地址" class="input"></el-input>
        </el-form-item>
      </el-form>
      </div>
      <div v-else>
        <div class="flex-col">
          <p>名片共享码</p>
          <el-input v-model="cardCode"></el-input>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="innerVisible = false;optionType =''">取 消</el-button>
        <el-button :loading="buttonLoading" type="primary" @click="submitOption(optionType,'form')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapMutations,mapState} from 'vuex';
  import cardFileAPI from '@/service/cardFile'
  import Card from '../component/Card'
  import CardFileList from '../component/CardFileList'
  export default {
    components: {
      Card,
      CardFileList
    },
    data() {
      return {
        newName: '', // 添加的名片夹分类名
        optionType: '', // 选择新建操作的类型
        dialogVisible1: false, // 添加分类
        dialogVisible2: false, // 添加名片
        innerVisible: false, //内层输入信息
        loading:true,
        buttonLoading:false,
        // 新建名片信息
        newInfo:{
          name: '',
          phone: '',
          email: '',
          company: '',
          department: '',
          position: '',
          address: '',
          employeeId:1
        },
        cardCode: '', // 名片导入时的共享码
        cards: [], // 名片信息
        fileId: 0, // 分类id
        currentPage:1,
        pageSize:5,
        totalCount: 0,
        rules:{
          email:[
            { required: true, message: '请输入电子邮箱', trigger: 'blur' },
            { pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '邮箱格式有误' }
          ],
          name:[
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ],
          address:[
            { required: true, message: '请输入地址', trigger: 'blur' }
          ],
          company:[
            { required: true, message: '请输入公司', trigger: 'blur' }
          ],
          department:[
            { required: true, message: '请输入部门', trigger: 'blur' }
          ],
          position:[
            { required: true, message: '请输入职位', trigger: 'blur' }
          ],
          phone:[
            { required: true, message: '请输入电话号码'},
            { pattern: /^[1][3,4,5,7,8][0-9]{9}$/, message: '电话号码格式有误' }
          ]
        }
      };
    },
    computed: {
    },
    watch: {
    },
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      // 新建或导入名片
      submitOption(type,formName) {
        this.buttonLoadng = true;
        if(type === '新建') {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            cardFileAPI.addCard({
              name: this.newInfo.name,
              phone: this.newInfo.phone,
              email: this.newInfo.email,
              company: this.newInfo.company,
              department: this.newInfo.department,
              position: this.newInfo.position,
              address: this.newInfo.address,
              cardHolderId:this.fileId
            })
            .then(res=>{
              this.innerVisible = false;
              this.dialogVisible2 = false;
              this.$forceUpdate();
              this.buttonLoadng = false;
            })
            .catch(err=>{
              this.$message.error('新建失败');
            })
          }
        })
        } else {
          cardFileAPI.importCard({
            cardHolderId:this.fileId,
            cardId: this.cardCode
          })
          .then(res=>{
            this.innerVisible = false;
            this.dialogVisible2 = false;
            this.$forceUpdate();
            this.buttonLoadng = false;
          })
          .catch(err=>{
            this.$message.error('导入失败');
          })
        }
      },
      // 获取某一分类下的名片
      getCard(data) {
        this.cards = data.info;
        this.totalCount = data.info.length;
        this.fileId = data.fileId;
        this.loading=false;
      },
      handleSizeChange(val) {
        this.pageSize = val;
      },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val;
      }
    },
    created() {
      // console.log(this.cards);
    },
    mounted() {
      this.UPDATE_BREAD(['名片夹','共享名片']);
    }
  }
</script>
<style lang='less' scoped>
// @import '../../../style/common.less';
  .ShareCard{
    height: 100%;
    width: 100%;
    font-size: 30px;
    .msgBox{
      width: 100%;
      height: 100%;
      .left {
        height: 100%;
        padding: 0.2rem 0;
        background-color:white;
        border-radius: 4px;
        overflow-y: scroll;
      }
      .left::-webkit-scrollbar{
        display: none;
      }
      .center {
        margin-left: 0.5rem;
        width: 100%;
        height: 550px;
        background-color: white;
        border-radius: 4px;
        padding: 30px 0;
        overflow-y: scroll;
        .card{
          margin: 0 auto;
          margin-bottom: 0.2rem;
        }
        .pagination {
          margin: 0 auto;
        }
      }
      .center::-webkit-scrollbar{
        display: none;
      }
      .tip_info {
        margin-left: 0.5rem;
        padding: 0.5rem;
        width: 9rem;
        font-size: 0.3rem;
        color: #E6A23C;
        background-color: white;
      }
      .new_card {
        padding: 0.5rem;
        margin-bottom: 0.5rem;
        background-color: white;
        border-radius: 4px;
        .submit {
          width: 2rem;
        }
      }
    }
    .newCard .el-dialog__body {
      p {
        margin-bottom: 0.3rem;
      }
    }
    .innerCard .el-dialog__body{
      padding: 0px;
      p {
        margin-bottom: 0.3rem;
      }
    }
    ::v-deep .form-item {
      margin: 20px;
    }
  }
</style>