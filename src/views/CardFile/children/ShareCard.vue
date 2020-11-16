!<!-- ChildrenComponent1 -->
<template>
  <div class='ShareCard'>
    <div class="msgBox flex-row">
      <div class="left">
        <div class="row flex-row"
        @click="SelectClassfy(item.name)"
        v-for="item in classifyNames"
        :key="item.name"
        :class="{active: select === item.name,normal: select !== item.name}">
          <i class="el-icon-caret-right"></i><p>{{ item.name }}</p>
        </div>
        <div class="row flex-row" @click="dialogVisible1 = true">
          <i class="el-icon-circle-plus-outline"></i><p>新建分类</p>
        </div>
      </div>
      <el-divider direction="vertical"></el-divider>
      <div class="center flex-col">
        <div class="card"
        v-for="(item, index) in cards"
        :key="item.position">
          <Card :msg="cards[index]"></Card>
        </div>
      </div>
      <div class="right flex-col">
        <el-button class="submit" round type="primary" @click="dialogVisible2 = true">新建</el-button>
      </div>
    </div>
    <!-- 新建名片夹分类 -->
    <el-dialog title="新建分类" :visible.sync="dialogVisible1">
      <el-input v-model="newName"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="AddClassify">确 定</el-button>
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
      <el-dialog
        width="30%"
        title="输入信息"
        :visible.sync="innerVisible"
        class="newCard"
        append-to-body>
        <div
          class="flex-col"
          v-if="optionType === '新建'">
          <p>姓名</p>
          <el-input v-model="newInfo.name"></el-input>
          <p>电话</p>
          <el-input v-model="newInfo.tel"></el-input>
          <p>职位</p>
          <el-input v-model="newInfo.location"></el-input>
          <p>邮箱</p>
          <el-input v-model="newInfo.email"></el-input>
          <p>地址</p>
          <el-input v-model="newInfo.position"></el-input>
        </div>
        <div v-else>
          <div class="flex-col">
            <p>名片共享码</p>
            <el-input v-model="cardCode"></el-input>
          </div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="innerVisible = false;optionType =''">取 消</el-button>
          <el-button type="primary" @click="innerVisible = false;dialogVisible2 = false">确 定</el-button>
        </div>
      </el-dialog>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false;optionType = ''">取 消</el-button>
        <el-button type="primary" @click="innerVisible = true" v-show="optionType !== ''">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex'
  import Card from './Card'
  export default {
    components: {
        Card
    },
    data() {
      return {
        classifyNames: [{
          name: '我的名片夹'
        },{
          name: '部门同事'
        }], // 名片夹分类名
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
          position: '',
          location: '',
        },
        cardCode: '', // 名片导入时的共享码
        cards: [{
          name: '张菲燕',
          position: '人事部部门经理',
          tel: '028-82601686',
          email: 'Paranoid_ZH@163.com',
          location: '成龙大道二段1819号'
        }, {
          name: '张静文',
          position: '财务部部门经理',
          tel: '028-82600562',
          email: 'Fineven@163.com',
          location: '成龙大道二段1819号'
        }] // 名片信息
      };
    },
    computed: {
    },
    watch: {},
    methods: {
    //   选择名片夹分类
      SelectClassfy (name) {
        this.select = name;
        console.log(name);
      },
    //   添加分类
      AddClassify () {
        this.classifyNames.push({name: this.newName});
        this.newName = '';
        this.dialogVisible1 = false;
      },
      next() {
        if (this.active++ > 2) this.active = 0;
      }
    },
    created() {
      
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
      }
      .center {
        margin-left: 2rem;
        .card {
          margin-bottom: 0.2rem;
        }
      }
      .right {
        margin-left: 1.5rem;
      }
      .row {
        padding: 0.15rem;
        font-size: 0.22rem;
        cursor: pointer;
        i {
          width: 0.4rem;
          margin-top: 0.05rem;
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
    .input.el-input__inner {
      margin-top: 20px;
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