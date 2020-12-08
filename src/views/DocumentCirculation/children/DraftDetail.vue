!<!-- DraftDetail -->
<template>
  <div class='DraftDetail' v-loading="loading">
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
      ...mapMutations(['UPDATE_BREAD'])
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','公文详情']);
      sendFileAPI.getDocument({
        sendfileId:this.$route.params.sendfileId
      })
      .then(res=>{
        console.log(res)
        this.fileInfo = res.object;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取公文详情失败');
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