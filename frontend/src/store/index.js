import { createStore } from 'vuex'
import { auth } from "./auth.module";
import createPersistedState from "vuex-persistedstate";
export default createStore({
  plugins: [createPersistedState()],
  state: {
   nftValues : [],
   searchednft : [],
   noticeNo: null,
  },
  getters: {
  },
  mutations: {
    NFT_VALUES(state,data) {
      console.log(data)
      state.nftValues = data
    },
    SEARCH_WALLET(state,data) {
      console.log(data)
      state.searchednft = data
    },
    GET_NOTICE_NO(state, noticeNumber){
      state.noticeNo = noticeNumber
    },
  },
  actions: {
    nftValues({commit},data) {
      commit("NFT_VALUES",data)
    },
    searchWallet({commit},data) {
      commit("SEARCH_WALLET",data)
    },
    getNoticeNo({commit}, noticeNumber) {
      commit('GET_NOTICE_NO', noticeNumber)
    },

  },
  modules: {
    auth
  }
})
