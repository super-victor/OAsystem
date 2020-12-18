!<!-- DraftDetail -->
<template>
  <div class='DraftDetail' v-loading="loading">
    <div class="backBox">
      <p class="back" @click="goBack"><i class="el-icon-arrow-left" style="margin-right:5px;"></i>返回</p>
    </div>
    <div class="documentBox">
      <the-draft :fileInfo="fileInfo"></the-draft>
    </div>
  </div>
</template>

<script>
  import sendFileAPI from '@/service/DocumentCirculation';
  import TheDraft from '../component/TheDraft';
  import {mapMutations} from 'vuex';
  export default {
    components: {
      TheDraft
    },
    data() {
      return {
        loading:true,
        fileInfo:{}
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      goBack(){
        this.$router.push('/document-circulation/view-draft-list');
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','公文详情']);
      sendFileAPI.getDocument({
        sendfileId:this.$route.params.sendfileId
      })
      .then(res=>{
        this.fileInfo = res.object;
        this.loading = false;
      })
      .catch(err=>{
        if(err.toString() != 'Error: 权限认证错误') this.$message.error('获取公文详情失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .DraftDetail{
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    .backBox{
      height: 30px;
      width: 1.4rem;
      display: flex;
      margin-top: 5px;
      align-items: center;
      .back{
        height: 100%;
        width: 70%;
        font-size: 17px;
        color:#606266;
        cursor: pointer;
        line-height: 30px;
      }
    }
    .documentBox{
      height: calc(100vh - 160px);
      width: 72%;
      overflow-y: scroll;
      padding: 5px;
      box-sizing: border-box;
    }
    .documentBox::-webkit-scrollbar{
      display: none;
    }
  }
</style>