<template>
  <div class='card flex-row'>
    <div class="box flex-col">
      <div class="inner">
        <div class="row flex-row">
          <p class="name">{{ msg.card.name }}</p>
          <p class="depart">/{{ msg.card.department }}{{ msg.card.position }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">电话</p>
          <p class="llabel">{{ msg.card.phone }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">邮箱</p>
          <p class="llabel">{{ msg.card.email }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">公司</p>
          <p class="llabel">{{ msg.card.company }}</p>
        </div>
        <div class="info_row flex-row">
          <p class="rlabel">地址</p>
          <p class="llabel">{{ msg.card.address }}</p>
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
        <img src="@/assets/Card/Delete.png" alt="" @click="deleteCard(msg.cardId)">
      </div>
    </div>
    <!-- dialog-获取名片id -->
    <el-dialog title="名片分享" :visible.sync="dialogVisible1">
      <p>名片分享码为：{{ this.msg.cardId }}</p>
    </el-dialog>
    <!-- dialog-修改名片 -->
    <el-dialog title="名片修改" :visible.sync="dialogVisible2" class="innerCard">
      <el-form ref="form" :model="newInfo" :rules="rules" label-width="80px" :inline-message=true>
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="newInfo.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话" prop="phone">
              <el-input v-model="newInfo.phone" placeholder="请输入电话" class="input"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="公司" prop="company">
          <el-input v-model="newInfo.company" placeholder="请输入公司" class="input"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门" prop="department">
              <el-input v-model="newInfo.department" placeholder="请输入部门" class="input"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位" prop="position">
              <el-input v-model="newInfo.position" placeholder="请输入职位" class="input"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="newInfo.email" placeholder="请输入邮箱" class="input"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="newInfo.address" placeholder="请输入地址" class="input"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="type">
          <el-select v-model="newInfo.holder" placeholder="请选择分类">
              <el-option
              v-for="item in holders"
              :key="item.id"
              :label="item.name"
              :value="item.id">
              </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="updateCard('form')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import cardFileAPI from '@/service/cardFile'
  export default {
    props: ["msg"],
    data() {
      return {
        dialogVisible1: false, // 分享dialog
        dialogVisible2: false, // 修改dialog
        // 编辑名片信息
        newInfo:{
          name: this.msg.card.name,
          phone: this.msg.card.phone,
          email: this.msg.card.email,
          company: this.msg.card.company,
          department: this.msg.card.department,
          position: this.msg.card.position,
          address: this.msg.card.address,
          holder: this.msg.card.holder,
        }, // 信息
        holders: [], // 名片夹
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
        },
      };
    },
    computed: {},
    watch: {},
    methods: {
      // 删除card
      deleteCard(id) {
        cardFileAPI.deleteCard({
          cardId:id
        })
        .then(res=>{
          this.dialogVisible1 = false;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('发生错误');
        })
      },
      // 修改card
      updateCard(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            cardFileAPI.updateCard({
                cardId:this.msg.cardId,
                name: this.newInfo.name,
                phone: this.newInfo.phone,
                email: this.newInfo.email,
                company: this.newInfo.company,
                department: this.newInfo.department,
                position: this.newInfo.position,
                address: this.newInfo.address,
                cardHolderId:this.newInfo.holder
            })
            .then(res=>{
              this.$message.success('修改成功');
              this.msg.card=this.newInfo;
              this.msg.cardHolderId = this.newHolder;
              this.dialogVisible2 = false;
              this.$forceUpdate();
            })
            .catch(err=>{
              console.log(err);
              this.$message.error('发生错误');
            })
          }
        })
      },
      // 请求名片夹分类
      getFileName () {
        cardFileAPI.requestCardFile()
        .then(res=>{
          res.object.forEach(element => {
            this.holders.push({'name':element.name, 'id':element.cardHolderId});
          });
        })
        .catch(err=>{
          // console.log(err);
          this.$message.error('获取失败');
        })
      },
    },
    created() {
      this.getFileName();
      console.log(this.msg);
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
// @import '../../../style/common.less';
  .card{
    padding: 0.5rem 1.5rem;
    background-color: white;
    border-radius: 4px;
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
        background-color: white;
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
        color: #303133;
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
      background-color:#9CB7F0;
    }
    .edit {
      background-color:#E6A23C;
    }
    .delete {
      background-color:#F56C6C;
    }
    .innerCard .el-dialog__body{
      padding: 0px;
      .el-input {
        margin-top: 0.2rem;
      }
    }
  }
</style>