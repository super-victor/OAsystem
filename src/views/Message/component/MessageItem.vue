!<!-- SendFileManagement -->
<template>
  <div class='messageItem'>
    <div class="message_box flex flex-col" @click="Read">
        <div class="title flex flex-row">
            <p>{{this.msg.title}}</p>
            <el-badge :is-dot=isRead class="item"/>
            <p class="tag" :style="{backgroundColor:type}">{{ this.info }}</p>
        </div>
        <div class="content">
            <p>{{this.msg.content}}</p>
        </div>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import messageApi from '@/service/Message';
  export default {
    components: {
    },
    data() {
      return {
        tagType: 'success',
        isRead:0,
        info:'',
        type:'',
      }
    },
    props:['msg'],
    computed: {},
    watch: {},
    methods: {
        Read(){
            messageApi.readMessage({
                messageId:this.msg.messageId
            })
            .then(res=>{
                console.log(res);
                this.isRead = false;
            })
            .catch(err=>{
                console.log(err);
            })
        }
    },
    created() {
        if (this.msg.type===0) {
            this.tagType ='danger';
            this.info = '失败';
            this.type="#F56C6C";
        } else if (this.msg.type===1) {
            this.tagType ='success';
            this.info = '成功';
            this.type="#67C23A";
        } else if (this.msg.type===2) {
            this.tagType ='info';
            this.info = '提示';
            this.type = '#5383EC';
        } else if (this.msg.type===3) {
            this.tagType ='warning';
            this.info = '警告';
            this.type= '#E6A23C';
        }
        if (this.msg.isRead === 0) {
            this.isRead = true;
        } else {
            this.isRead = false;
        }
    },
    mounted() {
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .messageItem{
    width: 100%;
    height: 120px;
    border-bottom: 1px solid @lighterBorder;
    cursor: pointer;
    margin: 10px 0;
    .message_box {
        padding: 20px 10px;
        .title {
            font-size: 20px;
            line-height: 30px;
            color: @primaryText;
        }
        .content {
            font-size: 14px;
            padding: 5px 0;
            margin-top: 10px;
            color: @regularText;
            width: 100%;
            word-wrap:break-word;
        }
    }
    .tag {
        width: 40px;
        text-align: center;
        margin-left: 20px;
        font-size: 0.15rem;
        font-weight: bold;
        color: @white;
        border-radius: @smallBorderRadius;
        // background-color: @dangerColor;
    }
  }
  .messageItem :hover {
    background-color: #F5F7FA;
  }
</style>