export default{
  //****全局用户信息
  userToken:null,
  userInfo:{},
  userAuthority:{
    user:{
      name:"用户信息",
      show:false,
      role:{
        '0001':{
          name:"获取个人资料",
          own:false
        },
        '0002':{
          name:"修改个人资料",
          own:false
        }
      }
    },
    businesscardholder:{
      name:"名片夹",
      show:false,
      role:{
        '0003':{
          name:"获取个人所拥有的名片夹分类",
          own:false
        },
        '0004':{
          name:"删除名片夹分类",
          own:false
        },
        '0005':{
          name:"增加名片夹分类",
          own:false
        },
        '0006':{
          name:"修改名片夹分类名称",
          own:false
        },
        '0007':{
          name:"添加名片",
          own:false
        },
        '0008':{
          name:"修改名片",
          own:false
        },
        '0009':{
          name:"添加别人分享的名片夹",
          own:false
        },
        '000A':{
          name:"删除名片夹",
          own:false
        },
        '000B':{
          name:"查找自己所拥有的名片夹",
          own:false
        },
        '000C':{
          name:"查找自己某一分类下的名片夹",
          own:false
        },
        '000D':{
          name:"修改名片夹的分类",
          own:false
        }
      }
    },
    schedule:{
      name:"日程安排",
      show:false,
      children:{
        mine:{
          name:"我的日程",
          show:false,
          role:{}
        },
        inquiry:{
          name:"查找日程",
          show:false,
          role:{}
        },
        agent:{
          name:"日程待办",
          show:false,
          role:{}
        },
        create:{
          name:"创建日程",
          show:false,
          role:{}
        }
      }
    },
    addressbook:{
      name:"通讯录",
      show:false,
      role:{
        '000E':{
          name:"获取所有员工的通讯录",
          own:false
        },
        '000F':{
          name:"获取所有部门的部门名称",
          own:false
        },
        '000G':{
          name:"分页获取员工通讯录",
          own:false
        },
        '000H':{
          name:"按条件查询员工通讯录",
          own:false
        }
      }
    },
    todo:{
      name:"待办事项",
      show:false,
      children:{
        todolist:{
          name:"待办清单",
          show:false,
          role:{}
        },
        addition:{
          name:"添加待办",
          show:false,
          role:{}
        },
        messagelist:{
          name:"留言清单",
          show:false,
          role:{}
        },
        send:{
          name:"发送留言",
          show:false,
          role:{}
        }
      }
    },
    meetingroommanagement:{
      name:"会议室管理",
      show:false,
      children:{
        equipment:{
          name:"设施管理",
          show:false,
          role:{
            '000I':{
              name:"添加设备分类",
              own:false
            },
            '000J':{
              name:"修改设备分类信息",
              own:false
            },
            '000K':{
              name:"获取所有设备分类信息",
              own:false
            },
            '000L':{
              name:"删除设备分类",
              own:false
            },
            '000M':{
              name:"添加设备",
              own:false
            },
            '000N':{
              name:"修改设备",
              own:false
            },
            '000P':{
              name:"删除设备",
              own:false
            },
            '000Q':{
              name:"获取所有的设备信息",
              own:false
            },
            '000R':{
              name:"查找设备信息",
              own:false
            }
          }
        },
        room:{
          name:"房间管理",
          show:false,
          role:{
            '000S':{
              name:"获取所有的会议室信息",
              own:false
            },
            '000T':{
              name:"添加会议室",
              own:false
            },
            '000V':{
              name:"删除会议室",
              own:false
            },
            '000W':{
              name:"查找会议室",
              own:false
            }
          }
        },
        appointmentapproval:{
          name:"预约审批",
          show:false,
          role:{}
        }
      },
    },
    meetingmanagement:{
      name:"会议管理",
      show:false,
      role:{}
    },
    sendfilemanagement:{
      name:"发文管理",
      show:false,
      children:{
        querydraft:{
          name:"查询拟稿",
          show:false,
          role:{}
        },
        senddraft:{
          name:"发送拟稿",
          show:false,
          role:{}
        },
        distribution:{
          name:"文件分发",
          show:false,
          role:{}
        }
      }
    },
    receivefilemanagement:{
      name:"收文管理",
      show:false,
      children:{
        signforofficialdocument:{
          name:"公文签收",
          show:false,
          role:{}
        },
        officialdocumentrecord:{
          name:"收文记录",
          show:false,
          role:{}
        },
        leaderinstructions:{
          name:"领导批示",
          show:false,
          role:{}
        },
        collectionmonitoring:{
          name:"收文监控",
          show:false,
          role:{}
        },
      }
    },
    backstagemanagement:{
      name:"后台管理",
      show:false,
      children:{
        department:{
          name:"部门管理",
          show:false,
          role:{}
        },
        staff:{
          name:"员工管理",
          show:false,
          role:{}
        },
        role:{
          name:"角色管理",
          show:false,
          role:{}
        }
      }
    },
  },
<<<<<<< HEAD
  //****面包屑状态
  currentBread:[],
=======
>>>>>>> lkx
  //****全局侧边栏状态
  //侧边栏信息
  asideMenu:[
    {
      img:'card_holder',
      title:'名片夹',
      clickMark:1,
      route:'business-card-holder',
      children:[
        {
          name:'我的名片夹',
          route:'mine'
        },
        {
          name:'共享名片夹',
          route:'shared'
        }
      ]
    },
    {
      img:'schedule',
      title:'日程安排',
      clickMark:2,
      route:'schedule',
      children:[
        {
          name:'我的日程',
          route:'mine'
        },
        {
          name:'查找日程',
          route:'inquiry'
        },
        {
          name:'日程待办',
          route:'agent'
        },
        {
          name:'创建日程',
          route:'create'
        }
      ]
    },
    {
      img:'address_book',
      title:'通讯录',
      clickMark:3,
      route:'address-book',
      children:[
        {
          name:'通讯录查询',
          route:'inquiry'
        }
      ]
    },
    {
      img:'todo',
      title:'代办事项',
      clickMark:4,
      route:'to-do',
      children:[
        {
          name:'代办清单',
          route:'to-do-list'
        },
        {
          name:'添加待办',
          route:'addition'
        },
        {
          name:'留言清单',
          route:'message-list'
        },
        {
          name:'发送留言',
          route:'send'
        }
      ]
    },
    {
      img:'meetingroom',
      title:'会议室管理',
      clickMark:5,
      route:'meetingroom-management',
      children:[
        {
          name:'设施管理',
          route:'equipment'
        },
        {
          name:'房间管理',
          route:'room'
        },
        {
          name:'预约审批',
          route:'appointment-approval'
        }
      ]
    },
    {
      img:'meeting',
      title:'会议管理',
      clickMark:6,
      route:'meeting-management',
      children:[
        {
          name:'会议信息',
          route:'information'
        }
      ]
    },
    {
      img:'send_file',
      title:'发文管理',
      clickMark:8,
      route:'send-file-management',
      children:[
        {
          name:'查询拟稿',
          route:'query-draft'
        },
        {
          name:'发送拟稿',
          route:'send-draft'
        },
        {
          name:'文件分发',
          route:'distribution'
        }
      ]
    },
    {
      img:'receive_file',
      title:'收文管理',
      clickMark:9,
      route:'receive-file-management',
      children:[
        {
          name:'公文签收',
          route:'sign-for-official-document'
        },
        {
          name:'收文记录',
          route:'official-document-record'
        },
        {
          name:'领导批示',
          route:'leader-instructions'
        },
        {
          name:'收文监控',
          route:'collection-monitoring'
        }
      ]
    },
    {
      img:'backstage_management',
      title:'后台管理',
      clickMark:7,
      route:'backstage-management',
      children:[
        {
          name:'部门管理',
          route:'department'
        },
        {
          name:'员工管理',
          route:'staff'
        },
        {
          name:'角色管理',
          route:'role'
        }
      ]
    }
  ],
  //侧边栏选中信息
  asideItem:{},
  //主界面是否选中(未选中从而允许侧边栏二级菜单显示)
  allowShowItem:false,
  //被选中的侧边栏序号
  mouseClickIndex:0
}