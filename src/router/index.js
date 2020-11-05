import Vue from 'vue'
import VueRouter from 'vue-router'
import Example from '@/views/ExamplePage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Example',
    component: Example
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
