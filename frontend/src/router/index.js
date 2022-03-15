import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "../views/Login.vue"
import Signup from "../views/Signup.vue"
import Notification from "../views/accounts/Notification.vue"
import SimpleSignup from "../views/accounts/SimpleSignup.vue"
import AdditionInfo from "../views/accounts/AdditionInfo.vue"
import Mainpage from "../views/Mainpage.vue"
import MainNotice from '@/views/notice/MainNotice.vue'
import NoticeForm from '@/components/notice/NoticeForm.vue'
import NoticeDetail from '@/components/notice/NoticeDetail.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component : Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component : Signup
  },
  {
    path: '/notification',
    name: 'Notification',
    component : Notification
  },
  {
    path: '/simplesignup',
    name: 'SimpleSignup',
    component : SimpleSignup
  },
  {
    path: '/additioninfo',
    name: 'AdditionInfo',
    component : AdditionInfo
  },
  {
    path: '/main',
    name: 'Mainpage',
    component : Mainpage
  },
  {
    path: '/notice',
    name: 'MainNotice',
    component: MainNotice
  },
  {
    path: '/noticeform',
    name: 'NoticeForm',
    component: NoticeForm
  },
  {
    path: '/noticedetail',
    name: 'NoticeDetail',
    component: NoticeDetail
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
