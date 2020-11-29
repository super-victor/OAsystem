<template>
  <div class='cardFileList flex-col'>
    <div class="row flex-row"
    @click="selectClassfy(item)"
    v-for="item in fileName"
    :key="item.id"
    :class="{active: select.id === item.id,normal: select.id !== item.id}">
    <img :src="(select.id === item.id)?require(`@/assets/Card/list_row_click.png`):require(`@/assets/Card/list_row.png`)" alt="">
    <p>{{ item.name }}</p>
    </div>
    <div class="flex-col edit_row">
    <p class="tip">管理分类</p>
    <div class="flex-row">
        <div class="right_i" title="添加分类" @click="dialogVisible1 = true;">
            <img src="@/assets/Card/f_add.png" alt="">
        </div>
        <div class="right_i" title="修改分类名">
        <img src="@/assets/Card/f_edit.png" alt="" @click="dialogVisible2 = true">
        </div>
        <div class="right_i" title="删除分类" @click="dialogVisible3 = true">
        <img src="@/assets/Card/f_delete.png" alt="">
        </div>
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
    <!-- dialog-修改名片夹分类 -->
    <el-dialog title="修改分类名字" :visible.sync="dialogVisible2" class="newCard">
        <p>请选择要修改的分类</p>
        <el-select v-model="updateFile" placeholder="请选择">
            <el-option
            v-for="item in fileName"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
        <div v-if="updateFile">
            <p>请输入新的分类名</p>
            <el-input v-model="updateName"></el-input>
        </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false;updateFile = '';updateName = ''">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>
    <!-- dialog-删除名片夹分类 -->
    <el-dialog title="删除分类" :visible.sync="dialogVisible3" class="newCard">
        <p>请选择要删除的分类</p>
        <el-select v-model="deleteFile" placeholder="请选择">
            <el-option
            v-for="item in fileName"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
        <p class="warning" v-if="deleteFile">该分类将被删除，该分类下的名片将转移到默认分类下！</p>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible3 = false;deleteFile = ''">取 消</el-button>
        <el-button type="primary" @click="submitDelete()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import cardFileAPI from '@/service/cardFile'
  export default {
    data() {
      return {
        fileName: [],
        select: [], // 选中的名片夹分类
        dialogVisible1: false, //新建分类dialog
        dialogVisible2: false, //修改分类dialog
        dialogVisible3: false, //删除分类dialog
        newName: '', // 新建分类名
        deleteFile: '', // 删除分类
        updateFile: '', // 修改分类
        updateName: '', // 修改的新名
      };
    },
    computed: {},
    watch: {},
    methods: {
      // 请求名片夹分类
      getFileName () {
        cardFileAPI.requestCardFile()
        .then(res=>{
          res.object.forEach(element => {
            this.fileName.push({'name':element.name, 'id':element.cardHolderId});
          });
          this.getCard(res.object[0].cardHolderId);
          this.select.id = res.object[0].cardHolderId;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('获取失败');
        })
      },
    //   选择名片夹分类
      selectClassfy (select) {
        this.select = select;
        this.getCard(select.id);
      },
    // 获取名片
    getCard(id) {
      cardFileAPI.requestCard({
        cardHolderId: id
      })
      .then(res=>{
        // this.cards = res.object;
        this.$emit('getCard',{info:res.object,fileId:this.select.id});
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
          this.fileName.push({name: this.newName,id:res.object});
          this.newName = '';
          this.dialogVisible1 = false;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('发生错误');
        })
      },
      // 修改分类名
      submitUpdate () {
        cardFileAPI.updateCardFile({
          cardHolderId: this.updateFile,
          name: this.updateName
        })
        .then(res=>{
          this.$message.success('修改成功');
          this.fileName = [];
          this.getFileName();
          this.updateName = '';
          this.dialogVisible2 = false;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('发生错误');
        })
      },
      // 删除分类
      submitDelete() {
        console.log(this.deleteFile);
        cardFileAPI.deleteCardFile({
          cardHolderId: this.deleteFile
        })
        .then(res=>{
          this.$message.success('删除成功');
          this.fileName=[];
          this.getFileName();
          this.dialogVisible3 = false;
        })
        .catch(err=>{
          console.log(err);
          this.$message.error('删除失败');
        })
      }
    },
    created() {
      this.getFileName();
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .cardFileList{
    .row {
    padding: 0.15rem;
    font-size: 0.2rem;
    cursor: pointer;
    img {
        width: 0.3rem;
        height: 0.3rem;
        margin-right: 0.15rem;
    }
    }
    .edit_row {
        margin-top: 0.2rem;
        padding: 0.15rem;
        font-size: 0.17rem;  
        cursor: pointer;
        img {
        width: 0.3rem;
        height: 0.3rem;
        color: @regularText;
        }
        color: @regularText;
        .right_i {
        margin-top: 0.15rem;
        margin-left: 0.2rem;
        cursor: pointer;
            img {
                width: 0.35rem;
                height: 0.35rem;
            }
        }
    }
    .newCard .el-dialog__body {
      p {
        margin: 0.3rem 0;
      }
      p.warning {
        margin-bottom: 0;
        color: @warningColor;
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