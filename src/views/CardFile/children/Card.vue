<template>
  <div class='card flex-row'>
    <div class="box flex-col">
      <div class="inner">
        <div class="row flex-row">
          <p class="name">{{ msg.name }}</p>
          <p class="depart">/{{ msg.department }}{{ msg.position }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">电话</p>
          <p class="llabel">{{ msg.phone }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">邮箱</p>
          <p class="llabel">{{ msg.email }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">公司</p>
          <p class="llabel">{{ msg.company }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">地址</p>
          <p class="llabel">{{ msg.address }}</p>
        </div>
      </div>
    </div>
    <div class="flex-col right">
      <div class="right_i share" title="分享">
        <img src="@/assets/Card/Share.png" alt="" @click="dialogVisible1 = true">
      </div>
      <div class="right_i edit" title="编辑">
        <img src="@/assets/Card/Edit.png" alt="" @click="dialogVisible2 = true">
      </div>
      <div class="right_i delete" title="删除">
        <img src="@/assets/Card/Delete.png" alt="" @click="shareCard">
      </div>
    </div>
    <!-- dialog-获取名片id -->
    <el-dialog title="名片分享" :visible.sync="dialogVisible1">
      <p>名片分享码为：{{ this.msg.cardHolderId }}</p>
    </el-dialog>
    <!-- dialog-修改名片 -->
    <el-dialog title="名片修改" :visible.sync="dialogVisible2" class="innerCard">
      <el-input v-model="newInfo.name" placeholder="请输入姓名"></el-input>
      <el-input v-model="newInfo.phone" placeholder="请输入电话"></el-input>
      <el-input v-model="newInfo.company" placeholder="请输入公司"></el-input>
      <el-input v-model="newInfo.department" placeholder="请输入部门"></el-input>
      <el-input v-model="newInfo.position" placeholder="请输入职位"></el-input>
      <el-input v-model="newInfo.email" placeholder="请输入邮箱"></el-input>
      <el-input v-model="newInfo.address" placeholder="请输入地址"></el-input>
      <p>修改分类</p>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: ["msg"],
    data() {
      return {
        dialogVisible1: false, // 分享dialog
        dialogVisible2: false, // 修改dialog
        // 编辑名片信息
        newInfo:{
          name: this.msg.name,
          tel: this.msg.phone,
          email: this.msg.email,
          company: this.msg.company,
          department: this.msg.department,
          position: this.msg.position,
          address: this.msg.address,
        },
      };
    },
    computed: {},
    watch: {},
    methods: {
      shareCard() {}
    },
    created() {
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .card{
    padding: 0.5rem 1.5rem;
    background-color: @white;
    border-radius: @baseBorderRadius;
    margin-bottom: 0.2rem;
    .box {
      box-sizing: border-box;
      padding: 10px;
      height: 3rem;
      width: 5.6rem;
      background: url(../../../assets/Card/cardBackground.jpg);
      .inner {
        width: 90%;
        height: 100%;
        background-color: @white;
      }
      p {
        font-size: 0.18rem;
      }
      .row {
        margin-top: 0.4rem;
        height: 0.7rem;
        margin-left: 0.5rem;
      }
      .info_row {
        height: 0.4rem;
        margin-left: 0.5rem;
      }
      p.name {
        font-size: 0.4rem;
        color: @primaryText;
      }
      p.depart {
        margin-top: 0.2rem;
        margin-left: 0.3rem;
      }
      p.rlabel {
        width: 0.7rem;
      }
    }
    .right {
      margin-left: 0.3rem;
    }
    .right_i {
      margin-top: 0.3rem;
      width: 0.5rem;
      height: 0.5rem;
      border-radius: 0.5rem;
      cursor: pointer;
      img {
        margin: 0.05rem;
        width: 0.4rem;
        height: 0.4rem;
      }
    }
    .share {
      background-color:@correlateColor1;
    }
    .edit {
      background-color:@warningColor;
    }
    .delete {
      background-color:@dangerColor;
    }
    .innerCard .el-dialog__body{
      padding: 0px;
      .el-input {
        margin-bottom: 0.2rem;
      }
    }
  }
</style>