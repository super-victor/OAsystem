!<!-- ChildrenComponent1 -->
<template>
  <div class='ChildrenComponent1'>
  </div>
</template>

<script>
  export default {
    components: {},
    data() {
      return {
        
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
      this.$emit('childrenBread',['查找日程']);
    }
  }
</script>
<style lang='less' scoped>
  .ChildrenComponent1{
    height: 500px;
    width: 100%;
    font-size: 30px;
  }
</style>