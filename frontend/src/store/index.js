import { createStore } from 'vuex'
import { auth } from "./auth.module";
import createPersistedState from "vuex-persistedstate";
export default createStore({
  plugins: [createPersistedState()],
  state: {
   nftValues : [],
   searchednft : [],
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
  },
  actions: {
    nftValues({commit},data) {
      commit("NFT_VALUES",data)
    },
    searchWallet({commit},data) {
      commit("SEARCH_WALLET",data)
    }
  },
  modules: {
    auth
  }
})
