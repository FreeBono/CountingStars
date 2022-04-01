import { createStore } from 'vuex'
import { auth } from "./auth.module";
import createPersistedState from "vuex-persistedstate";
export default createStore({
  plugins: [createPersistedState()],
  state: {
  nftValues : [],
  searchednft : [],
  noticeNo: null,
  userInfo : [],
  walletInfo : [],
  blockEvent : [],
  },
  ipfsData : [],
  getters: {
  },
  mutations: {
    NFT_VALUES(state,data) {
      console.log(data)
      state.nftValues = data
    },
    SEARCH_WALLET(state,data) {
      console.log(data, '데이터 확인')
      state.searchednft = data
    },
    GET_NOTICE_NO(state, noticeNumber){
      state.noticeNo = noticeNumber
    },
    SEND_TOKEN(state,data) {
      console.log('실행')
      state.nftValues = state.nftValues.filter(e => e.tokenId != data)
      // for (let i = 0; i < state.nftValues.length; i++) {
      //   if (state.nftValues[i].tokenId === data) {
      //     state.nftValues[i].status = 1
      //     console.log(state.nftValues[i])
      //   }
      // }
      console.log(state.nftValues)
    },
    SET_USER(state,data) {
      state.userInfo = data
    },
    SET_WALLET(state,data) {
      state.walletInfo = data
    },
    SET_BLOCKEVENT(state,data) {
      state.blockEvent = data
    },
    SET_IPFS(state,data) {
      console.log(data)
      state.ipfsData = data
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
    sendToken({commit}, data) {
      commit("SEND_TOKEN",data)
    },
    setUser({commit}, data) {
      commit("SET_USER",data)
    },
    setWallet({commit}, data) {
      commit("SET_WALLET",data)
    },
    setBlockevent({commit}, data) {
      commit("SET_BLOCKEVENT",data)
    },
    setIpfs({commit}, data) {
      commit("SET_IPFS",data)
    },


  },
  modules: {
    auth
  }
})