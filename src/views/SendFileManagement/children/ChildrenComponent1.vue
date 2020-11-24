!<!-- ChildrenComponent1 -->
<template>
  <div class='ChildrenComponent1'>
    <p>{{sendFileManagementInfo}}</p>
    <p>这是子路由组件，对应二级菜单中的每一个功能页面，这里面开始书写当前页面内容，该div宽度需为100%，高度可根据页面自定，这个子路由div的高度不超过父组件的高度，不然会出现bug，实际书写时使用组件名替代该div的class名</p>
    <button @click="update">改变模块中的vuex数据</button>
  </div>
</template>

<script>
  import {mapState,mapMutations} from 'vuex'
  export default {
    components: {},
    data() {
      return {
        
      };
    },
    computed: {
      ...mapState('sendFileManagement',[
        'sendFileManagementInfo'
      ])
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
      ...mapMutations('sendFileManagement',[
        'UPDATE_FILEMANAGEMENTINFO'
      ]),
      update(){
        this.UPDATE_FILEMANAGEMENTINFO(333);
      }
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['发文管理','查询拟稿']);
    }
  }
</script>
<style lang='less' scoped>
  .ChildrenComponent1{
    height: 500px;
    width: 100%;
    background-color: salmon;
    font-size: 30px;
  }
</style>