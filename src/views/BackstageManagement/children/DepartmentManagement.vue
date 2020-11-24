!<!-- DepartmentManagement -->
<template>
  <div class='DepartmentManagement'>
    <div class="departmentBox">
      <div class="companyBox"></div>
      <div class="editBox"></div>
    </div>
    <div class="addBox"></div>
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
@import '../../../style/common.less';
  .DepartmentManagement{
    height: calc(100% - 90px);
    width: 100%;
    font-size: 30px;
    display: flex;
    align-items: center;
    .departmentBox{
      height: 100%;
      width: 5.2rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .companyBox{
        height: 20%;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
      }
      .editBox{
        height: 77%;
        width: 100%;
        background-color: @white;
        border-radius: @smallBorderRadius;
      }
    }
    .addBox{
      margin-left: 25px;
      height: 100%;
      width: 11.2rem;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      background-color: @white;
      border-radius: @smallBorderRadius;
    }
  }
</style>