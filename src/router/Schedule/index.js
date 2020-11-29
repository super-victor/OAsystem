import Schedule from '@/views/Schedule/Schedule'
import Mine from '@/views/Schedule/children/Mine'
import CompanySchedule from '@/views/Schedule/children/CompanySchedule'
import CreateSchedule from '@/views/Schedule/children/CreateSchedule'
import ManagementSchedule from '@/views/Schedule/children/ManagementSchedule'

  export default {
    path: '/schedule',
    name: 'Schedule',
    component: Schedule,
    redirect: '/schedule/childrencomponent',
    children:[
        {
            path:'mine',
            name:'Mine',
            component:Mine
        },
        {
            path:'company-schedule',
            name:'CompanySchedule',
            component:CompanySchedule
        },
        {
            path:'create-schedule',
            name:'CreateSchedule',
            component:CreateSchedule
        },
        {
            path:'management-schedule',
            name:'ManagementSchedule',
            component:ManagementSchedule
        }
    ]
  }
