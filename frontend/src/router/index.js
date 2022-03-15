import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "../views/Login.vue"
import Signup from "../views/Signup.vue"
import Notification from "../views/accounts/Notification.vue"
import SimpleSignup from "../views/accounts/SimpleSignup.vue"
import AdditionInfo from "../views/accounts/AdditionInfo.vue"
import Mainpage from "../views/Mainpage.vue"
// import Test from "../components/test/Test.vue"
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // {
  //   path: '/test',
  //   name: 'Test',
  //   component: Test
  // },
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
