import SendFileManagement from '@/views/SendFileManagement/SendFileManagement'

import ChildrenComponent1 from '@/views/SendFileManagement/children/ChildrenComponent1'

  export default {
    path: '/send-file-management',
    name: 'SendFileManagement',
    component: SendFileManagement,
    redirect: '/sendfilemanagement/childrencomponent1',
    children:[
      {
        path: 'query-draft',
        name: 'ChildrenComponent1',
        component: ChildrenComponent1
      }
    ]
  }
