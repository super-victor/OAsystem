import DocumentCirculation from '@/views/DocumentCirculation/DocumentCirculation'

import QueryDraft from '@/views/DocumentCirculation/children/QueryDraft'
import CreateDraft from '@/views/DocumentCirculation/children/CreateDraft'
import DraftBox from '@/views/DocumentCirculation/children/DraftBox'
import ConfirmDraft from '@/views/DocumentCirculation/children/ConfirmDraft'
import CheckPendingDraft from '@/views/DocumentCirculation/children/CheckPendingDraft'
import CheckDraft from '@/views/DocumentCirculation/children/CheckDraft'
import CheckDraftDetail from '@/views/DocumentCirculation/children/CheckDraftDetail'
import RejectDraftDetail from '@/views/DocumentCirculation/children/RejectDraftDetail'
import ViewDraftList from '@/views/DocumentCirculation/children/ViewDraftList'
import DraftDetail from '@/views/DocumentCirculation/children/DraftDetail'

  export default {
    path: '/document-circulation',
    name: 'DocumentCirculation',
    component: DocumentCirculation,
    redirect: '/document-circulation/query-draft',
    children:[
      {
        path: 'query-draft',
        name: 'QueryDraft',
        component: QueryDraft
      },
      {
        path: 'create-draft',
        name: 'CreateDraft',
        component: CreateDraft
      },
      {
        path: 'draft-details/:sendfileId',
        name: 'DraftBox',
        component: DraftBox
      },
      {
        path: 'confirm-draft/:sendfileId',
        name: 'ConfirmDraft',
        component: ConfirmDraft
      },
      {
        path: 'check-pending-draft/:sendfileId',
        name: 'CheckPendingDraft',
        component: CheckPendingDraft
      },
      {
        path: 'check-draft',
        name: 'CheckDraft',
        component: CheckDraft
      },
      {
        path: 'check-draft-detail/:sendfileId',
        name: 'CheckDraftDetail',
        component: CheckDraftDetail
      },
      {
        path: 'reject-draft-detail/:sendfileId',
        name: 'RejectDraftDetail',
        component: RejectDraftDetail
      },
      {
        path: 'view-draft-list',
        name: 'ViewDraftList',
        component: ViewDraftList
      },
      {
        path: 'draft-detail/:sendfileId',
        name: 'DraftDetail',
        component: DraftDetail
      },
    ]
  }
