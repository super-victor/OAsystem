!<!-- ChildrenComponent1 -->
<template>
  <div class='CreateSchedule'>
    <div class="center flex-col">
      <p class="title">新建日常</p>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="日程类型" prop="type" style="height:60px;width:500px">
          <el-select :v-model="form.type">
            <el-option
            v-for="item in types"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日程内容" prop="comment" style="height:60px;width:500px">
          <el-input :v-model="form.comment"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime" style="height:60px;width:500px">
          <el-input :v-model="form.startTime"></el-input>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime" style="height:60px;width:500px">
          <el-input :v-model="form.endTime"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="button" @click="submitForm(form)">提交信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
import ScheduleApi from '@/service/schedule';
  export default {
    components: {},
    data() {
      return {
        types:[{
          id:1,
          name:'聚餐'
        },{
          id:2,
          name:'会议'
        },{
          id:3,
          name:'合同签署'
        },{
          id:4,
          name:'项目'
        },{
          id:5,
          name:'采购'
        },{
          id:6,
          name:'资金流转'
        },{
          id:7,
          name:'出差'
        },{
          id:8,
          name:'其它'
        }],
        form:[],
        rules:{
          type:[
            { required: true, message: '请选择日程类型', trigger: 'blur' }
          ],
          startTime:[
            { required: true, message: '请输入开始时间', trigger: 'blur' }
          ],
          endTime:[
            { required: true, message: '请输入结束时间', trigger: 'blur' }
          ]
        }
      };
    },
    computed: {
    },
    watch: {},
    methods: {
      ...mapMutations(['UPDATE_BREAD']),
    },
    created() {
      
    },
    mounted() {
      this.UPDATE_BREAD(['日程安排','新建日常']);
    }
  }
</script>
<style lang='less' scoped>
@import '../../../style/common.less';
  .CreateSchedule{
    height: 500px;
    width: 60%;
    font-size: 0.2rem;
    color: @regularText;
    .center {
      padding: 50px;
      border-radius: @baseBorderRadius;
      background-color: @white;
      p.title {
        color: @primaryText;
        font-weight: bold;
        font-size: 0.25rem;
        margin-bottom: 0.3rem;
      }
      .button{
        margin-top: 30px;
        height: 40px;
        width: 100px;
      }
    }
  }
</style>