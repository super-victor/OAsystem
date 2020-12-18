import MeetingRoomManagement from '@/views/MeetingRoomManagement/MeetingRoomManagement.vue'

import equipment from "@/views/MeetingRoomManagement/children/equipment.vue";
import room from "@/views/MeetingRoomManagement/children/room.vue";
import AppointmentApproval from "@/views/MeetingRoomManagement/children/AppointmentApproval.vue";


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