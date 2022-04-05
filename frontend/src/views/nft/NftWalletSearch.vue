<template>
  
  <sidebar style=""/>
  <div class="wrapper">

    <div class="main-content">
      <div class="header">
        <div style="position:absolute; margin-left:100px; margin-top: 50px; color:white; font-size: 2rem;">NFT 조회</div>
        <div class="head_title" style="font-size: 2rem;">

          <div class="container d-flex justify-content-center" >
            <div class="card mt-7 p-4" style="width:500px;" id="search-card">
              <div class="input-group" style=""> 
                <select style="border-color: #ced4da; width:5.5rem; height: 40px; font-size: 1rem;">
                  <option>지갑주소</option>
                  <option>아이디</option>
                </select>
                <input type="text" class="form-control" placeholder="Search products...." v-model="walletAddress" style="height: 40px;">
                <div class="input-group-append btn_position">
                  <button class="btn " style="background-color: #2dce89; height: 40px;" @click="searchWallet"><i class="fas fa-search" style="color: white;" ></i></button>
                </div>
              </div>
            </div>
          </div>

          <!-- <div class="input-group mb-3" style="width:500px;">
            <select style="height:45px; border:0; width:6rem;">
              <option>지갑 주소</option>
              <option>아이디</option>
            </select>
              
          
            <input type="text" class="form-control input-text" style="height:45px; border:0px;" placeholder="Search products...." aria-label="Recipient's username" aria-describedby="basic-addon2" v-model="walletAddress">
            <div class="input-group-append"> <button class="btn btn-outline-warning btn-lg" type="button" style="height:45px; z-index:5; border-radius:0px;"><i class="fa fa-search" @click="searchWallet"></i></button> </div>
          </div> -->
        </div>
      </div>



      
      <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; min-width:590px; overflow-y:scroll; max-height:600px;">
        <div  class="container-fluid">
          <div class="row">
            <div class="searchBarTag mt-3">

              <!-- 필터링 부분 -->
                <div class="searchbarr mb-4">
                  <select class="brandSel-tag" v-model="brandSelected" @change="brandSel()" >
                    <option v-for="(brandoption, idx) in brandOpt" :key="idx" :value="brandoption.value">
                      {{ brandoption.text }}
                    </option>

                  </select>
                  <select class="categorySel-tag" v-model="categorySelected" @change="headerSel()" >
                    <option value="null" selected >카테고리</option>
                    <option value="Class Bag">Bag</option>
                    <option value="accessory">Accessory</option>
                    <option value="Clothes">Clothes</option>
                    <option value="Shoes">Shoes</option>
                    <option value="Wallet">Wallet</option>
                  </select>

                  <b-form-input class="mx-2" b-form-input style="width: 350px; height: 40px; font-size: 15px;" placeholder="검색할 nft 이름을 입력하세요." v-model="word" @keydown.enter="searchTitle()" autocomplete="off"></b-form-input>
                  <b-button class="searchBtn mr-1" @click="searchTitle()">검색</b-button>
                  <b-button class="resetSearchBtn" @click="searchInit()">초기화</b-button>
                </div>
                <!-- 필터링 부분 끝 -->

              <div v-for="(item,idx) in historiesUnique" :key="idx"  >
                <span class="tag tag-ionic tag-lg" style="margin:0px 10px; white-space: nowrap;" >
                
                  <span @click="historySearch(idx)"><i class="fa fa-search" style="margin-right:10px;"></i>{{convertedHistories(item)}}</span>
                </span>
              </div>
          </div>

          
            
            <!-- <div class="container justify-content-center"> -->
              <div class="row" >
                <div class="col-3" v-for="(nft,idx) in nfts" :key="idx" >
                    <div class="card col-3" style="padding:0px; width:85%;" >
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" style="color:white;" v-if="nft.name">{{nft.name}}</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                      
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
                    

                  


                </div>
            </div>
          </div>
        </div>
      </div>
      <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; left:47%; width : 41%;min-width:650px; overflow-y:scroll; max-height:600px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div align="left" style="margin-left:10px; margin-top:10px; ">월별 NFT이전</div>
                  <!-- <hr style="margin-top:15px 0;"> -->
                  <div align="center" style=" margin-top:10px; " >
                    <div class="d-flex" style="padding-top:10px; height:40px; border:2px solid #e7eaf3; background-color: #f8fafd;  color: #6c757e;">
                      <div style="width:20%; text-align:left;">Txn Hash</div><div style="width:20%; text-align:left;">Block</div><div style="width:25%; text-align:left;">From</div><div style="width:25%; text-align:left; margin-left:10px;">To</div><div style="width:10%; text-align:left;">Token ID</div>
                    </div>
                    <div v-for="(item,idx) in transactions" :key="idx" align="left" style="margin-top:10px;">
                      <div class="d-flex">
                      <div class="testt" style="width:20%;" @click="goTxDetail(item.transactionHash)" data-bs-toggle="modal" :data-bs-target="'#exampleModal'+idx">
                        {{item.transactionHash.substring(0,10)}}...
                      </div>
                      <!-- Modal -->
                      <div class="modal fade" :id="'exampleModal'+idx" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="exampleModalLabel">Transaction Details</h5>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                              <div class="d-flex">
                                <div style="width:25%; font-size:14px">
                        
                                  <div class="TxDetail" style="font-weight:bold;">Transaction Hash</div><div class="TxDetail" style="font-weight:bold;">Status</div ><div class="TxDetail" style="font-weight:bold;">Block Hash</div><div class="TxDetail" style="font-weight:bold;">Block Number</div>
                                  <hr>
                                  <div class="TxDetail" style="font-weight:bold;">From</div><div class="TxDetail" style="font-weight:bold;">To</div>
                                  <hr>
                                  <div class="TxDetail" style="font-weight:bold;">Gas Price</div>
                              
                                </div>
                                <div style="width:75%; font-size:14px">
                                  <div class="TxDetail">{{TxData.hash}}</div><div class="TxDetail"><span class="status">&nbsp;&nbsp;<i class="fa fa-check-circle"></i>&nbsp;Success&nbsp;&nbsp;</span></div><div class="TxDetail">{{TxData.blockHash}}</div><div class="TxDetail">{{TxData.blockNumber}}</div>
                                  <hr>
                                  <div class="TxDetail" id="copytext" >{{TxData.from}}<i class="far fa-copy" style="margin-left:10px; cursor:pointer;" @click="copyToClickBoard(TxData.from)"></i></div><div class="TxDetail" @click="copyToClickBoard(TxData.to)">{{TxData.to}}<i class="far fa-copy" style="margin-left:10px; cursor:pointer;"></i></div>
                                  <hr>
                                  <div class="TxDetail" id="copytext2">{{TxData.gasPrice}}</div>
                                </div>
                              
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="testt" style="width:20%;">
                        {{item.blockHash.substring(0,10)}}...
                      </div>
                      <div class="testt" style="width:25%;">
                        <div style="float:left;">
                        {{item.returnValues.from.substring(0,10)}}...
                        </div>
                        <div class="warning" style="float:right; margin-right:10px;" v-if="item.returnValues.from===walletAddress">
                          out
                        </div>
                        <div class="success" style="float:right; margin-right:10px;"  v-else>
                          in
                        </div>
                        
                      </div>
                      <div class="testt" style="width:25%; margin-left:10px;">
                        {{item.returnValues.to.substring(0,10)}}...
                      </div>
                      <div class="testt" style="width:10%; text-align:center;">
                        {{item.returnValues.tokenId}}
                      </div>
                      </div>
                      <hr>
                      
                    </div>

                  </div>
              </div>
            </div>
          </div>
        </div>






    </div>

  </div>






</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import "@/assets/style/notice/noticeTable.css"
import { useRouter } from 'vue-router'
import searchNFTs from '@/utils/WalletSearch'
import {ref, computed} from 'vue'
import {useStore} from 'vuex'
import { Carousel, Pagination, Slide } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
import { useCookies } from "vue3-cookies";
import SearchToken from '@/utils/SearchNFT'
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
import CheckTransaction from '@/utils/CheckTransaction'
import api from "@/services/api.js"


export default {
  name: 'NftWalletSearch',
  components: {
    Sidebar,
    Carousel,
    Slide,
    Pagination,
    VueperSlides,
    VueperSlide,
  

  },
  setup() {

    const store = useStore()
    const router = useRouter()
    const walletAddress = ref('')
    const nfts = ref([])
    
    //transactions
    const transactions = ref([])
    const TxData = ref([])
    const goTxDetail = (txHash) => {
      CheckTransaction(txHash).then(res => {
        console.log(res)
        TxData.value = res
        })
    
    }
    
    

    //지갑조회실행
    const searchWallet = () => {
      nfts.value = []
      console.log('실행')
      // localStorage.setItem(  , JSON.stringify({a: 1, b: 2}))
      searchNFTs(walletAddress.value)
      addEntry()
      SearchToken(walletAddress.value).then(res => transactions.value = res.sort(function(a,b) {
        return b.blockNumber - a.blockNumber
      }))
      setTimeout(()=> {
      nfts.value.push(...store.state.searchednft)
      store.state.searchednft = []
      },2000)
      
    }
  
    // 지갑 검색 기록
    function addEntry() {
      var existingEntries = JSON.parse(localStorage.getItem(store.state.auth.user.email));
      if(existingEntries == null) existingEntries = [];
      var value = {
          "searchHistory": walletAddress.value,
      };
      localStorage.setItem("value", JSON.stringify(value));
      existingEntries.push(value);
      localStorage.setItem(store.state.auth.user.email, JSON.stringify(existingEntries));
    };

    const histories = JSON.parse(localStorage.getItem(store.state.auth.user.email))
    const historiesUnique = []
    if (histories != [] && histories != null) {
      histories.forEach(e => {
      if (!(historiesUnique.includes(e.searchHistory)) && historiesUnique.length <7) {
        console.log(e.searchHistory)
        historiesUnique.push(e.searchHistory)
    }})
    }

    const convertedHistories = (x) => {
      
      return x.substring(0,8) + '...' + x.substring(34,42)
    }

    const historySearch = (x) => {
      walletAddress.value = histories[x].searchHistory
      searchWallet()
    } 

    // 필터 부분
    const brandSelected = ref(null)
    const categorySelected = ref(null)
    const searchSelected = ref(null)
    const word = ref("")


      // filter 사용될 데이터들
    
    const filters = ref([null,null,null])
  

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
    
    
    const searchPaging = () => {
      rowws.value = store.state.nftValues.length;
    }

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


    // 주소 복사
    function copyToClickBoard(val){
      var content = val;
      navigator.clipboard.writeText(content)
          .then(() => {
          console.log("Text copied to clipboard...")
      })
          .catch(err => {
          console.log('Something went wrong', err);
      })
  
      }
    return {

      searchWallet,
      walletAddress,
      nfts,
      addEntry,
      histories,
      convertedHistories,
      historySearch,
      historiesUnique,
      transactions,
      goTxDetail,
      TxData,
      brandSelected,
      categorySelected,
      searchSelected,
      brandOpt,
      categoryOpt,
      searchInit,
      searchOpt,
      word,
      searchInit,
      searchPaging,
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

      copyToClickBoard,
    }
  },

}
</script>

<style lang="scss" scoped>

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



.carousel__slide > .carousel__item {
  transform: scale(1);
  opacity: 0.5;
  transition: 0.5s;
}
.carousel__slide--visible > .carousel__item {
  opacity: 1;
  transform: rotateY(0);
}
.carousel__slide--next > .carousel__item {
  transform: scale(0.6) translate(-10px);
  opacity: 0.8;
}
.carousel__slide--prev > .carousel__item {
  transform: scale(0.6) translate(10px);
  opacity: 0.8;
}
.carousel__slide--active > .carousel__item {
  transform: scale(1.02);
  
}



// 태그
:root {
    --red:#ff3860;--red-dark:#ff1443;--red-light:#ff5c7c;--blue:#498afb;--blue-dark:#2674fa;--blue-light:#6ca0fc;--orange:#fa8142;--orange-dark:#f96a1f;--orange-light:#fb9865;--green:#09c372;--green-dark:#07a15e;--green-light:#0be586;--purple:#9166cc;--purple-dark:#7d4bc3;--purple-light:#a481d5;--yellow:#ffdd57;--yellow-dark:#ffd633;--yellow-light:#ffe47a;--pink:#ff4088;--pink-dark:#ff1c72;--pink-light:#ff649e;--gray0:#f8f8f8;--gray1:#dbe1e8;--gray2:#b2becd;--gray3:#6c7983;--gray4:#454e56;--gray5:#2a2e35;--gray6:#12181b;--nav-width:4em;--font-body:"sofia-pro",sans-serif;--font-head:"sofia-pro",sans-serif;--font-code:"attribute-mono",monospace;--font-size:20px;--max-width-bp:768px;--orange-pink:linear-gradient(to bottom right,var(--orange-light),var(--orange-dark) 85%);--green-grad:linear-gradient(to bottom right,var(--green-light),var(--green-dark) 85%);--background:var(--gray6);--text-color:var(--gray2);--h-color:#fff;--nav-shadow:4px 0 10px -3px #010101;--card-shadow:0 4px 8px rgba(0,0,0,0.38);--toc-shadow:rgba(0,0,0,0.7) 0px 10px 20px 0px;--nav-bg:var(--gray5);--tag-bg:var(--gray4);--code-bg:#22262f;--card-bg:var(--gray5);--overlay-bg:rgba(0,0,0,0.9);--h-border:2px dashed var(--nav-bg);--nav-border:2px dashed var(--text-color);--card-radius:0.25em;transition: all .3s ease
}

.tag-ionic {
    background: #fff;
    color: #4a8afc
}

.tag {
    display: inline-block;
    border-radius: 3px;
    padding: .2em .5em .3em;
    border-radius: 2px;
    background: var(--tag-bg);
    color: var(--text-color);
    font-weight: 600;
    margin: .25em .1em;
    float:left;
}

h1.tag {
    margin-left: 0;
    margin-right: 0
}

.tag-sm {
    font-size: .7em;
    display: inline-block;
    letter-spacing: .15ch;
    font-weight: 400
}

.tag-lg {
    font-size: 1.2em;
    border-radius: 4px
}

.tag-bg {
    background: var(--background)
}

.tag-ionic {
    background: #fff;
    color: #4a8afc;
    border: 1px solid #4a8afc;
    font-size : 14px;
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





// transaction 부분
.testt {
  // overflow: hidden;
  // text-overflow: ellipsis;
  // white-space: nowrap;
  // // width: 100px;
  height: 20px;
}

.success {
    color: #02977e;
    background-color: rgba(0,201,167,.2);
    width:40px;
    text-align:center;
    border-radius:10px;
}

.warning {
    color: #b47d00;
    background-color: rgba(219,154,4,.2);
    width:40px;
    text-align:center;
    border-radius:10px;
}

.TxDetail {
  height : 40px;
  
}

.status {
  color: #00c9a7;
  background-color: rgba(0,201,167,.1);
}

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

#search-card {
  box-shadow: 1px 2px 5px 2px #d8d7d7;
  // box-shadow: none;
  display: flex;
  flex-wrap: wrap;
  align-content: center;
}

.btn_position {
  position: relative;
  bottom: 10px;
}
</style>