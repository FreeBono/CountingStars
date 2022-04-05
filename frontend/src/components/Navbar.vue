<template>
  <div class="container-fluid" id="container1">
    <nav class="navbar navbar-expand-lg navbar-light mb-5">
        <div class="container-fluid"><img src="@/assets/cslogo.png" alt="" style="height:60px; margin-right:20px;">  <a class="navbar-brand" href="#" id="container8">COUNTING STARS</a> <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-bar" aria-controls="navbar-bar" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
            <div class="collapse navbar-collapse" id="navbar-bar">
                <div class="navbar-nav ms-auto" style="margin-top:15px;">
                    
                    <a class="nav-link" aria-current="page" href="#" id="container2">Home</a>
                    <a class="nav-link"  id="container3" >About</a> <a class="nav-link" href="#" id="container4">Services</a>
                    <a class="nav-link" id="container5" @click="goNftpage">NFT</a> <a class="nav-link" href="#" id="container6" @click="getAccount">MetaMask</a>
                    <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal" v-if="!store.state.userInfo">Login</a>
                    <a class="nav-link" href="#"  v-else @click="logOut">Logout</a>
                  
                </div>
                    
            </div>
        </div>
    </nav>
    
  </div>

  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div> -->
          <div class="modal-body">
            <LoginModal @login-value="loginValue"/>
          </div>
          <!-- <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div> -->
        </div>
      </div>
    </div>
</template>

<script>
import {computed ,onMounted, } from 'vue'
import {useStore} from 'vuex'
import {useRouter} from 'vue-router'
import LoginModal from './accounts/LoginModal.vue'
import LookupNFTs from '@/utils/LookupNFT.js'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'

export default {
  name : "Navbar",
  components : {
      LoginModal,
  },
  setup() {
    const store = useStore()
    const router = useRouter()

    //메타마스크 연결
    if (typeof window.ethereum !== 'undefined') {
      console.log('MetaMask is installed!');
    }
    const getAccount = async function () {
      store.dispatch("nftValues",[])
      
      const accounts = await ethereum.request({ method: 'eth_requestAccounts' });
      console.log(accounts)
      if (accounts) {
        createToast(
        { title: 'MetaMask is connected',  },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'success', showIcon:true, position:'bottom-right', }
        )
      }
      await LookupNFTs()
      createToast(
        { title: 'NFTs are loaded',  },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'success', showIcon:true, position:'bottom-right', }
        )
    }
    const goScroll = () => {
      console.log(document.getElementById("container3"))
      document.getElementById("container3").scrollIntoView(true);
    }


    window.onscroll = function() {
      const divTag1 = document.getElementById("container1");
      const divTag2 = document.getElementById("container2");
      const divTag3 = document.getElementById("container3");
      const divTag4 = document.getElementById("container4");
      const divTag5 = document.getElementById("container5");
      const divTag6 = document.getElementById("container6");
      // const divTag7 = document.getElementById("container7");
      const divTag8 = document.getElementById("container8");
      if(window.scrollY > window.innerHeight){
        
   
        divTag1.classList.add('test')
        divTag2.classList.add('test')
        divTag3.classList.add('test')
        divTag4.classList.add('test')
        divTag5.classList.add('test')
        divTag6.classList.add('test')
        // divTag7.classList.add('test')
        divTag8.classList.add('test')
      } else{
        divTag1.classList.remove('test')
        divTag2.classList.remove('test')
        divTag3.classList.remove('test')
        divTag4.classList.remove('test')
        divTag5.classList.remove('test')
        divTag6.classList.remove('test')
        // divTag7.classList.remove('test')
        divTag8.classList.remove('test')

      }
    }

    const loggedIn = computed(() => {

        return store.state.auth.status.loggedIn
    })

    onMounted(() => {
        
    })
    const loginValue = (val) => {
      console.log(val)
      store.dispatch("auth/login", val)


    }

    const logOut = () => {
      console.log('로그아웃')
      console.log(23)
    //   if (window.Kakao.Auth.getAccessToken()) {
    //     console.log('in')
    //     window.Kakao.API.request({
    //       url: '/v1/user/unlink',
    //       success: function (response) {
    //         console.log(response)
    //       },
    //       fail: function (error) {
    //         console.log(error)
    //       },
    //     })
    //     window.Kakao.Auth.setAccessToken(undefined)
    //   }
      console.log('out')
      store.dispatch('auth/logout');
  

    }

    const goNftpage = () => {
      if (store.state.userInfo) {
      router.push({name:'MyNft'})
      }
      else {
        createToast(
        { title: 'This service requires a login.',  },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'danger', showIcon:true, position:'bottom-right', }
        )
      }
    }


    return {
      // divTag1,
      loggedIn,
      store,
      logOut,
      loginValue,
      getAccount,
      goScroll,
      goNftpage,
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap');

* {
    /* margin: 0; */
    padding: 0;
    box-sizing: border-box;
    list-style: none;
    font-family: 'Montserrat', sans-serif;
    z-index:1051;
}
.test {
  background-color: rgb(255, 255, 255);
  color:black !important

}


.container-fluid {
    /* margin: 20px auto; */
    /* background-color: #ffd70085; */
    /* padding: 10px 40px */
    position:fixed;
    width: 100vw;
    padding-top: 20px;
    padding-right :5em;
}

.container-fluid nav {
    background-color: transparent
}

.navbar-light .navbar-toggler {
    box-shadow: none
}

.container-fluid nav .container-fluid a {
    color: white
}

.container-fluid nav .container-fluid .navbar-nav a {
    color: white;
    font-size: 18px;
    font-weight: 600
}

.container-fluid nav .container-fluid a:hover {
    color: white
}

.container-fluid nav .container-fluid .navbar-nav a:hover {
    background-color: #ffd700;
    color: white
}

.container-fluid nav .fas {
    color: #ffd700
}

.container-fluid nav .container-fluid .navbar-nav .nav-link {
    margin-right: 15px
}

.container-fluid nav .navbar-nav :last-child {
    background-color: #ffd700
}

.container-fluid .content {
    max-width: 240px
}

.container-fluid .content .line {
    max-height: 10px;
    width: 100px;
    border-top: 2px solid white
}

.container-fluid .content .h2 {
    color: white;
    font-weight: 600
}

.container-fluid .content .p2 {
    color: white;
    font-weight: 600
}

.container-fluid .content .btn.btn-primary {
    background-color: transparent;
    color: white;
    border: 1px solid white
}

.container-fluid form {
    max-width: 300px;
    background-color: white
}

.container-fluid form.line {
    max-height: 10px;
    width: 100px;
    border-top: 2px solid #ffd700
}

.container-fluid form option {
    font-size: 20px
}

.container-fluid form .text-muted {
    font-size: 18px;
    color: #0a0a0a !important
}

.container-fluid form .c-g {
    color: #ffd700;
    font-weight: 800
}

.container-fluid form .dropdown-toggle::after {
    content: '';
    border: none
}

.container-fluid form .form-select {
    font-size: 20px
}

.container-fluid form .form-select:focus {
    box-shadow: none;
    border: 1px solid #ddd
}

.container-fluid form .btn.btn-primary {
    text-transform: uppercase;
    font-size: 20px;
    height: 40px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #ffd700;
    font-weight: 600;
    outline: none;
    border: none
}

@media (max-width:500px) {
    body {
        padding: 10px
    }

    .container-fluid {
        padding: 10px 20px
    }

    .container-fluid form {
        max-width: 100%
    }
}

.modal-body {
  border-radius: 4rem;
  background: linear-gradient(87deg,#2dce89,#2dcecc)!important;
  background: linear-gradient(87deg,#2dce89,#2dcecc)!important;
  background-image: linear-gradient(87deg,#2dce89,#2dcecc)!important;
  background-image: linear-gradient(87deg,#2dce89,#2dcecc)!important;
}

.modal-content{
  border-radius: 4rem;
}
</style>