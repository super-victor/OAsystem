!<!-- SendFileManagement -->
<template>
  <div class='messageItem'>
    <div class="message_box flex flex-col" @click="Read">
        <div class="title flex flex-row">
            <el-tag :type="tagType" class="tag">成功</el-tag>
            <p>{{this.msg.title}}</p>
            <el-badge :is-dot=isRead class="item"/>
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
        } else if (this.msg.type===1) {
            this.tagType ='success';
        } else if (this.msg.type===3) {
            this.tagType ='info';
        } else if (this.msg.type===4) {
            this.tagType ='warning';
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
            padding: 5px;
            margin-top: 10px;
            margin-left: 55px;
            color: @regularText;
        }
    }
    .tag {
        width: 40px;
        text-align: center;
        margin-right: 20px;
    }
  }
  .messageItem :hover {
    background-color: #F5F7FA;
  }
</style>