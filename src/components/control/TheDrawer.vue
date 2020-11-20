!<!-- TheDrawer -->
<template>
  <div class='theDrawer' @mousewheel.prevent :style="{'width':showDrawer?'220px':'0px'}">
    <div v-show="showDrawer">
      <p class="title">{{asideItem.title}}</p>
      <p class="item"
      v-for="(item,index) in asideItem.children"
      :key="index"
      @click="routeChange(asideItem.route,item.route)">{{item.name}}</p>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex';
  export default {
    components: {},
    data() {
      return {
      };
    },
    computed: {
      ...mapState([
        'allowShowItem',
        'asideItem'
      ]),
      showDrawer(){
        return this.allowShowItem && !(JSON.stringify(this.asideItem)=='{}' || this.asideItem.children.length==0);
      }
    },
    watch: {
      
    },
    methods: {
      routeChange(firstRoute,secondRoute){
        this.$router.push(`/${firstRoute}/${secondRoute}`);
      }
    },
    created() {
      
    },
    mounted() {
      
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .theDrawer{
    transition: all 0.1s;
    height: calc(100vh - 60px);
    background-color: @white;
    box-shadow: 1px 0 4px 0 #E7EBF2;
    left: 121px;
    top: 60px;
    z-index: 999;
    position: fixed;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    -khtml-user-select: none;
    user-select: none;
    .title{
      height: 70px;
      width: 150px;
      font-size: 15px;
      line-height: 70px;
      margin-left: 25px;
      color: @primaryText;
    }
    .item{
      height: 25px;
      width: 150px;
      font-size: 13px;
      line-height: 25px;
      margin-left: 35px;
      margin-top: 10px;
      color: @regularText;
      cursor: pointer;
    }
    .item:hover{
      color: @primaryColor;
    }
  }
</style>