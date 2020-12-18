const MeetingManagement = () => import('@/views/MeetingManagement/MeetingManagement.vue')
const MeetingMessage = () => import("@/views/MeetingManagement/children/MeetingMessage.vue")


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