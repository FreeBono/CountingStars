import { createStore } from 'vuex'
import { auth } from "./auth.module";

export default createStore({
  state: {
    noticeNo: null,
  },
  getters: {
  },
  mutations: {
    GET_NOTICE_NO(state, noticeNumber){
      state.noticeNo = noticeNumber
      console.log(noticeNumber, '번호 왔? 뮤테')
      console.log(state.noticeNo, '번호 왔? 뮤테22')
    },
  },
  actions: {
    getNoticeNo({commit}, noticeNumber) {
      commit('GET_NOTICE_NO', noticeNumber)
      console.log(noticeNumber, '번호 왔? 액션')
    },
  },
  modules: {
    auth
  }
})
