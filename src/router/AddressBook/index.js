import AddressBook from '@/views/AddressBook/AddressBook.vue'

import AddressBookSelect from "@/views/AddressBook/children/AddressBookSelect.vue";


  export default {
    path: '/address-book',
    name: 'AddressBook',
    component: AddressBook,
    redirect: '/address-book/inquiry',
    children:[
      {
      path:'/address-book/inquiry',
      name:'AddressBookSelect',
      component:AddressBookSelect,
      }
  ]
  }