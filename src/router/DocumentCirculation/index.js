const DocumentCirculation = () => import('@/views/DocumentCirculation/DocumentCirculation')

const QueryDraft = () => import('@/views/DocumentCirculation/children/QueryDraft')
const CreateDraft = () => import('@/views/DocumentCirculation/children/CreateDraft')
const DraftBox = () => import('@/views/DocumentCirculation/children/DraftBox')
const ConfirmDraft = () => import('@/views/DocumentCirculation/children/ConfirmDraft')
const CheckPendingDraft = () => import('@/views/DocumentCirculation/children/CheckPendingDraft')
const CheckDraft = () => import('@/views/DocumentCirculation/children/CheckDraft')
const CheckDraftDetail = () => import('@/views/DocumentCirculation/children/CheckDraftDetail')
const RejectDraftDetail = () => import('@/views/DocumentCirculation/children/RejectDraftDetail')
const ViewDraftList = () => import('@/views/DocumentCirculation/children/ViewDraftList')
const DraftDetail = () => import('@/views/DocumentCirculation/children/DraftDetail')

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
