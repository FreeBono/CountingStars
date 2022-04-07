<template>
  <!-- <h2>My NFT 상세보기</h2> -->
  <sidebar/>
  
  <div class="test">
  <div class="wrapper">

    <!-- 내용 들어갈 곳 -->
    <div class="main-content">

      <div class="header">
        <div style="position:absolute; margin-left:100px; margin-top: 50px; color:white; font-size: 2rem;"> NFT Transfer</div>
        <div class="row-vh d-flex" style="margin-left:80px; width:90%; margin-top:100px;">
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:20px; text-align:left" >  
                    <div >TOTAL NFTS</div>
                    <div >{{nfts.length}}</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <img src="@/assets/nftcoin.png"/>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden; ">
                  <div class="media-body" style="float:left; margin-top:18px;">  
                    <div style="text-align:left;">TOTAL TRANSFER</div>
                    <div align="left">{{transferHistory}}</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-handshake fa-3x" style="color:#3773f6;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:18px; text-align:left;">  
                    <div>TOTAL WORTH</div>
                    <div align="left">{{worth.toLocaleString('ko-KR')}} WON</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fa fa-won-sign fa-3x" style="color:gold;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:18px; text-align:left;">  
                    <div>HIGHEST PRICE</div>
                    <div align="left">{{highestPrice.toLocaleString('ko-KR')}} WON</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fa fa-won-sign fa-3x" style="color:gold;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
        </div>
        <div>
        
        </div>
      </div>
        <!-- <div class="testbox" style=">
          <div class="container">
            <div class="row">
              <div class="col-3" v-for="(nft,idx) in nfts" :key="idx">
                    <div class="card col-3" style="padding:0px; width:175px;">
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" v-if="nft.name">{{nft.name}}</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                          <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" @click="tokenChangeNum(nft.tokenId)">transfer</span>
                        </figcaption>
                      </figure>
                    </div>
                  </div>
            </div>
          </div>
        </div> -->
        
  
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; min-width:590px; overflow-y:scroll; max-height:1000px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">

              <!-- 필터링 부분 -->
              <div align="left" style="margin-bottom: 15px;">NFT 목록</div>
                <div class="searchbarr mb-4">
                  <select class="brandSel-tag" v-model="brandSelected" @change="brandSel()" >
                    <option v-for="(brandoption, idx) in brandOpt" :key="idx" :value="brandoption.value">
                      {{ brandoption.text }}
                    </option>

                  </select>
                  <select class="categorySel-tag" v-model="categorySelected" @change="headerSel()" >
                    <option value="null" selected>카테고리</option>
                    <option value="Bag">Bag</option>
                    <option value="Accessory">Accessory</option>
                    <option value="Clothes">Clothes</option>
                    <option value="Cloth">Cloth</option>
                    <option value="Wallet">Wallet</option>
                  </select>

                  <b-form-input class="mx-2" b-form-input style="width: 350px; height: 40px; font-size: 15px;" placeholder="검색할 nft 이름을 입력하세요." v-model="word" @keydown.enter="searchTitle()" autocomplete="off"></b-form-input>
                  <b-button class="searchBtn mr-1" @click="searchTitle()">검색</b-button>
                  <b-button class="resetSearchBtn" @click="searchInit()">초기화</b-button>
                </div>
                <!-- 필터링 부분 끝 -->

              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div class="col-3" v-for="(nft,idx) in nfts" :key="idx">
                    <div class="card" style="padding:0px; " >
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%; ">
                        <figcaption class="card__caption" >
                          <h2 class="card__title" v-if="nft.name" style="color:white;">{{nft.name}}</h2>
                          <div class="card__snippet" >
                            <div>{{nft.brandName.toUpperCase()}}</div>
                            <div> {{parseInt(nft.productPrice).toLocaleString('ko-KR')}} WON</div>
                          </div>
                          <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" @click="tokenChangeNum(nft.tokenId)" style="cursor:pointer;">transfer</span>
                        </figcaption>
                      </figure>
                    </div>
                    <!-- <div class="card col-3" style="padding:0px; width:85%;" v-else>
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img src="@/assets/cslogo.png" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%; ">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" v-if="nft.name" style="color:white;">이전 중인 NFT입니다.</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                          <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" @click="tokenChangeNum(nft.tokenId)" style="cursor:pointer;">transfer</span>
                        </figcaption>
                      </figure>
                    </div> -->
                  </div>
              </div>
            </div>
          </div>
        </div>

        <div class="content_box row-vh d-flex flex-row " style="position:absolute; top : 280px; left:47%; width : 41%;min-width:650px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div align="left" style="margin-left:10px; margin-top:10px;">월별 NFT이전</div>
                  <!-- <hr style="margin-top:15px 0;"> -->
                  <div align="center" >
                    <Graph style="width:90%; height:100%; margin-top:30px;"/>
                  </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 로고 -->
        <div style="position:absolute; top : 900px; width:30%; height :200px; margin-left:12%; margin-top:70px;">
          <img src="@/assets/cslogo.png" alt="" style="width:50%; height:100%;">
          <div>
            <div style="font-size:50px; width:100%;">COUNTING STARS</div>
          </div>
        </div>

        <div class="abc content_box row-vh d-flex flex-row " style="position:absolute; top : 950px; left: 47%; width : 41%;min-width:700px;">
          <!-- <div  class="container-fluid"> -->
            <div class="searchBarTag mt-3" style="width:100%;">
              <!-- <div class="container justify-content-center"> -->
                <div align="left" style="margin-left:10px; margin-top:10px;">NFT 이전 랭킹</div>
                <div class="row" >
                  
                  <!-- <hr style="margin-top:15px 0;"> -->
                  
                    <div class="row-vh d-flex flex-row  justify-content-around"   >
                      <div class="row-vh d-flex flex-column" style="" >
                          <div class="row-vh d-flex flex-row justify-content-around" v-for="(item,idx) in rankData.slice(0,3)" :key="idx" style="margin-top:40px;">
                            <div style="margin-top:5px;  font-size : 25px;  width:15%;">{{idx+1}}</div>
                            <div style=" text-align:left; width:75%;">
                              <div align="left" style="font-size:20px; ">{{item.email}}</div>
                              <div style="font-size:14px;">{{item.address.substring(0,18)}}</div>
                              
                            </div>
            
                          </div>                       
                      </div>
                      <div class="row-vh d-flex flex-column" style="" >
                          <div class="row-vh d-flex flex-row justify-content-around" v-for="(item,idx) in rankData.slice(3,6)" :key="idx" style="margin-top:40px;">
                            <div style="margin-top:5px;  font-size : 25px;  width:15%;">{{idx+4}}</div>
                            <div style="padding-left:15px; text-align:left; width:75%;">
                              <div align="left" style="font-size:20px; ">{{item.email}}</div>
                              <div style="font-size:14px;">{{item.address.substring(0,18)}}</div>
                              
                            </div>
                 
                          </div>                       
                      </div>
                      <div class="row-vh d-flex flex-column" style="margin-right:15px">
                          <div class="row-vh d-flex flex-row justify-content-around" v-for="(item,idx) in rankData.slice(6,9)" :key="idx" style="margin-top:40px;">
                            <div style="margin-top:5px;  font-size : 25px;  width:15%;">{{idx+7}}</div>
                            <div style="padding-left:15px; text-align:left; width:75%;">
                              <div align="left" style="font-size:20px; ">{{item.email}}</div>
                              <div style="font-size:14px;">{{item.address.substring(0,18)}}</div>
                              
                            </div>
              
                          </div>                       
                      </div>
                      
                      
                      
          
                    
                    







                  </div>
              </div>
            <!-- </div> -->
          </div>
        </div>
    
 <!-- <button type="button" class="btn btn-primary" @click="send()">transfer</button> -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">NFT를 이전할 지갑 주소를 입력해주세요.</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- <div>지갑 주소</div> -->
				<div>
					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Name" name="name" id='name' v-model="receiveAccount" required />
						<label for="name" class="form__label">Account (상대방 지갑주소)</label>
					</div>
				</div>
        <div>
					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Name" name="name2" v-model="receivePrivatekey" required />
						<label for="name" class="form__label">Privatekey (내 개인키)</label>
					</div>
				</div>
				<!-- <div>로 이전합니다.</div> -->
      </div>
      <div class="modal-footer">
        <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="sendToken">transfer</button>
      </div>
    </div>
  </div>
</div>





    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>

  </div>
</template>

<script>
import api from '@/services/api.js'
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import { useRouter } from 'vue-router'
import LookupNFTs from '@/utils/LookupNFT.js'
import {ref, computed } from 'vue'
// import axios from 'axios'
import {useStore} from 'vuex'
import TransferToken from '@/utils/TransferNFT.js'
import Graph from '@/components/Graph'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'


export default {
  name: 'NftTransfer',
  components: {
    Sidebar,
    Graph,
  },
 
  setup() {
    const store = useStore()
    const router = useRouter()
    // const store = useStore()
    const nfts = ref([])
		const receiveAccount = ref('')
    const receivePrivatekey = ref('')
    const transferHistory = ref(0)
    const rankData = ref([])
    nfts.value = []
    function sendNft() {
      // router.push({name: 'NftTransfer'})
      // 모달? 알럿? 띄우기
      alert('전송 되었습니다.')
      // 전송되면 내 목록에서 삭제 되야 함
    }

    function goMyNftDetail() {
      router.push({name: 'MyNftDetail'})
    }
    if (store.state.nftValues.length === 0) {
      LookupNFTs()
    }
    nfts.value = store.state.nftValues


		const tokenNum = ref(0)
		const tokenChangeNum = (e) => {
			// console.log(e)
			tokenNum.value = e
		}

		async function sendToken() {
      let info = nfts.value.filter(e => {
        return parseInt(e.tokenId) ===parseInt(tokenNum.value)
      })
      console.log(info[0])
      
      //알람
      createToast(
        { title: 'Send Transaction',  },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'info', showIcon:true, position:'bottom-right', }
        )
      
			console.log(tokenNum.value)
   
			await TransferToken(receiveAccount.value ,receivePrivatekey.value, tokenNum.value)
      nfts.value = store.state.nftValues
      
      
      send(info[0],receiveAccount.value)
      
      LookupNFTs()
		}
    


    const worth = computed(() => {
      if (nfts) {
      return (nfts.value.map(function(x) {return parseInt(x.productPrice)}).reduce(function(a,b) { return a+b;},0))
      } else {
        0
      }
    })

    const highestPrice = computed(() => {
      return Math.max.apply(null, store.state.nftValues.map(function(x) {return parseInt(x.productPrice)}))
      })

    api.get('/userTransaction',{params: {userId: store.state.auth.user.id}})
    .then(res => {
      console.log(res)
      transferHistory.value = res.data.length
    })
    .catch(err => {
      console.log(err)
    })

    // transfer ranking
    api.get('/userTransaction/rank',)
    .then(res => {
      console.log(res.data)
      rankData.value = res.data.content
      // rankData.value = res.data.content.filter(e => {
      //   return e.address != null
      // })
    
    })
    .catch(err => {
      console.log(err)
    })


    

    // 필터 부분
    const word = ref("")
    const brandSelected = ref(null)
    const categorySelected = ref(null)
    const searchSelected = ref(null)
    nfts.value = store.state.nftValues


      // filter 사용될 데이터들
    
    // const filters = ref([null,null,null])
  

    const brandOpt = ref([ { value: null, text: '브랜드' }])
    api.get('/brand')
    .then(res => res.data.content.forEach( e => {
      brandOpt.value.push({value:e.name.toLowerCase(),text: e.name})
    
    }))
    .catch(err => console.log(err))

    
    const categoryOpt = ref([
        { value: null, text: '카테고리' },
        { value: 'Class Bag', text: 'BAG' },
        { value: 'wallet', text: '지갑' },
        { value: true, text: '의류' },
        { value: 'accessories', text: '악세사리' },
        { value: true, text: '기타' },
      ])
    
    
    const searchOpt = ref([
        { value: "name", text: 'nft이름' },
        // { value: "type", text: '소재' },
      ])

    // 동현 필터
    // const goFilter = () => {
    //   console.log('엥실행')
    //   console.log('brandselected : ', filters.value[0])
    //   if (filters.value[0] != null) {
    //     console.log('엥실행2')
    //     nfts.value = nfts.value.filter( e => {
          
    //       return e.brandName.toLowerCase() == filters.value[0].toLowerCase()
          
    //     })
    //   }

    //   if (filters.value[1] != null) {
    //     console.log('엥실행3')
    //     nfts.value = nfts.value.filter( e => {
    //       return e.productClassification == filters.value[1]
    //     })
    //   }

    //   if (filters.value[2] != null ) {
    //     nfts.value = nfts.value.filter( e => {
    //       return e.name.toLowerCase().includes(filters.value[2].toLowerCase())
    //     })
    //   }
    //   console.log(nfts.value)
    // }

    
    
    // const searchPaging = () => {
    //   rowws.value = store.state.nftValues.length;
    // }

    // 검색 초기화
    const searchInit = () => {
      categorySelected.value = null;
      brandSelected.value = null;
      word.value = "";
      nfts.value = store.state.nftValues;
    }


    // 카테고리 셀렉
    const headerSel = () => {
      word.value ="";
      console.log(store.state.nftValues,' 카테고리--작동 확인--')

      if(categorySelected.value == null){ // 카테고리 선택을 안했을 때
        if(brandSelected.value == null){ // 브랜드 선택을 안했을 때
          console.log(categorySelected.value,' 브랜 안 선택 카테고리2')
          console.log(brandSelected.value, '되나여기')
          } else { // 브랜드 선택을 했을 때
              nfts.value = store.state.nftValues.filter((nft) => { // 브랜드에 해당하는 게시글 불러오기
              console.log(nfts.value,' 브랜드선택 카테고리')
              console.log(nft,'nft 뭐 찍히나 확인----')
              console.log(nft.brandName,'nft.brandName 뭐 찍히나 확인----')
              return nft.brandName.toLowerCase() == brandSelected.value.toLowerCase();
            });
          }
      } else { // 카테고리 선택을 했을 때
        if(brandSelected.value == null){ // 브랜드 선택이 안 되어 있을 때
          nfts.value = store.state.nftValues.filter((nft) => { // 카테고리에 해당하는 게시글 불러오기
          console.log(nft.productClassification, '카노 - 브노 선택했을 때')
            return nft.productClassification.toLowerCase() == categorySelected.value.toLowerCase();
          });
        } else{ // 브랜드 선택이 되어 있을 때
          nfts.value = store.state.nftValues.filter((nft) => { // 카테고리와 브랜드에 해당하는 게시글 불러오기
            return nft.productClassification.toLowerCase() == categorySelected.value.toLowerCase() && nft.brandName.toLowerCase() == brandSelected.value.toLowerCase();
          });
        }
      }
    }
    
    // 브랜드 선택
    const brandSel = () => {
      word.value ="";


      if(brandSelected.value == null){ // 브랜드을 선택 안했을 때
        if(categorySelected.value == null){ // 카테고리 선택을 안했을 때
          console.log(brandSelected.value, '브랜드 선택 😆')
          console.log(categorySelected.value, '브노 - 카노 브랜드 선택 😆')
        } else{ // 카테고리 선택을 했을 때
          nfts.value = store.state.nftValues.filter((nft) => { // 카테고리에 해당하는 게시글 불러오기
          console.log(nfts.value, 'brandSel 작동 확인')
            return nft.productClassification.toLowerCase() == categorySelected.value.toLowerCase();
          });
        }
      } else { // 브랜드 선택을 했을 때
        if(categorySelected.value == null){ // 카테고리 선택이 안 되어 있을 때
          nfts.value = store.state.nftValues.filter((nft) => { // 브랜드에 해당하는 게시글 불러오기
          console.log(nft, '브랜드 선택 했다, 브랜드 셀에서')

            return nft.brandName.toLowerCase() == brandSelected.value.toLowerCase();
          });
        } else{ // 카테고리 선택이 되어 있을 때
          console.log(brandSelected.value, '브 else -브')
          console.log(categorySelected.value, '브 else -카')
          nfts.value = store.state.nftValues.filter((nft) => {  // 카테고리와 브랜드에 해당하는 게시글 불러오기
            return nft.productClassification.toLowerCase() == categorySelected.value.toLowerCase() && nft.brandName.toLowerCase() == brandSelected.value.toLowerCase();
          });
        }
      }
    }

    const searchTitle = () => {
      nfts.value = nfts.value.filter((nft) => {
        console.log(nft.name, '검색 확인')
        return nft.name.toLowerCase().includes(word.value.toLowerCase())
      })
    }

    const selectedBrandName = ref(null)
    const selectBrandImg = ref(null)
    const showDetailModal = ref(null)
    const madeCountry = ref(null)
    const madeDate = ref(null)
    const description = ref(null)
    const material = ref(null)
    const nftName = ref(null)
    const productType = ref(null)
    const productColor = ref(null)
    const price = ref(null)
    const serialNumber = ref(null)


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
      const serverURL = "/api/v1/alarm"
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
            });
          }else{//일반 유저면 자기 email을 구독해야함
            stompClient.value.subscribe("/sub/channel/" + senderWallet, res => { 
              console.log('구독으로 받은 메시지 입니다.', res.body);
              recvList.value.push(JSON.parse(res.body))
            });
          }
        },
        error => {
          console.log('소켓 연결 실패', error);
          connected.value = false;
        }
      ); 
    }

    connect()

    const send = (info,wallet) => {
      if (stompClient.value && stompClient.value.connected) {
        if(senderRole == 'ROLE_STORE_ADMIN'){
          
          const msg = {
            sender: senderRole,//보내는사람정보
            receiver : 'ROLE_BRAND_ADMIN',//받는사람
            brand : storeBrand,
            productName: info.name,//이전할상품정보
  
          };
          console.log(msg)
          stompClient.value.send("/pub/pubs", JSON.stringify(msg), {});
        }else{
          const msg = { 
            sender: sender,//보내는사람정보
            receiver : wallet,//받는사람
            productName: 'productname',//이전할상품정보
          };
          stompClient.value.send("/pub/pubs", JSON.stringify(msg), {});

        }
      }
    }

    // const sendAlarm = (e) => {
    //   send()
    // }
    


    return {
      goMyNftDetail,
      sendNft,
      LookupNFTs,
      nfts,
			receiveAccount,
			sendToken,
			tokenNum,
      tokenChangeNum,
      worth,
      highestPrice,
      // getTransferInfo,
      transferHistory,
      rankData,
      receivePrivatekey,

      brandSelected,
      categorySelected,
      searchSelected,
      brandOpt,
      categoryOpt,
      searchInit,
      searchOpt,
      word,
      searchInit,
      // searchPaging,
      headerSel,
      brandSel,
      searchTitle,
      // goDetailModal,
      selectedBrandName,
      showDetailModal,
      selectBrandImg,
      madeCountry,
      madeDate,
      description,
      material,
      nftName,
      productType,
      productColor,
      price,
      serialNumber,
      
      //socket
      connect,
      recvList,
      connected,
      stompClient,
      receiver,
      sender,
      // sendAlarm,
      send,
      

     
    }
  },
  
  
}
</script>

<style lang="scss" scoped>

// 필터링 부분
.searchbarr {
  display: flex;
  // margin-top: 10px;

}

.brandSel-tag {
  border-color: #ced4da;
  width: 150px; 
  height: 40px; 
  font-size: 15px;
  border-radius: 0.25rem;
  color: #717981;
}

.categorySel-tag {
  border-color: #ced4da;
  margin-left: 0.5rem;
  width: 170px; 
  height: 40px; 
  font-size: 15px;
  border-radius: 0.25rem;
  color: #717981;
}

.searchBtn {
  width: 60px;
  padding: 0; 
  height: 40px; 
  font-size: 15px;
  color: #333333 !important;
  background-color: #fff !important;
  border-color: transparent;
  border: 1px solid transparent !important;
  box-shadow: 1px 1px 2px 2px #ececf0;
}

.searchBtn:hover {
  background-color: #34db93 !important;
  color: rgb(0, 0, 0);
}

.resetSearchBtn {
  padding: 0; 
  width: 60px; 
  height: 40px; 
  font-size: 15px;
  color: #333333 !important;
  background-color: #fff !important;
  border-color: transparent;
  border: 1px solid transparent !important;
  box-shadow: 1px 1px 2px 2px #ececf0;
}

.resetSearchBtn:hover {
  background-color: #2dce89 !important;
  color: white;
}

// 필터링 끝

.nft_img {
  display: flex;
  justify-content: flex-start;
  /* align-items: center; */
  top: 150px;
  position: relative;
  left: 100px;
}


$desktop: 1024px;

@mixin breakpoint($point) {
  @if $point == desktop {
    @media (min-width: $desktop) {
      @content;
		}
	}
}

html {
  box-sizing: border-box;
  font-size: 62.5%;

  *,
  *:before,
  *:after {
    box-sizing: inherit;
  }
}



.container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	width: 100%;
	margin: 20px 0 80px;
}

.card {
	// width: 300px;
	margin: 10px;
	background-color: white;
	box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.5);

	&:hover {
		.card__caption {
			top: 50%;
			transform: translateY(-50%);
		}

		.card__image {
			transform: translateY(-10px);
		}

		.card__thumb {
			&::after {
				top: 0;
			}
		}

		.card__snippet {
			margin: 20px 0;
		}
	}

	&__thumb {
		position: relative;
		max-height: 400px;
		overflow: hidden;
		
		@include breakpoint(desktop) {
			max-height: 500px;
		}

		&::after {
			position: absolute;
			top: 0;
			display: block;
			content: '';
			width: 100%;
			height: 100%;
			background: linear-gradient(0deg, rgba(0, 0, 0, .5) 40%, rgba(255, 255 ,255 , 0) 100%);
			transition: .3s;
			
			@include breakpoint(desktop) {
				top: calc(100% - 140px);
			}
		}
	}
	
	&__image {
		transition: .5s ease-in-out;
	}
	
	&__caption {
		position: absolute;
		top: 50%;
		z-index: 1;
		padding: 0 20px;
    width:100%;
		color: white;
		transform: translateY(-50%);
		text-align: center;
		transition: .3s;

		@include breakpoint(desktop) {
			top: calc(100% - 110px);
			transform: unset;
		}
	}

	&__title {
		display: -webkit-box;
		max-height: 85px;
    overflow: hidden;
		font-family: 'Playfair Display', serif;
		font-size: 14px;
		line-height: 28px;
		text-shadow: 0px 1px 5px black;
		text-overflow: ellipsis;
    -webkit-line-clamp: 3;
		-webkit-box-orient: vertical;
	}

	&__snippet {
		display: -webkit-box;
		max-height: 150px;
		margin: 20px 0;
		overflow: hidden;
		font-family: 'Roboto', sans-serif;
		font-size: 16px;
		line-height: 20px;
		text-overflow: ellipsis;
		transition: .5s ease-in-out;
    -webkit-line-clamp: 5;
		-webkit-box-orient: vertical;
		
		@include breakpoint(desktop) {
			margin: 60px 0;
		}
	}

	&__button {
		display: inline-block;
		padding: 10px 20px;
		color: white;
		border: 1px solid white;
		font-family: 'Roboto', sans-serif;
		font-size: 12px;
		text-transform: uppercase;
		text-decoration: none;
		transition: .3s;

		&:hover {
			color: black;
			background-color: white;
		}
	}
}

.disclaimer {
	position: fixed;
	bottom: 0;
	left: 50%;
	z-index: 2;
	box-sizing: border-box;
	width: 100%;
	padding: 20px 10px;
	background-color: white;
	transform: translateX(-50%);
	font-family: 'Roboto', sans-serif;
	font-size: 14px;
	text-align: center;
	
	&__link {
		color: #755D87;
		text-decoration: none;
	}
}







$primary: #11998e;
$secondary: #38ef7d;
$white: #fff;
$gray: #9b9b9b;
.form__group {
  position: relative;
  padding: 15px 0 0;
  margin-top: 10px;
  width: 90%;
}

.form__field {
  font-family: inherit;
  width: 100%;
  border: 0;
  border-bottom: 2px solid $gray;
  outline: 0;
  font-size: 1.3rem;
  color: black;
  padding: 7px 0;
  background: transparent;
  transition: border-color 0.2s;

  &::placeholder {
    color: transparent;
  }

  &:placeholder-shown ~ .form__label {
    font-size: 1.3rem;
    cursor: text;
    top: 10px;
  }
}

.form__label {
  position: absolute;
  top: 0;
  display: block;
  transition: 0.2s;
  font-size: 1rem;
  color: $gray;
}

.form__field:focus {
  ~ .form__label {
    position: absolute;
    top: 0;
    display: block;
    transition: 0.2s;
    font-size: 1rem;
    color: $primary;
    font-weight:700;    
  }
  padding-bottom: 6px;  
  font-weight: 700;
  border-width: 3px;
  border-image: linear-gradient(to right, $primary,$secondary);
  border-image-slice: 1;
}
/* reset input */
.form__field{
  &:required,&:invalid { box-shadow:none; }
}
/* 비반응형 설정 */

.wrapper {
  width:100%;
  min-width: 1600px;
}

.test {
  width:100vw;
  
}



//스크롤
body {
  -ms-overflow-style: none;
}

::-webkit-scrollbar {
   display: none; 
} 

/*특정 부분 스크롤바 없애기*/ 
.content_box { 
  -ms-overflow-style: none; 
}

.content_box::-webkit-scrollbar{ display:none; }

.abc {
  // width: 75%;
  min-height: 350px;
  background-color: white;
  // margin-left: 100px;
  border: 1px;
  border-radius: 10px;
  box-shadow: 3px 3px 10px 1px #d8d7d7;
}


</style>
