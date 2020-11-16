!<!-- ChildrenComponent1 -->
<template>
  <div class='Mine'>
    <div class="msgBox flex-row">
      <div class="right flex-col">
        <div class="card">
          <Card :msg="cards"></Card>
        </div>
      </div>
    </div>
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
        cards: {
          name: '张菲燕',
          position: '人事部部门经理',
          tel: '028-82601686',
          email: 'Paranoid_ZH@163.com',
          location: '成龙大道二段1819号'
        }
      };
    },
    computed: {
    },
    watch: {},
    methods: {
    },
    created() {
      
    },
    mounted() {
      this.$emit('childrenBread',['我的名片']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .Mine{
    height: 200px;
    width: 100%;
    font-size: 30px;
    .msgBox{
      height: 1000px;
      width: 100%;
      .right {
        margin-left: 1rem;
        .card {
          margin-bottom: 0.2rem;
        }
      }
    }
  }
</style>