<template>
	<div class="item">
    <FullCalendar class="fullCalendar" ref="fullCalendar" :options="calendarOptions"/>
    <el-dialog title="日程信息" :visible.sync="dialogVisible1" class="dialog">
      <div class="row" v-if="type==='company'">
        <p class="in_label">发起人</p>
        <p>{{info.leader}}</p>
      </div>
      <div class="row">
        <p class="in_label">日程内容</p>
        <p>{{info.content}}</p>
      </div>
      <div class="row" v-if="type==='company'">
        <p class="in_label">参与人员</p>
        <p>{{joinerName}}</p>
      </div>
      <div class="row">
        <p class="in_label">日程地点</p>
        <p>{{info.location}}</p>
      </div>
      <div class="row">
        <p class="in_label">开始时间</p>
        <p>{{info.startTime}}</p>
      </div>
      <div class="row">
        <p class="in_label">结束时间</p>
        <p>{{info.endTime}}</p>
      </div>
      <div slot="footer" class="dialog-footer" v-if="type==='mine'">
        <el-button type="primary" @click="dialogVisible2=true">修改</el-button>
        <el-button type="danger" @click="deleteInfo()">删除</el-button>
      </div>
    </el-dialog>
    <el-dialog title="日程信息" :visible.sync="dialogVisible2" class="dialog">
      <el-form ref="form" :model="info" :rules="rules" label-width="80px">
        <el-form-item label="日程内容" prop="content">
          <el-input v-model="info.content" placeholder="请输入日程内容" class="input"></el-input>
        </el-form-item>
        <el-form-item label="日程地点" prop="location">
          <el-input v-model="info.location" placeholder="请输入日程地点" class="input"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="info.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker
            v-model="info.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateInfo()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import timeGridPlugin from '@fullcalendar/timegrid'
import scheduleAPI from '@/service/schedule'

export default {
  components: {
    FullCalendar  // 像使用自定义组件一样使用
  },
  props:['events','type'],
  data() {
    return {
      scheduleId:0,
      dialogVisible1:false,
      dialogVisible2:false,
      calendarApi: null,
      update:true,
      info:[],
      joinerName:[],
      rules:{
        content:[
          { required: true, message: '请输入日程内容', trigger: 'blur' }
        ],
        location:[
          { required: true, message: '请输入日程地点', trigger: 'blur' }
        ],
        startTime:[
          { required: true, message: '请输入开始时间', trigger: 'blur' }
        ],
        endTime:[
          { required: true, message: '请输入结束时间', trigger: 'blur' }
        ]
      },
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin, timeGridPlugin ], // 需要用哪个插件引入后放到这个数组里
        initialDate: new Date(), // 日历第一次加载时显示的初始日期。可以解析为Date的任何职包括ISO8601日期字符串，例如"2014-02-01"。
        initialView: 'dayGridMonth', // 日历加载时的初始视图，默认值为'dayGridMonth'，可以为任何可用视图的值，如例如'dayGridWeek'，'timeGridDay'，'listWeek'
        locale: 'zh-cn', // 设置日历的语言，中文为 “zh-cn”
        firstDay: '1', // 设置每周的第一天，默认值取决于当前语言环境，该值为代表星期几的数字，且值必须为整数，星期日=0
        weekNumberCalculation: 'ISO', // 指定"ISO"结果为ISO8601周数。指定"ISO"将firstDay的默认值更改为1（Monday）
        buttonText: { // 文本将显示在headerToolbar / footerToolbar的按钮上。不支持HTML注入。所有特殊字符将被转义。
          today: '今天',
          month: '月',
          week: '周',
          day: '天'
        },
        headerToolbar: { // 在日历顶部定义按钮和标题。将headerToolbar选项设置为false不会显示任何标题工具栏。可以为对象提供属性start/center/end或left/center/right。这些属性包含带有逗号/空格分隔值的字符串。用逗号分隔的值将相邻显示。用空格分隔的值之间会显示一个很小的间隙。
          right: 'today prev,next',
          center: 'title',
          left: 'dayGridMonth,dayGridWeek,dayGridDay'
        },
        eventTimeFormat: { // 在每个事件上显示的时间的格式
          hour: 'numeric',
          minute: '2-digit',
          hour12: false
        },
        events: [{ // 将在日历上显示的事件对象， events 可以是数组、json、函数。具体可以查看官方文档
            title: '聚餐',
            id:4,
            start: '2020-12-01 16:39:01',
            end: '2020-12-05 20:00:00', // 这里要注意，end为可选参数，无end参数时该事件仅在当天展示
            backgroundColor: '#FDEBC9', // 该事件的背景颜色
            borderColor: '#FDEBC9', // 该事件的边框颜色
            textColor: '#F9AE26' // 该事件的文字颜色
        }],
        customButtons: { // 定义可在headerToolbar / footerToolbar中使用的自定义按钮
          today: {
            text: '今天', // 按钮的展示文本
            click: this.todayClick // 点击按钮触发的事件，这里要注意的是当按钮绑定了事件之后该按钮原本自带的事件将会失效
          },
          next: {
            click: this.nextClick
          },
          prev: {
            click: this.prevClick
          }
        },
        eventClick: this.handleDateClick, // 点击事件时，触发该回调
        // eventMouseEnter: this.handleMouseEnter, // 鼠标悬停在事件上时，触发该回调
        // eventMouseLeave: this.handleMouseLeave, // 鼠标移除时，触发该回调
        dateClick: this.handleDateClick // 当用户单击日期或时间时,触发该回调，触发此回调，您必须加载interaction插件
      },
    }
  },
  mounted() {
    // 这里有两点要注意，想要调用插件的方法，要在组件上设置ref
    // 并且在组件未加载的时候this.$refs中是没有fullCalendar的，所以未加载的时候调用方法会报错
    this.calendarApi = this.$refs.fullCalendar.getApi();
  },
  methods: {
    // 点击日程触发的事件
    handleDateClick(dateClickInfo) {
      // console.log(dateClickInfo);
      if (dateClickInfo.event) {
        this.scheduleId = parseInt(dateClickInfo.event.id);
        // console.log(dateClickInfo.event);
        scheduleAPI.getSchedule({
          scheduleId:this.scheduleId
        })
        .then(res=>{
          this.dialogVisible1 = true;
          this.info = res.object;
          res.object.joiner.forEach(element => {
            this.joinerName.push(element.name);
          });
          // console.log(res.object.joiner);
        })
        .catch(err=>{
          console.log(err);
        })
      }
    },
    // 修改个人日程
    updateInfo() {
      console.log(this.info.endTime);
      scheduleAPI.updateSelfSchedule({
        content:this.info.content,
        startTime:this.info.startTime,
        endTime:this.info.endTime,
        location:this.info.location,
        scheduleId:this.scheduleId
      })
      .then(res=>{
        this.dialogVisible1=this.dialogVisible2=false;
        // this.$router.go(0);
        this.$message.success('修改成功');
      })
      .catch(err=>{
        console.log(err);
        this.$message.error('修改失败');
      })
    },
    // 删除个人日程
    deleteInfo() {
      scheduleAPI.deleteSelfSchedule({
        scheduleId:this.scheduleId
      })
      .then(res=>{
        this.dialogVisible1=this.dialogVisible2=false;
        this.$router.go(0);
        this.$message.success('删除成功');
      })
      .catch(err=>{
        this.$message.error('删除失败');
      })
    },
    todayClick(mouseEvent, htmlElement) {
      this.calendarApi.today() // 将日历移动到当前日期。
      this.calendarApi.getDate() // 返回日期的日历的当前日期
    },
    nextClick(mouseEvent, htmlElement) {
      this.calendarApi.next() // 将日历向前移动一步（例如，一个月或一周）。
      // 如果dayGridMonth查看日历，则将日历向前移动一个月。
      // 如果日历位于dayGridWeek或中timeGridWeek，则将日历向前移动一周。
      // 如果日历位于dayGridDay或中timeGridDay，则将日历向前移动一天。
    },
    prevClick(mouseEvent, htmlElement) {
      this.calendarApi.prev() // 将日历后退一步（例如，一个月或一周）。
      // 如果dayGridMonth查看日历，则将日历后移一个月。
      // 如果日历位于dayGridWeek或中timeGridWeek，则将日历后移一周。
      // 如果日历位于dayGridDay或中timeGridDay，则将日历移回一天。
    },
  },
  created() {
    this.calendarOptions.events = this.events;
  }
}
</script>

<style lang="less" scoped>
.item {
  .dialog .el-dialog__body{
    .row {
      .in_label {
        font-weight: bold;
        width: 80px;
      }
      display: flex;
      flex-direction: row;
      margin-bottom: 10px;
    }
  }
/deep/.fc-daygrid-event{
  cursor: pointer;
}
/deep/.fc-daygrid-event :hover{
  opacity: .8;
}
/deep/.fc .fc-toolbar > * > * {
  button {
    background-color: #578BFA;
    border-color: #4A76D4;
  }
}
/deep/.fc th, .fc td{
  font-weight: normal;
  color: #565A66;
}
/deep/.fc-state-default{
  background: #fff;
  box-shadow:none;
  border-color: #D9D9D9;
  color:#000;
  font-size: 14px;
}
}
</style>