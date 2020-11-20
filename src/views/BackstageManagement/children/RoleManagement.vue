!<!-- RoleManagement -->
<template>
  <div class='RoleManagement'>

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
      this.$emit('childrenBread',['角色管理']);
    }
  }
</script>
<style lang='less' scoped>
  .RoleManagement{
    height: 500px;
    width: 100%;
    background-color: salmon;
    font-size: 30px;
  }
</style>