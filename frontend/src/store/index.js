import { createStore } from 'vuex'
import { auth } from "./auth.module";
import createPersistedState from "vuex-persistedstate";
export default createStore({
  plugins: [createPersistedState()],
  state: {
   nftValues : []
  },
  getters: {
  },
  mutations: {
    NFT_VALUES(state,data) {
      console.log(data)
      state.nftValues = data
    },
  },
  actions: {
    nftValues({commit},data) {
      commit("NFT_VALUES",data)
    }
  },
  modules: {
    auth
  }
})
