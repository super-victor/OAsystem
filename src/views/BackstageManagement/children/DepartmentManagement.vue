!<!-- DepartmentManagement -->
<template>
  <div class='DepartmentManagement'>

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
      this.$emit('childrenBread',['部门管理']);
    }
  }
</script>
<style lang='less' scoped>
  .DepartmentManagement{
    height: 500px;
    width: 100%;
    background-color: salmon;
    font-size: 30px;
  }
</style>