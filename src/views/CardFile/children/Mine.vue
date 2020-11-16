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
  import Card from './Card'
  import cardFileAPI from '@/service/cardFile'
  export default {
    components: {
      Card
    },
    data() {
      return {
        cards: {
          name: '张菲燕',
          department: '',
          position: '人事部部门经理',
          phone: '028-82601686',
          email: 'Paranoid_ZH@163.com',
          company: '四川师范大学',
          address: '成龙大道二段1819号'
        }
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      getMine() {
        cardFileAPI.requestMine()
        .then(res=>{
          this.cards = res.object;
        })
        .catch(err=>{
          // console.log(err);
          this.$message.error('获取失败');
        })
      }
    },
    created() {
      this.getMine();
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