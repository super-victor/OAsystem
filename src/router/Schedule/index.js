const Schedule = () => import('@/views/Schedule/Schedule')
const Mine = () => import('@/views/Schedule/children/Mine')
const CompanySchedule = () => import('@/views/Schedule/children/CompanySchedule')
const CreateSchedule = () => import('@/views/Schedule/children/CreateSchedule')
const ManagementSchedule = () => import('@/views/Schedule/children/ManagementSchedule')

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
            path:'schedule-management',
            name:'ManagementSchedule',
            component:ManagementSchedule
        }
    ]
  }
