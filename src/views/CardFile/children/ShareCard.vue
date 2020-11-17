!<!-- ChildrenComponent1 -->
<template>
  <div class='ShareCard'>
    <div class="msgBox flex-row">
      <!-- 左侧名片夹分类 -->
      <div class="left">
        <div class="row flex-row"
        @click="selectClassfy(item)"
        v-for="item in classifyNames"
        :key="item.id"
        :class="{active: select.id === item.id,normal: select.id !== item.id}">
          <img :src="(select.id === item.id)?require(`@/assets/Card/list_row_click.png`):require(`@/assets/Card/list_row.png`)" alt="">
          <p>{{ item.name }}</p>
        </div>
        <div class="flex-row add_row" @click="dialogVisible1 = true">
          <img src="@/assets/Card/add.png" alt=""><p>新建分类</p>
        </div>
      </div>
      <el-divider direction="vertical"></el-divider>
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
      <el-divider direction="vertical"></el-divider>
      <!-- 新建button -->
      <div class="right flex-col">
        <el-button class="submit" round type="primary" @click="dialogVisible2 = true">新建</el-button>
      </div>
    </div>
    <!-- dialog-新建名片夹分类 -->
    <el-dialog title="新建分类" :visible.sync="dialogVisible1">
      <el-input v-model="newName" placeholder="请输入名片夹分类名"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="addFile">确 定</el-button>
      </div>
    </el-dialog>
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
  export default {
    components: {
        Card
    },
    data() {
      return {
        classifyNames: [], // 名片夹分类名
        newName: '', // 添加的名片夹分类名
        select: '', // 选中的名片夹分类
        optionType: '', // 选择新建操作的类型
        dialogVisible1: false, // 添加分类
        dialogVisible2: false, // 添加名片
        innerVisible: false, //内层输入信息
        // 新建名片信息
        newInfo:{
          name: '',
          tel: '',
          email: '',
          company: '',
          department: '',
          position: '',
          address: '',
        },
        cardCode: '', // 名片导入时的共享码
        cards: [{
          name: '张静文',
          tel: '028-92060989',
          email: '2346273686@qq.com',
          company: 'Sicnu',
          department: '计科',
          position: '学生',
          address: '成龙大道二段1819号',
        }] // 名片信息
      };
    },
    computed: {
      ...mapState['userInfo']
    },
    watch: {},
    methods: {
    //   选择名片夹分类
      selectClassfy (select) {
        this.select = select;
        this.getCard(select.id);
      },
      next() {
        if (this.active++ > 2) this.active = 0;
      },
      // 请求名片夹分类
      getFileName () {
        cardFileAPI.requestCardFile()
        .then(res=>{
          res.object.forEach(element => {
            this.classifyNames.push({'name':element.name, 'id':element.cardHolderClassfyId});
          });
        })
        .catch(err=>{
          // console.log(err);
          this.$message.error('获取失败');
        })
      },
      // 添加名片夹分类
      addFile () {
        cardFileAPI.addCardFile({
          name: this.newName
        })
        .then(res=>{
          this.$message.success('添加成功');
          this.classifyNames.push({name: this.newName});
          this.newName = '';
          this.dialogVisible1 = false;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('添加失败');
        })
      },
      // 获取名片
      getCard(id) {
        cardFileAPI.requestCard({
          cardHolderClassfyId: id
        })
        .then(res=>{
          // this.cards = res.object;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('获取失败');
        })
      },
      // 新建或导入名片
      submitOption(type) {
        if(type === '新建') {
        } else {
          cardFileAPI.importCard({
            cardHolderClassfyId:this.select.id,
            cardHolderId: this.cardCode
          })
          .then(res=>{
            this.innerVisible = false;
            this.dialogVisible2 = false;
            console.log('a');
          })
          .catch(err=>{
            this.$message.error('导入失败');
          })
        }
      }
    },
    created() {
      this.getFileName();
        cardFileAPI.test()
        .then(res=>{
          console.log(res);
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('添加失败');
        })
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
      height: 1000px;
      width: 100%;
      .left {
        width: 2.5rem;
        .add_row {
          padding: 0.15rem;
          font-size: 0.2rem;  
          cursor: pointer;
          img {
            width: 0.3rem;
            height: 0.3rem;
            color: @regularText;
          }
          color: @regularText;
        }
      }
      .center {
        width: 8rem;
        padding-left: 2rem;
        .card {
          margin-bottom: 0.2rem;
        }
      }
      .tip_info {
        padding-left: 1rem;
        width: 9rem;
        font-size: 0.35rem;
        color: @warningColor;
      }
      .right {
        margin-left: 0.5rem;
      }
      .row {
        padding: 0.15rem;
        font-size: 0.22rem;
        cursor: pointer;
        img {
          width: 0.3rem;
          height: 0.3rem;
          margin-right: 0.15rem;
        }
      }
      .el-divider--vertical {
        height: auto;
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
    .normal {
      color: @primaryColor;
    }
    .active {
      background-color: @primaryColor;
      color: @white;
    }
  }
</style>