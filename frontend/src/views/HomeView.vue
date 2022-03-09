<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <button @click.prevent="logOut">로그아웃</button>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  methods : {
    logOut() {
      console.log('로그아웃')
      console.log('엥')
      
      // if (window.Kakao.Auth.getAccessToken()) {
      //   console.log('in')
      //   window.Kakao.API.request({
      //     url: '/v1/user/unlink',
      //     success: function (response) {
      //       console.log(response)
      //     },
      //     fail: function (error) {
      //       console.log(error)
      //     },
      //   })
      //   window.Kakao.Auth.setAccessToken(undefined)
      // }
      console.log('out')
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  },
  components: {
    HelloWorld
  }
}
</script>
