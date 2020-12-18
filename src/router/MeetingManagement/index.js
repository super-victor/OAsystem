import MeetingManagement from '@/views/MeetingManagement/MeetingManagement.vue'

import MeetingMessage from "@/views/MeetingManagement/children/MeetingMessage.vue";


  export default {
    path: '/meeting-management',
    name: 'MeetingManagement',
    component: MeetingManagement,
    redirect: '/meeting-management/information',
    children:[
      {
      path:'/meeting-management/information',
      name:'MeetingMessage',
      component:MeetingMessage,
      }
  ]
  }