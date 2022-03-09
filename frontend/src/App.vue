<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/>
</template>

<script>
import EventBus from "/common/EventBus"
import {onMounted, onUnmounted} from 'vue'
import {useStore} from 'vuex'
import {useRouter} from 'vue-router'
export default {
  setup() {
    const store = useStore()
    const router = useRouter()
    onMounted(() => {
      EventBus.on("logout", () => {
      logOut()
      })
    })

    onUnmounted(() => {
      EventBus.remove("logout");
    });

    const logOut = () => {
      console.log('로그아웃')
      console.log(23)
      if (window.Kakao.Auth.getAccessToken()) {
        console.log('in')
        window.Kakao.API.request({
          url: '/v1/user/unlink',
          success: function (response) {
            console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
        window.Kakao.Auth.setAccessToken(undefined)
      }
      console.log('out')
      store.dispatch('auth/logout');
      router.push('/login');
    }


    return {
      logOut,
      store,
      router,
    }
  }
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
