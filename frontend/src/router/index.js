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
import AdminPage from '@/views/admin/AdminPage.vue'
import PartnerManagement from '@/views/admin/PartnerManagement.vue'
import NftCreate from '@/views/admin/NftCreate.vue'
import MyNftDetail from '@/views/nft/MyNftDetail.vue'
import MyNft from '@/views/nft/MyNft.vue'
import NftWalletSearch from '@/views/nft/NftWalletSearch.vue'
import NftNumberSearch from '@/views/nft/NftNumberSearch.vue'
import NftTransfer from '@/views/nft/NftTransfer.vue'
import PartnerCreate from '@/views/admin/PartnerCreate.vue'
import NftAllCreate from '@/views/admin/NftAllCreate.vue'

import Pinata from "../components/Pinata.vue"
// import Test from "../components/test/Test.vue"
const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
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
    path: '/',
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
  {
    path: '/admin',
    name: 'AdminPage',
    component: AdminPage
  },
  {
    path: '/partner',
    name: 'PartnerManagement',
    component: PartnerManagement
  },
  {
    path: '/nftcreate',
    name: 'NftCreate',
    component: NftCreate
  },
  {
    path: '/NftAllCreate',
    name: 'NftAllCreate',
    component: NftAllCreate
  },
  {
    path: '/mynftdetail',
    name: 'MyNftDetail',
    component: MyNftDetail
  },
  {
    path: '/mynft',
    name: 'MyNft',
    component: MyNft
  },
  {
    path: '/nftwalletsearch',
    name: 'NftWalletSearch',
    component: NftWalletSearch
  },
  {
    path: '/nftnumbersearch',
    name: 'NftNumberSearch',
    component: NftNumberSearch
  },
  {
    path: '/nfttransfer',
    name: 'NftTransfer',
    component: NftTransfer
  },
  {
    path: '/pinata',
    name: 'Pinata',
    component : Pinata
  },
  {
    path: '/partnercreate',
    name: 'PartnerCreate',
    component : PartnerCreate
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
