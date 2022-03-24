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
    SEND_TOKEN(state,data) {
      console.log('실행')
      for (let i = 0; i < state.nftValues.length; i++) {
        if (state.nftValues[i].tokenId === data) {
          state.nftValues[i].status = 1
          console.log(state.nftValues[i])
        }
      }
      console.log(state.nftValues)
    }
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
    sendToken({commit}, data) {
      commit("SEND_TOKEN",data)
    }

  },
  modules: {
    auth
  }
})
