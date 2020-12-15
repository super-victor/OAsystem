!<!-- CheckLogs -->
<template>
  <div class='CheckLogs'>
    <div class='TheTitle'><i class="el-icon-document" style="margin-right:5px;"></i>日志管理</div>
    <div class="buttonBox">
      <el-radio-group v-model="checkboxGroup" size="small" @change="change">
        <el-radio-button label="全部"></el-radio-button>
        <el-radio-button label="操作日志"></el-radio-button>
        <el-radio-button label="系统日志"></el-radio-button>
      </el-radio-group>
    </div>
    <div class="logBox" v-loading="loading">
      <div 
      class="log"
      v-for="(item,index) in currentLogArr"
      :key="index"
      :style="{'margin-top':index==0?'0':'20px'}"
      >
        <div class="titleBox">
          <p class="level" :style="{'background-color':item.level=='ERROR'?'#F56C6C':'#E6A23C'}">{{item.level}}</p>
          <p class="time">{{item.createTime}}</p>
        </div>
        <div class="textBox">
          <p class="type"><span style="font-weight:bolder;color:#303133;">种类：</span>{{item.type}}</p>
          <p class="text"><span style="font-weight:bolder;color:#303133;">详情：</span>{{item.content || '无'}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import administratorAPI from '@/service/Administrator';
  export default {
    components: {},
    data() {
      return {
        logArr:[],
        currentLogArr:[],
        checkboxGroup:'全部',
        loading:true
      };
    },
    computed: {},
    watch: {},
    methods: {
      change(e){
        if(e=='全部'){
          this.currentLogArr = this.logArr;
        }else if(e=='操作日志'){
          this.currentLogArr = this.logArr.filter(item=>item.level=='INFO');
        }else{
          this.currentLogArr = this.logArr.filter(item=>item.level=='ERROR');
        }
      }
    },
    created() {
      
    },
    mounted() {
      administratorAPI.getAllLogs()
      .then(res=>{
        let arr = res.object.sort((a,b)=>{
          return b.createTime < a.createTime ? -1 : 1;
        })
        for(let item of arr){
          if(item.level=='INFO ') item.level='INFO';
        }
        this.logArr = arr;
        this.currentLogArr = arr;
        this.loading = false;
      })
      .catch(err=>{
        this.$message.error('获取日志失败');
      })
    }
  }
</script>
<style lang='less' scoped>
  @import '../../../style/common.less';
  .CheckLogs{
    height: 100%;
    width: 49%;
    background-color: @white;
    border-radius: @baseBorderRadius;
    box-shadow: 0 0 13px 0 rgba(82, 63, 105, 0.05);
    .TheTitle{
      height: 50px;
      width: 130px;
      box-sizing: border-box;
      background-color: @primaryColor;
      border-bottom-right-radius: 10px;
      color: @white;
      border-right: 3px solid @correlateColor1;
      border-bottom: 3px solid @correlateColor1;
      font-size: 18px;
      line-height: 50px;
      text-align: center;
      user-select: none;
    }
    .buttonBox{
      height: 70px;
      width: 90%;
      margin-left: 5%;
      display: flex;
      align-items: center;
    }
    .logBox{
      margin-top: 10px;
      height: 450px;
      width: 90%;
      margin-left: 5%;
      overflow-y: scroll;
      .log{
        height: 162px;
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        background-color: #F2FAFF;
        border-radius: @baseBorderRadius;
        .titleBox{
          margin-top: 10px;
          height: 25px;
          width: 90%;
          display: flex;
          justify-content: space-between;
          line-height: 25px;
          .level{
            height: 100%;
            width: 50px;
            text-align: center;
            font-size: 12px;
            border-radius: @baseBorderRadius;
            font-weight: bolder;
            color: @white;
          }
          .time{
            height: 100%;
            width: 300px;
            font-size: 12px;
            text-align: right;
            color: @secondaryText;
          }
        }
        .textBox{
          height: 110px;
          width: 90%;
          margin-top: 7px;
          background-color: @white;
          .type{
            margin-top: 7px;
            height: 20px;
            width: 100%;
            font-size: 10px;
            padding: 0 10px;
            box-sizing: border-box;
            color: @correlateColor4;
          }
          .text{
            margin-top: 7px;
            height: 70px;
            width: 100%;
            word-break:break-all;
            font-size: 10px;
            padding: 0 10px;
            box-sizing: border-box;
            color: @correlateColor4;
          }
        }
      }
    }
    .logBox::-webkit-scrollbar{
      display: none;
    }
  }
</style>