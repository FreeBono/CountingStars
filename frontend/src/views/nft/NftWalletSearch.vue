<template>
  
  <sidebar style=""/>
  <div class="wrapper">

    <div class="main-content">
      <div class="header">
        <div class="head_title" style="font-size:16px;">
          <div class="input-group mb-3" style="width:500px; ">
            <select style="height:45px; border:0; width:6rem;">
              <option>지갑 주소</option>
              <option>아이디</option>
            </select>
              
          
            <input type="text" class="form-control input-text" style="height:45px; border:0px;" placeholder="Search products...." aria-label="Recipient's username" aria-describedby="basic-addon2" v-model="walletAddress">
            <div class="input-group-append"> <button class="btn btn-outline-warning btn-lg" type="button" style="height:45px; z-index:5; border-radius:0px;"><i class="fa fa-search" @click="searchWallet"></i></button> </div>
          </div>
        </div>
      </div>
      

      
      <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; min-width:590px; overflow-y:scroll; max-height:600px;">
        <div  class="container-fluid">
          <div class="row">
            <div class="searchBarTag mt-3">
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
                                  <div class="picture"><img :src="selectBrandImg" alt="nft_img" style="max-width: 20rem;"></div>
                                </b-card-header>
                                <b-card-body style="max-width: 20rem;">
                                  <b-card-title style="margin-bottom: 20px;">{{ selectedBrandName }}</b-card-title>
                                  <b-card-text>
                                    <p style=" font-size: 0.9rem;" >nft name: {{ nftName }}</p>
                                    <p >description : {{ description }}</p>
                                    <!-- <p>serialNumber : {{ serialNumber }}</p> -->
                                    <p style="margin: 0; font-size: 0.8rem;">카테고리 : {{ productType }}</p>
                                    <p style="margin: 0; font-size: 0.8rem;">소재 : {{ material }}</p>
                                    <p style="margin: 0; font-size: 0.8rem;">제조국가 : {{ madeCountry }}</p>
                                    <!-- <p>색상 : {{ productColor }}</p> -->
                                    <p style="margin: 0; font-size: 0.8rem;">가격 : {{ price }}</p>
                                    <!-- <p>제조일 : {{ madeDate }}</p> -->
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
                                <button type="button" class="btn transeferBtn" data-bs-dismiss="modal" block @click="sendToken">transfer</button>
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
                                  <div class="TxDetail">{{TxData.hash}}</div><div class="TxDetail"><span class="status">&nbsp;&nbsp;Success&nbsp;&nbsp;</span></div><div class="TxDetail">{{TxData.blockHash}}</div><div class="TxDetail">{{TxData.blockNumber}}</div>
                                  <hr>
                                  <div class="TxDetail">{{TxData.from}}</div><div class="TxDetail">{{TxData.to}}</div>
                                  <hr>
                                  <div class="TxDetail">{{TxData.gasPrice}}</div>
                                </div>
                              
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                              <button type="button" class="btn btn-primary">Save changes</button>
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

                    <!-- <div v-if="transactions.length===0">검색결과음슴</div>
                    <div v-else>{{transactions}}</div> -->
                  </div>
              </div>
            </div>
          </div>
        </div>




<!-- <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">NFT를 이전할 지갑 주소를 입력해주세요.</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
    
				<div>
					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Name" name="name" id='name' v-model="receiveAccount" required />
						<label for="name" class="form__label">Account</label>
					</div>
				</div>

      </div>
      <div class="modal-footer">

        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="sendToken">transfer</button>
      </div>
    </div>
  </div>
</div> -->




    </div>
    <!-- 내용 들어갈 곳 끝 -->
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
    // const values = ref([])
    const store = useStore()
    const router = useRouter()
    // const datas = 'ipfs://QmVHcbX4KFHGfdkWbaFNT6x66LKrHaKCdR1THD42pbMWc5'.substring(7)
    // console.log(datas)
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
      // console.log(TxData.value)
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
  
    function addEntry() {
      // Parse any JSON previously stored in allEntries
      var existingEntries = JSON.parse(localStorage.getItem(store.state.auth.user.email));
      // console.log(existingEntries)
      if(existingEntries == null) existingEntries = [];
      var value = {
          "searchHistory": walletAddress.value,
      };
      // console.log(value)
      localStorage.setItem("value", JSON.stringify(value));
      // Save allEntries back to local storage
      existingEntries.push(value);
      localStorage.setItem(store.state.auth.user.email, JSON.stringify(existingEntries));
      // console.log(localStorage.getItem(store.state.auth.user.email).searchHistory)
    };

    const histories = JSON.parse(localStorage.getItem(store.state.auth.user.email))
    const historiesUnique = []
    if (histories != [] && histories != null) {
      // console.log(histories)
      histories.forEach(e => {
      if (!(historiesUnique.includes(e.searchHistory)) && historiesUnique.length <7) {
        console.log(e.searchHistory)
        historiesUnique.push(e.searchHistory)
    }})
    }



    console.log(histories)
    console.log(historiesUnique)
    const convertedHistories = (x) => {
      
      return x.substring(0,8) + '...' + x.substring(34,42)
    }

    const historySearch = (x) => {
      // nfts.value = []
      walletAddress.value = histories[x].searchHistory
      searchWallet()
      // addEntry()
      // searchNFTs(walletAddress.value)
      // setTimeout(()=> {
      //   nfts.value.push(...store.state.searchednft)
      //   store.state.searchednft = []
      // },3000)
      
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
</style>