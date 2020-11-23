!<!-- StaffManagement -->
<template>
  <div class='StaffManagement'>
    <div class="departmentBox"></div>
    <div class="roleBox"></div>
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
      this.$emit('childrenBread',['员工管理']);
    }
  }
</script>
<style lang='less' scoped>
  .StaffManagement{
    height: calc(100% - 90px);
    width: 100%;
    background-color: salmon;
    font-size: 30px;
    display: flex;
    align-items: center;
    justify-content: space-around;
    .departmentBox{
      height: 90%;
      width: 5rem;
      background-color: aqua;
    }
    .roleBox{
      height: 90%;
      width: 10rem;
      background-color: aquamarine;
    }
  }
</style>