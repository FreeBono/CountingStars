<template>
  <!-- <h2>My NFT 상세보기</h2> -->
  <sidebar/>
  
  <!-- <div class="test"> -->
  <div class="wrapper">
    
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <div style="position:absolute; margin-left:100px; margin-top: 50px; color:white; font-size: 2rem;" @click="toast"> My NFTs</div>
        <div class="row-vh d-flex" style="margin-left:80px; width:90%; margin-top:100px;">
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:20px; text-align:left" >  
                    <div >TOTAL NFTS</div>
                    <!-- <div >{{nfts.length}}</div> -->
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fab fa-bitcoin fa-3x" style="color:gold;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px; text-align:left;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden; ">
                  <div class="media-body" style="float:left; margin-top:18px;">  
                    <div style="text-align:left;">MAIN WALLET ADDRESS</div>
                    <!-- <div align="left" style="word-break:break-all;" @click="copyToClickBoard" v-if="myWallet.length >= 1">{{myWallet.substring(0,10)}} ... {{myWallet.substring(32,42)}}</div>
                    <div align="left" style="word-break:break-all;" @click="copyToClickBoard" v-else>지갑을 설정해주세요..</div>
                    <div align="left" style="word-break:break-all; display:none;" id="copytext" @click="copyToClickBoard" >{{myWallet}}</div> -->
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-wallet fa-3x" style="color: #9d6510;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:15px; text-align:left;">  
                    <div>TOTAL WORTH</div>
                    <!-- <div align="left">{{worth.toLocaleString('ko-KR')}}$</div> -->
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
                  <div class="media-body" style="float:left; margin-top:15px; text-align:left;">  
                    <div>HIGHEST PRICE</div>
                    <!-- <div align="left">{{highestPrice.toLocaleString('ko-KR')}}$</div> -->
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
        
        <!-- NFT목록 -->
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; min-width:590px; overflow-y:scroll; max-height:600px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->


                <!-- 필터링 부분 -->
                <div class="searchbarr mb-4">
                  <select class="brandSel-tag" v-model="brandSelected" @change="brandSel()" >
                    <option v-for="(brandoption, idx) in brandOpt" :key="idx" :value="brandoption.value">
                      {{ brandoption.text }}
                    </option>

                  </select>
                  <select class="categorySel-tag" v-model="categorySelected" @change="headerSel()" >
                    <option value="null" selected>카테고리</option>
                    <option value="Class Bag">Bag</option>
                    <option value="accessory">Accessory</option>
                    <option value="Clothes">Clothes</option>
                    <option value="Shoes">Shoes</option>
                    <option value="Wallet">Wallet</option>
                  </select>

                  <b-form-input class="mx-2" b-form-input style="width: 250px; height: 40px; font-size: 15px;" placeholder="검색할 nft 이름을 입력하세요." v-model="word" @keydown.enter="searchTitle()" autocomplete="off"></b-form-input>
                  <b-button class="searchBtn mr-1" @click="searchTitle()">검색</b-button>
                  <b-button class="resetSearchBtn" @click="searchInit()">초기화</b-button>
                </div>
                <!-- 필터링 부분 끝 -->


                <div class="row" >
                  <div align="left" >NFT 목록></div>
                  
                  <div class="col-3" v-for="(nft,idx) in nfts" :key="idx" >
                    <div class="card col-3" style="padding:0px; width:85%;" v-if="nft.status ===0">
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" style="color:white;" v-if="nft.name">{{nft.name}}</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                          <!-- <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" style="cursor:pointer;">Detail</span> -->
                          <div>
                            <span class="card__button " data-bs-toggle="modal" data-bs-target="#detail-modal" style="cursor:pointer;" @click="goDetailModal(nft, idx)">Detail</span>
                            

                            <!-- 디테일 모달!!! -->
                            <b-modal class="modal fade" id="detail-modal" title="Detail" hide-footer>
                              <div class="container" footer-tag="footer" style="margin-bottom: 20px;">
                                <b-card-header>
                                  <div class="picture"><img :src="selectBrandImg" alt="nft_img" style="max-width: 20rem; width: 300px; height: 250px;"></div>
                                </b-card-header>
                                <b-card-body style="max-width: 20rem;">
                                  <b-card-title style="margin-bottom: 20px;">{{ selectedBrandName }}</b-card-title>
                                  <b-card-text>
                                    <p style=" font-size: 0.9rem;" >nft name: {{ nftName }}</p>
                                    <p >description : {{ description }}</p>
                                    <div  style="width: 20rem;">
                                      <p style="float: left; margin: 0; font-size: 0.8rem;">카테고리 : {{ productType }}</p>
                                      <p style="float: right; margin: 0; font-size: 0.8rem;">{{ madeCountry }}</p>
                                    </div>
                                    <div class="d-flex" style="width: 20rem; justify-content: space-between;">
                                      <p style="float: left; margin: 0; font-size: 0.8rem;">소재 : {{ material }}</p>
                                      <p style="float: right; margin: 0; font-size: 0.8rem;">{{ price }}</p>
                                    </div>
                                  </b-card-text>
                                </b-card-body>
                                <b-card-footer class="footerr-tag text-muted" style="max-width: 20rem; " >
                                <!-- <hr> -->
                                  <div style="width: 20rem;">
                                    <p style="float: left; margin: 0; font-size: 0.8rem;">Serial Number: {{ serialNumber }}</p>
                                    <p style="float: right; margin: 0; font-size: 0.8rem;">제조일자 : {{ madeDate }}</p>
                                  </div>
                                </b-card-footer>
                              </div>
                              <div class="modal-footer">
                                <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
                                <!-- <button type="button" class="btn transeferBtn" data-bs-dismiss="modal" block @click="sendToken">transfer</button> -->
                                <button type="button" class="btn transeferBtn" data-bs-dismiss="modal" block >닫기</button>
                              </div>
                            </b-modal>
                            <!-- 디테일 모달 끝 -->


                          </div>
                        </figcaption>
                      </figure>
                    </div>
                    <div class="card col-3" style="padding:0px; width:85%;" v-else>
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img src="@/assets/cslogo.png" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%; ">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" v-if="nft.name" style="color:white;">이전 중인 NFT입니다.</h2>
                          <!-- <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p> -->
                          <!-- <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" @click="tokenChangeNum(nft.tokenId)" style="cursor:pointer;">transfer</span> -->
                        </figcaption>
                      </figure>
                    </div>

                  


                  </div>
                </div>
                
            </div>
          </div>
        </div>

        <!-- 메인 지갑 설정 -->
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; left:47%; width : 41%;min-width:650px; min-height:300px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div align="left" >메인 지갑 설정</div>
                  <!-- <hr style="margin:15px 0;"> -->
                  <!-- <div>현재 지갑 주소 </div> -->
                  <div class="form__group field" style="margin-left:40px; margin-top:30px;">
                    <input type="input" class="form__field" placeholder="Name" name="address" v-model="walletAddress" required />
                    <label for="name" class="form__label" style="font-size:18px;">WALLET ADDRESS</label>
                  </div>
                  <div class="form__group field" style="margin-left:40px;">
                    <input type="input" class="form__field" placeholder="Name" name="privatekey" v-model="privatekey" required />
                    <label for="name" class="form__label" style="font-size:18px;">WALLET PRIVIATEKEY</label>
                  </div>
                  
              </div>
              <div align="right" style="margin-right:25px;">
                <button style="width:80px; margin-top:25px; background-color: #2dce89; border:0px; border-radius:5px; height:40px;" @click="sendWalletInfo">확인</button>
              </div>
            </div>
          </div>
        </div>

        <!-- 도넛 차트 -->
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 610px; left:47%; width : 41%;min-width:650px; min-height:300px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div align="left" >메인 지갑 설정</div>
                <div class="row" style="" >
                  <div>{{testt}}</div>
                  <!-- <hr style="margin:15px 0;"> -->
                  <!-- <div>현재 지갑 주소 </div> -->
                  <!-- <DoughnutChart :chartData="tes tData" style="width:100%; "/> -->
                  
                </div>
            
            </div>
          </div>
        </div>
    








    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
  <!-- </div> -->
</template>

<script>
import api from "@/services/api.js"
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import { useRouter } from 'vue-router'
import LookupNFTs from '@/utils/LookupNFT.js'
import {ref, computed, } from 'vue'
import {useStore} from 'vuex'
import TransferToken from '@/utils/TransferNFT.js'
import checkAccount from '@/utils/CheckMainWallet.js'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
import { DoughnutChart, } from 'vue-chart-3';
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);




export default {
  name: 'MyNfts',
  components: {
    Sidebar,
    DoughnutChart,
  },
  setup() {
    const toast = () => {
        createToast(
        { title: 'some title', description: 'some good description', },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'success', showIcon:true, position:'bottom-right', }
        )
    }


    const word = ref("")
    const store = useStore()
    const router = useRouter()
    // const store = useStore()
    const nfts = ref([])
		const receiveAccount = ref('')
    const testt = store.state.nftValues


    function sendNft() {
      alert('전송 되었습니다.')
    }

    // nft 디테일로 가기
    function goMyNftDetail() {
      router.push({name: 'MyNftDetail'})
    }
    // if (store.state.nftValues.length === 0) {
    //   LookupNFTs()
    // }
    nfts.value = store.state.nftValues
    console.log(nfts.value)

		const tokenNum = ref(0)
		const tokenChangeNum = (e) => {
			// console.log(e)
			tokenNum.value = e
		}

		const sendToken = () => {
			TransferToken(receiveAccount.value ,tokenNum.value)
		}
    

    // const worth = computed(() => {
    //   return store.state.nftValues.map(function(x) {return parseInt(x.productPrice.substring(0,1)+x.productPrice.substring(2,5))}).reduce(function(a,b) { return a+b;},0)
    // })

    // const highestPrice = computed(() => {
    //   return Math.max.apply(null, store.state.nftValues.map(function(x) {return parseInt(x.productPrice.substring(0,1)+x.productPrice.substring(2,5))}))
    //   })

    // 메인 지갑 설정
    const myWallet = ref('')
    // if (store.state.userInfo.address) {
    //   myWallet.value = store.state.userInfo.address
    //   return
    // } else if (store.state.walletInfo) {
    //   myWallet.value = store.state.walletInfo
    // }

    // const myWallet = ref(store.state.auth.user.address)
    const walletAddress = ref('')
    const privatekey = ref('')

    const sendWalletInfo = () => {
      checkAccount(walletAddress.value, privatekey.value).then(res => {
        if (res.address == walletAddress.value) {
          
          console.log(store.state.auth.user.id, walletAddress.value)
          api.put("/user", {
            userId : store.state.auth.user.id, wallet : walletAddress.value 
          })
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
          console.log(res.address)
          store.dispatch("setWallet",res.address)
          myWallet.value = store.state.walletInfo
          createToast(
            { title: 'Mainwallet is registered',  },
            // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
            { type:'success', showIcon:true, position:'bottom-right', }
          )
        } else {
          createToast(
            { title: 'Information is not correct',  },
            // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
            { type:'danger', showIcon:true, position:'bottom-right', }
          )
        }
        })
      // console.log(abc)
    }

    // 메인지갑 복사
    function copyToClickBoard(){
      var content = document.getElementById('copytext').innerHTML;

      navigator.clipboard.writeText(content)
          .then(() => {
          console.log("Text copied to clipboard...")
      })
          .catch(err => {
          console.log('Something went wrong', err);
      })
  
      }

    
    //테스트 도넛
    // store.state.nftValues 에 나중에 더미 정상적으로 작동하면 사용
    
    const testData = {
      labels: ['chanel', 'louis vuitton', 'gucci', 'cartier', 'versace','fendi'],
      datasets: [
        {
          data: [0, 0, 0, 0, 0],
          backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4', '#A5C8ED'],
        },
      ],
    };

    nfts.value.forEach( e =>{
      if (e.brandName.toLowerCase() == 'chanel') {
        testData.datasets[0].data[0] += 1
      } else if (e.brandName.toLowerCase() == 'louis vuitton') {
        testData.datasets[0].data[1] += 1
      } else if (e.brandName.toLowerCase() == 'gucci') {
        testData.datasets[0].data[2] += 1
      } else if (e.brandName.toLowerCase() == 'cartier') {
        testData.datasets[0].data[3] += 1
      } else if (e.brandName.toLowerCase() == 'versace') {
        testData.datasets[0].data[4] += 1
      } else if (e.brandName.toLowerCase() == 'fendi') {
        testData.datasets[0].data[5] += 1
      }
    })
    
    // 필터 부분
    const brandSelected = ref(null)
    const categorySelected = ref(null)
    const searchSelected = ref(null)



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

    // 디테일 모달
    const goDetailModal = (index) => {
      console.log(index, '뭘까?')
      console.log(index.brandName, '모달 함수 브랜드 뭘까?')
      showDetailModal.value = true;
      selectedBrandName.value = index.brandName;
      selectBrandImg.value = index.image;
      madeCountry.value = index.countryOfManufacture
      madeDate.value = index.dateOfManufacture
      description.value = index.description
      material.value = index.material
      nftName.value = index.name
      productType.value = index.productClassification
      productColor.value = index.productColor
      price.value = index.productPrice
      serialNumber.value = index.serialNumber
    }

    const userRole = store.state.userInfo
    console.log(userRole.role, '유저정보')

    return {
      goMyNftDetail,
      sendNft,
      LookupNFTs,
      nfts,
			receiveAccount,
			sendToken,
			tokenNum,
      tokenChangeNum,
      // worth,
      // highestPrice,
      checkAccount,
      walletAddress,
      privatekey,
      sendWalletInfo,
      myWallet,
      copyToClickBoard,
      toast,
      testData,
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
      goDetailModal,
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
      userRole,
      testt,
    }
  },
}
</script>

<style lang="scss" scoped>

.transeferBtn {
  background-color: #2dce89;
}

// 필터링 부분
.searchbarr {
  display: flex;
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
  background-color: #32CCBC !important;
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
  background-color: #32CCBC !important;
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
	width: 300px;
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
		font-size: 23px;
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
/* demo */



//input



//비반응형 설정

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


</style>
