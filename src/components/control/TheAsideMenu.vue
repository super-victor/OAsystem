!<!-- TheAsideMenu -->
<template>
  <div>
    <div 
    class='asideMenu'
    v-for="item in asideMenu"
    :key="item.clickMark"
    :style="{'backgroundColor':mouseClick==item.clickMark?'#5383EC':'#FFFFFF'}"
    @mouseenter="mouseIndex=item.clickMark"
    @mouseleave="mouseIndex=0"
    @click="click(item.clickMark)">
      <img :src="mouseClick==item.clickMark?require(`@/assets/aside/${item.img}_click.png`):mouseIndex==item.clickMark?require(`@/assets/aside/${item.img}_opt.png`):require(`@/assets/aside/${item.img}.png`)" alt="" class="img">
      <p class="title" :style="{'color':mouseClick==item.clickMark?'#FFFFFF':mouseIndex==item.clickMark?'#5383EC':'#868B8E'}">{{item.title}}</p>
    </div>
  </div>
</template>

<script>
  
  export default {
    props:{
      asideMenu:{
        type:Array,
        required:true
      }
    },
    components: {},
    data() {
      return {
        mouseIndex:0,
        mouseClick:0
      };
    },
    computed: {},
    watch: {},
    methods: {
      click(index){
        this.mouseClick=index;
      }
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
  .asideMenu{
    height: 80px;
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    cursor: pointer;
    .img{
      height: 23px;
      width: 23px;
    }
    .title{
      height: 25px;
      width: 100%;
      text-align: center;
      line-height: 30px;
      font-size: 13px;
    }
  }
</style>