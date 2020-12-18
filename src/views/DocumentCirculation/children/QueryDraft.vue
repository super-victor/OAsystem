!<!-- QueryDraft -->
<template>
  <div class='QueryDraft'>
    <div class="topBox">
      <query-drafts v-show="draftsFlag"></query-drafts>
      <query-reject v-show="rejectFlag"></query-reject>
    </div>
    <div class="bottomBox">
      <query-verify v-show="verifyFlag"></query-verify>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import QueryDrafts from '../component/QueryDrafts'
  import QueryReject from '../component/QueryReject'
  import QueryVerify from '../component/QueryVerify'
  export default {
    components: {
      QueryDrafts,
      QueryReject,
      QueryVerify
    },
    data() {
      return {
        draftsFlag:false,
        rejectFlag:false,
        verifyFlag:false
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD'])
    },
    created() {
      let role = this.$authority.getPageAuthority('documentcirculation','querydraft').role;
      if(role['002G'].own) this.draftsFlag = true;
      if(role['002M'].own) this.rejectFlag = true;
      if(role['002L'].own) this.verifyFlag = true;
    },
    mounted() {
      this.UPDATE_BREAD(['公文流转','查询拟稿']);
    },
  }
</script>
<style lang='less' scoped>
  .QueryDraft{
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    .topBox{
      height: 58%;
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
    .bottomBox{
      height: 40%;
      width: 100%;
    }
  }
</style>