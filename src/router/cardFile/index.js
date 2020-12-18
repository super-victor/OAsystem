import CardFile from '@/views/CardFile/CardFile'

import ShareCard from '@/views/CardFile/children/ShareCard'

  export default {
    path: '/business-card-holder',
    name: 'BusinessCardHolder',
    component: CardFile,
    redirect: '/businesscardholder/childrencomponent',
    children:[
      {
        path: 'shared',
        name: 'ShareCard',
        component: ShareCard
      },
    ]
  }
