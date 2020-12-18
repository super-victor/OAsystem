const BackstageManagement = () => import('@/views/BackstageManagement/BackstageManagement')
const DepartmentManagement = () => import('@/views/BackstageManagement/children/DepartmentManagement')
const StaffManagement = () => import('@/views/BackstageManagement/children/StaffManagement')
const RoleManagement = () => import('@/views/BackstageManagement/children/RoleManagement')
const RoleAuthority = () => import('@/views/BackstageManagement/children/RoleAuthority')

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