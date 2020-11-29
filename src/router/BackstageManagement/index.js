import BackstageManagement from '@/views/BackstageManagement/BackstageManagement'
import DepartmentManagement from '@/views/BackstageManagement/children/DepartmentManagement'
import StaffManagement from '@/views/BackstageManagement/children/StaffManagement'
import RoleManagement from '@/views/BackstageManagement/children/RoleManagement'
import RoleAuthority from '@/views/BackstageManagement/children/RoleAuthority'

  export default {
    path: '/backstage-management',
    name: 'BackstageManagement',
    component: BackstageManagement,
    redirect: '/backstage-management/department',
    children:[
      {
        path: 'department',
        name: 'DepartmentManagement',
        component: DepartmentManagement
      },
      {
        path: 'staff/:department',
        name: 'StaffManagement',
        component: StaffManagement
      },
      {
        path: 'role',
        name: 'RoleManagement',
        component: RoleManagement
      },
      {
        path: 'role-authority',
        name: 'RoleAuthority',
        component: RoleAuthority
      }
    ]
  }