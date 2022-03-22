import { createStore } from 'vuex'
import { auth } from "./auth.module";
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  plugins: [
    createPersistedState({ storage: window.sessionStorage })
  ],
  state: {
    noticeNo: null,
  },
  getters: {
  },
  mutations: {
    GET_NOTICE_NO(state, noticeNumber){
      state.noticeNo = noticeNumber
    },
  },
  actions: {
    getNoticeNo({commit}, noticeNumber) {
      commit('GET_NOTICE_NO', noticeNumber)
    },
  },
  modules: {
    auth
  }
})
