import Vue from 'vue'
import Store from '@/store/index'
import VueRouter from 'vue-router'
import UserLogin from '@/views/Login/UserLogin'
import HomePage from '@/views/Home/HomePage'
import Administrator from '@/views/Administrator/Administrator'

Vue.use(VueRouter)

//动态加载路由模块
function createModules(arr,list){
  arr.keys().forEach(key=>{
    list.push(arr(key).default);
  })
}

const routerList = [];
let matches = require.context('./', true, /^\.\/[^/]+\/.+\.js$/);

createModules(matches,routerList);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/login',
    name: 'UserLogin',
    component: UserLogin
  },
  {
    path: '/administrator',
    name: 'Administrator',
    component: Administrator
  },
  ...routerList
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//全局钩子函数，处理用户登录情况不同时的页面跳转
router.beforeEach((to,from,next)=>{
  if(to.path==='/login' && Store.state.userRole=='administrator'){
    next();
  }
  else if(to.path==='/administrator' && Store.state.userRole!='administrator'){
    next({path:'/'});
  }
  else if(to.path!=='/administrator' && Store.state.userRole=='administrator'){
    next({path:'/administrator'});
  }
  else if(Store.state.userToken!==null){
    if(to.path==='/login'){
      next({path:'/'});
    }else{
      next();
    }
  }else{
    if(to.path==='/login'){
      next();
    }else{
      next({
        path:'/login',
        query:{
          redirect:to.path
        }
      })
    }
  }
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
 return originalPush.call(this, location).catch(err => err);
}

export default router
