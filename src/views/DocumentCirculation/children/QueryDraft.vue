!<!-- QueryDraft -->
<template>
  <div class='QueryDraft'>
    <div class="topBox">
      <query-drafts></query-drafts>
      <query-reject></query-reject>
    </div>
    <div class="bottomBox">
      <query-verify></query-verify>
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