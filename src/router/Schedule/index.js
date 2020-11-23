import Schedule from '@/views/Schedule/Schedule'
import Mine from '@/views/Schedule/children/Mine'
import Inquiry from '@/views/Schedule/children/Inquiry'
import Create from '@/views/Schedule/children/Create'

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
            path:'inquiry',
            name:'Inquiry',
            component:Inquiry
        },
        {
            path:'create',
            name:'CreateSchedule',
            component:Create
        }
    ]
  }
