import AddressBook from '@/views/AddressBook/AddressBook.vue'

import ChildComponent from "@/views/AddressBook/children/ChildComponent.vue";


  export default {
    path: '/address-book',
    name: 'AddressBook',
    component: AddressBook,
    redirect: '/address-book/inquiry',
    children:[
      {
      path:'/address-book/inquiry',
      name:'ChildrenComponent',
      component:ChildComponent,
      }
  ]
  }