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
        class="center flex-col">
        <div class="card"
        v-for="(item, index) in cards"
        :key="item.position">
          <Card :msg="cards[index]"></Card>
        </div>
      </div>
      <div v-else>
        <p class="tip_info">该分类下暂无名片，请进行新建或导入名片操作！</p>
      </div>
    </div>
    <!-- 新建/导入名片 -->
    <el-dialog title="新建名片" :visible.sync="dialogVisible2" class="newCard">
      <div class="step1 flex-col">
          <p>请选择您想要进行的操作:</p>
          <el-select v-model="optionType">
            <el-option label="新建" value="新建"></el-option>
            <el-option label="导入" value="导入"></el-option>
          </el-select>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false;optionType = ''">取 消</el-button>
        <el-button type="primary" @click="innerVisible = true" v-show="optionType !== ''">下一步</el-button>
      </div>
    </el-dialog>
    <el-dialog
      width="40%"
      title="输入信息"
      :visible.sync="innerVisible"
      class="innerCard">
      <div v-if="optionType === '新建'">
        <el-input v-model="newInfo.name" placeholder="请输入姓名"></el-input>
        <el-input v-model="newInfo.phone" placeholder="请输入电话"></el-input>
        <el-input v-model="newInfo.company" placeholder="请输入公司"></el-input>
        <el-input v-model="newInfo.department" placeholder="请输入部门"></el-input>
        <el-input v-model="newInfo.position" placeholder="请输入职位"></el-input>
        <el-input v-model="newInfo.email" placeholder="请输入邮箱"></el-input>
        <el-input v-model="newInfo.address" placeholder="请输入地址"></el-input>
      </div>
      <div v-else>
        <div class="flex-col">
          <p>名片共享码</p>
          <el-input v-model="cardCode"></el-input>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="innerVisible = false;optionType =''">取 消</el-button>
        <el-button type="primary" @click="submitOption(optionType)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
  import cardFileAPI from '@/service/cardFile'
  import Card from './Card'
  import CardFileList from './CardFileList'
  export default {
    components: {
      Card,
      CardFileList
    },
    data() {
      return {
        classifyNames: [], // 名片夹分类名
        newName: '', // 添加的名片夹分类名
        optionType: '', // 选择新建操作的类型
        dialogVisible1: false, // 添加分类
        dialogVisible2: false, // 添加名片
        innerVisible: false, //内层输入信息
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
        fileId: 0 // 分类id
      };
    },
    computed: {
      ...mapState['userInfo']
    },
    watch: {
    },
    methods: {
      // 新建或导入名片
      submitOption(type) {
        if(type === '新建') {
          cardFileAPI.addCard({
            name: '',
            phone: '',
            email: '',
            company: '',
            department: '',
            position: '',
            address: '',
            employeeId:1
          })
          .then(res=>{
            this.innerVisible = false;
            this.dialogVisible2 = false;
          })
          .catch(err=>{
            this.$message.error('新建失败');
          })
        } else {
          cardFileAPI.importCard({
            cardHolderClassfyId:this.select.id,
            cardHolderId: this.cardCode
          })
          .then(res=>{
            this.innerVisible = false;
            this.dialogVisible2 = false;
          })
          .catch(err=>{
            this.$message.error('导入失败');
          })
        }
      },
      // 获取某一分类下的名片
      getCard(data) {
        this.cards = data.info;
        this.fileId = data.fileId;
      }
    },
    created() {
      // console.log(this.classifyNames);
    },
    mounted() {
      this.$emit('childrenBread',['共享名片']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .ShareCard{
    height: 500px;
    width: 100%;
    font-size: 30px;
    .msgBox{
      width: 100%;
      .left {
        padding: 0.2rem 0;
        background-color: @white;
        border-radius: @baseBorderRadius;
      }
      .center {
        margin-left: 0.5rem;
      }
      .tip_info {
        padding-left: 1rem;
        width: 9rem;
        font-size: 0.35rem;
        color: @warningColor;
      }
      .new_card {
        padding: 0.5rem;
        margin-bottom: 0.5rem;
        background-color: @white;
        border-radius: @baseBorderRadius;
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
      .el-input {
        margin-bottom: 0.2rem;
      }
      p {
        margin-bottom: 0.3rem;
      }
    }
  }
</style>