const MeetingRoomManagement = () => import('@/views/MeetingRoomManagement/MeetingRoomManagement.vue')
const equipment = () => import("@/views/MeetingRoomManagement/children/equipment.vue")
const room = () => import("@/views/MeetingRoomManagement/children/room.vue")
const AppointmentApproval = () => import("@/views/MeetingRoomManagement/children/AppointmentApproval.vue")


  export default {
    path: '/meetingroom-management',
    name: 'MeetingRoomManagement',
    component: MeetingRoomManagement,
    redirect: '/meetingroom-management/equipment',
    children:[
      {
        path:'equipment',
        name:'equipment',
        component:equipment,
      },
      {
        path:'room',
        name:'room',
        component:room, 
      },
      {
        path:'appointment-approval',
        name:'AppointmentApproval',
        component:AppointmentApproval,
      }
  ]
  }