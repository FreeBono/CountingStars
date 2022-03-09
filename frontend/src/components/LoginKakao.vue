<template>
  <div>
    <img
        class="kakao_btn"
        src="@/assets/kakao_login_medium_narrow.png"
        @click="loginWithKakao"
    />
  </div>
</template>

<script>
import api from "../services/api"
export default {
    name: "LoginKakao",
   
    methods: {
        loginWithKakao() {
          const vm = this
          if (!window.Kakao.isInitialized()) {
              // JavaScript key를 인자로 주고 SDK 초기화
              window.Kakao.init('eb6b89b350284c15c748ea3d3855f6c5');
              // SDK 초기화 여부를 확인하자.
              console.log(window.Kakao.isInitialized());
              }
          
          // if (window.Kakao.Auth.getAccessToken()) {
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
          window.Kakao.Auth.login({
          success: function () {
            window.Kakao.API.request({
              url: '/v2/user/me',
              // data: {
              //   property_keys: ["kakao_account.email","kakao_account.age_range","properties.nickname","properties.profile_image","properties.thumbnail_image","kakao_account.birthday","kakao_account.gender"]
              // },
              success: async function (response) {
                const a = {
                  username : response.id,
                  password : response.id,
                  email : response.kakao_account.email,
                }

                console.log(a)
                console.log(response)
                // this.schema['username'] = response.id,
                // this.schema['password'] = response.id,
                // this.schema['email'] =  response.kakao_account.email,
                
                // console.log(this.schema)
                api.post("/auth/signup",a)
                .then(res => {
                  console.log(res)
                  const b = response.id
                  vm.$store.dispatch("auth/login", {username:b,password:b})
                  vm.$router.push("/profile");
                })
                .catch(err => {
                  console.log(err)
                  console.log(response.id)
                  const b = response.id
                  // async 해보기
                  vm.$store.dispatch("auth/login", {username:b,password:b})
                  vm.$router.push("/");

                })
              },
              fail: function (error) {
                console.log(error)
              },
            })
          },
          fail: function (error) {
            console.log(error)
          },
          })






      },
    },
};
</script>
<style scoped>

.kakao_btn {
    cursor: pointer;
}
</style>