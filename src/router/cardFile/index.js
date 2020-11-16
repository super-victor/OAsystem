import CardFile from '@/views/CardFile/CardFile'

import Mine from '@/views/CardFile/children/Mine'
import ShareCard from '@/views/CardFile/children/ShareCard'

  export default {
    path: '/business-card-holder',
    name: 'BusinessCardHolder',
    component: CardFile,
    redirect: '/businesscardholder/childrencomponent',
    children:[
      {
        path: 'mine',
        name: 'Mine',
        component: Mine
      },
      {
        path: 'shared',
        name: 'ShareCard',
        component: ShareCard
      },
    ]
  }
