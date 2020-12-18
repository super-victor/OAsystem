!<!-- TheAsideMenu -->
<template>
  <div>
    <div 
    class='asideMenu'
    v-for="item in asideMenu"
    :key="item.clickMark"
    :style="{'backgroundColor':($store.state.allowShowItem && $store.state.mouseClickIndex==item.clickMark)?'#5383EC':'#FFFFFF'}"
    @mouseenter="mouseIndex=item.clickMark"
    @mouseleave="mouseIndex=0"
    @click="click(item)">
      <img :src="($store.state.allowShowItem && $store.state.mouseClickIndex==item.clickMark)?require(`@/assets/aside/${item.img}_click.png`):mouseIndex==item.clickMark?require(`@/assets/aside/${item.img}_opt.png`):require(`@/assets/aside/${item.img}.png`)" alt="" class="img">
      <p class="title" :style="{'color':($store.state.allowShowItem && $store.state.mouseClickIndex==item.clickMark)?'#FFFFFF':mouseIndex==item.clickMark?'#5383EC':'#868B8E'}">{{item.title}}</p>
    </div>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex';
  export default {
    components: {},
    data() {
      return {
        mouseIndex:0
      };
    },
    computed: {
      ...mapState([
        'asideMenu'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations([
        'ITEM_CLICK',
        'MAIN_CLICK',
        'ASIDE_CLICK'
      ]),
      click(item){
        this.ITEM_CLICK(item.clickMark);
        this.MAIN_CLICK(true);
        this.ASIDE_CLICK(item);
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