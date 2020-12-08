!<!-- EmptyBox -->
<template>
  <div class='EmptyBox'>
    <img src="@/assets/noData.png" alt="">
    <p class="noData">暂无内容</p>
  </div>
</template>

<script>
  
  export default {
    components: {},
    data() {
      return {
        
      };
    },
    computed: {},
    watch: {},
    methods: {
      
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  .EmptyBox{
    height: calc(100% - 50px);
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .noData{
      height: 20px;
      width: 200px;
      font-size: 0.18rem;
      text-align: center;
      color: #C0C4CC;
    }
  }
</style>