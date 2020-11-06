!<!-- Aside -->
<template>
  <div class='aside'>
    <the-aside-menu :asideMenu="asideMenu"></the-aside-menu>
  </div>
</template>

<script>
import TheAsideMenu from '@/components/control/TheAsideMenu';
  export default {
    components: {
      TheAsideMenu
    },
    data() {
      return {
        asideMenu:[
          {
            img:'card_holder',
            title:'名片夹',
            clickMark:1
          },
          {
            img:'schedule',
            title:'日程安排',
            clickMark:2
          },
          {
            img:'address_book',
            title:'通讯录',
            clickMark:3
          },
          {
            img:'todo',
            title:'代办事项',
            clickMark:4
          },
          {
            img:'meetingroom',
            title:'会议室管理',
            clickMark:5
          },
          {
            img:'meeting',
            title:'会议管理',
            clickMark:6
          },
          {
            img:'backstage_management',
            title:'后台管理',
            clickMark:7
          },
          {
            img:'send_file',
            title:'发文管理',
            clickMark:8
          },
          {
            img:'receive_file',
            title:'收文管理',
            clickMark:9
          }
        ]
      };
    },
    computed: {},
    watch: {},
    methods: {
    },
    created() {
      
    },
    mounted() {
    }
  }
</script>
<style lang='less' scoped>
@import '../../style/common.less';
  .aside{
    background-color: @white;
    overflow: hidden;
    height: 92.7vh;
    overflow-y: scroll;
  }
</style>