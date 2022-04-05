<template>

  <router-view/>
  
  <kakao-chatbot/>
</template>

<script>
import EventBus from "/common/EventBus"
import {onMounted, onUnmounted} from 'vue'
import {useStore} from 'vuex'
import {useRouter} from 'vue-router'
import KakaoChatbot from '@/components/KakaoChatbot.vue';


export default {
  components: {
  KakaoChatbot,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    onMounted(() => {
  
      EventBus.on("logout", () => {
      logOut()
      })
    })

    // onUnmounted(() => {
    //   EventBus.remove("logout");
    // });

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

@font-face {
  font-family: 'MaruBuri-Regular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/MaruBuri-Regular.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
    font-family: 'Galmuri9';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/Galmuri9.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'SUIT-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'MinSans-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/MinSans-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'Chosunilbo_myungjo';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Chosunilbo_myungjo.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'ChosunKm';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunKm.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'ChosunSm';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.1/ChosunSm.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'ChosunKg';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunKg.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}



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
