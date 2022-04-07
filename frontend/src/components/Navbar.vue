<template>
  <div class="container-fluid" id="container1">
    <nav class="navbar navbar-expand-lg navbar-light mb-5">
        <div class="container-fluid"><img src="@/assets/cslogo.png" alt="" style="height:60px; margin-right:20px;">  <a class="navbar-brand" href="#" id="container8" style="font-size:30px;">COUNTING STARS</a> <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-bar" aria-controls="navbar-bar" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
            <div class="collapse navbar-collapse" id="navbar-bar">
                <div class="navbar-nav ms-auto" style="margin-top:15px;">
                    <a class="nav-link" aria-current="page" href="#" id="container2" style="font-size:30px;">Home</a>
                    <a class="nav-link"  id="container3" data-bs-toggle="modal" data-bs-target="#exampleModal2" style="position:relative;" @click="refreshAlarm">
                      <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="currentColor" class="bi bi-bell" viewBox="0 0 16 16">
                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
                      </svg>
                      <div class="circle-num" >{{newReceivedAlarm.length}}</div>
                    </a> 
                    <a class="nav-link" id="container5" @click="goNftpage" style="font-size:30px;">NFT</a> <a class="nav-link" href="#" id="container6" @click="getAccount" style="font-size:30px;">Wallet</a>
                    <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal" v-if="!myInfo" style="font-size:30px;">Login</a>
                    <a class="nav-link" href="#" v-else @click="logOut" style="font-size:30px;">Logout</a>
                  
                </div>
                    
            </div>
        </div>
    </nav>
    
  </div>

  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" >
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

  <!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="border-radius:1rem">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel2">Alarms</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body2" style="max-height :500px; overflow:hidden; overflow-y: scroll;">
        <div class="row-vh d-flex flex-row align-items-stretch" v-for="(item,idx) in receivedAlarm" :key="idx" style="height:100px;">
          <div style="margin-top:15px; width:20%; margin-left: 20px;">
            <img src="https://i.ibb.co/w64P7gS/Cartier.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'cartier'">
            <img src="https://i.ibb.co/TMvkQws/Fendi-logo.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'fendi'">
            <img src="https://i.ibb.co/TYhcqQS/versace.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'versace'">
            <img src="https://i.ibb.co/0J6dYWz/Louis-Vuitton.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'louis vuitton'">
            <img src="https://i.ibb.co/tPfwcDV/Prada.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'prada'">
            <img src="@/assets/Cartier.png" alt="" style="width:100%; height:100%;" v-if="item.brand === 'chanel'">
          </div>
          <div style="width:40%; margin-top:32px; margin-left: 20px;" align="left">
            <div >
              {{item.productName}}
            </div>
            <div>
              {{item.brand}}
            </div>
          </div>
          <div style="width:40%; margin-top:39px;">
            {{item.registerDate}}
          </div>
          
        </div>
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {ref, computed ,onMounted, } from 'vue'
import {useStore} from 'vuex'
import {useRouter} from 'vue-router'
import LoginModal from './accounts/LoginModal.vue'
import LookupNFTs from '@/utils/LookupNFT.js'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import api from '@/services/api'

export default {
  name : "Navbar",
  components : {
      LoginModal,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const myInfo = ref(store.state.userInfo)
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
      const divTag5 = document.getElementById("container5");
      const divTag6 = document.getElementById("container6");
      const divTag8 = document.getElementById("container8");
      if(window.scrollY > window.innerHeight){
        
   
        divTag1.classList.add('test')
        divTag2.classList.add('test')
        divTag3.classList.add('test')
        divTag5.classList.add('test')
        divTag6.classList.add('test')
        divTag8.classList.add('test')
      } else{
        divTag1.classList.remove('test')
        divTag2.classList.remove('test')
        divTag3.classList.remove('test')
        divTag5.classList.remove('test')
        divTag6.classList.remove('test')
        divTag8.classList.remove('test')

      }
    }

    const loggedIn = computed(() => {

        return store.state.auth.status.loggedIn
    })

    onMounted(() => {
        
    })
    const loginValue = async (val) => {
      console.log(val)
      store.dispatch("auth/login", val)
      console.log('엥')
      await connect()
      console.log('엥')

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
      myInfo.value = ''
  

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

    //socket test

    const recvList = ref([])
    const connected = ref(true)
    const stompClient = ref('')
    const receiver = ''

    const receiverWallet = ''// 이전 보내는 월렛 주소 => 수정해야함
    const sender = store.state.userInfo.email //지금 로그인 한 사람 메일
    const senderWallet = store.state.userInfo.address //로그인 한 사람 지갑
    const senderRole = store.state.userInfo.role //로그인 한 사람 역할
    const receiverBrand = store.state.userInfo.username //로그인 한 사람 브랜드
    const storeBrand = store.state.userInfo.store //스토어브랜드
    const connect = () => {
      const serverURL = "http://localhost:8080/alarm"
      let socket = new SockJS(serverURL);
      stompClient.value = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      stompClient.value.connect(
        {},
        frame => {
          connected.value = true;
          console.log('소켓 연결 성공', frame);
          if(senderRole == 'ROLE_BRAND_ADMIN'){ //로그인 한 사람의 role 이 brand면 brand구독
            stompClient.value.subscribe("/sub/channel/" + senderRole + "/" + receiverBrand, res => {
              console.log('구독으로 받은 메시지 입니다.', res.body);
              recvList.value.push(JSON.parse(res.body))
              api.get(`alarm/${receiver}/${myInfo.value.username}`).then(response => 
              {
                receivedAlarm.value = response.data.content
                console.log(receivedAlarm.value)
              })
              createToast(
                { title: 'Check your new alarm',  },
                // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
                { type:'info', showIcon:true, position:'bottom-right', }
              )
              
            });
          }else{//일반 유저면 자기 email을 구독해야함
            stompClient.value.subscribe("/sub/channel/" + senderWallet, res => { 
              console.log('구독으로 받은 메시지 입니다.', res.body);
              recvList.value.push(JSON.parse(res.body))
              api.get(`alarm/${receiver}/${myInfo.value.username}`).then(response => 
              {
                receivedAlarm.value = response.data.content
                console.log(receivedAlarm.value)
              })
              createToast(
                { title: 'Check your new alarm',  },
                // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
                { type:'info', showIcon:true, position:'bottom-right', }
              )
            });
          }
        },
        error => {
          console.log('소켓 연결 실패', error);
          connected.value = false;
        }
      ); 
    }

    

    const send = () => {
      console.log("Send message:" + receiver + sender);
      if (stompClient.value && stompClient.value.connected) {
        const msg = { 
          sender: sender,//보내는사람정보
          receiver : receiver,//받는사람
          productName: 'productname',//이전할상품정보
          brandNmae: 'chanel'
        };
        stompClient.value.send("/pub/pubs", JSON.stringify(msg), {});
      }
    }

    const sendAlarm = (e) => {
      send()
    }

    
    //socket - 받은 알람모음
    const receivedAlarm = ref([])

    if (myInfo.value.role === "ROLE_BRAND_ADMIN") {
      api.get(`alarm/ROLE_BRAND_ADMIN/${myInfo.value.username}`).then(res => 
    {
      receivedAlarm.value = res.data.content
      console.log(receivedAlarm.value)
    })} else if (myInfo.value.role === "ROLE_USER") {
      console.log(sender)
    api.get(`alarm/${sender}`).then(res => 
    {
      receivedAlarm.value = res.data.content
      console.log(receivedAlarm.value)
    })
    }

    const newReceivedAlarm = computed(() => {
      return receivedAlarm.value.filter(e => {
        return e.status == 0
      })
    })
    connect()


    const refreshAlarm = () => {
      if (myInfo.value.role === "ROLE_BRAND_ADMIN") {
        api.put(`alam/${myInfo.value.username}`,{check : 0}).then(res =>{
        console.log(res)
      })
      } else if (myInfo.value.role === "ROLE_USER") {
      console.log(sender)
      api.put(`alarm/${sender}`,{check : 1}).then(res => 
      {
        receivedAlarm.value = res.data.content
        console.log(receivedAlarm.value)
      })
    }




      // console.log(myInfo.value.username)
      // api.put(`alam/${myInfo.value.username}`).then(res =>{
      //   console.log(res)
      // })
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
      myInfo,


      //socket
      connect,
      recvList,
      connected,
      stompClient,
      receiver,
      sender,
      sendAlarm,
      send,
      receivedAlarm,
      newReceivedAlarm,
      refreshAlarm,
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
    padding-top: 35px;
    padding-right :5em;
    height:110px;
    /* font-size:30px; */
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


.circle-num {
  position: absolute;
  top: 0;
  right: -10%;
  width: 30px;
  height: 30px;
  font-size: 20px;
  font-weight: 500;
  text-align: center;
  background: #ec2c54 !important;
  color: #fff;
  border: 2px solid #fff;
  border-radius: 50%;
  color:white;
}


/* 스크롤바 숨기기 */
.modal-body2 {
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
}
.modal-body2::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}
</style>