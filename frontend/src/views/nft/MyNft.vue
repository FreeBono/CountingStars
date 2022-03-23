<template>
  <!-- <h2>My NFT 상세보기</h2> -->
  <sidebar/>
  
  
  <div class="wrapper">

    <!-- 내용 들어갈 곳 -->
    <div class="main-content">

      <div class="header">
        <div style="position:absolute; margin-left:100px; margin-top: 50px; color:white;"> My NFTs</div>
        <div class="row-vh d-flex" style="margin-left:80px; width:90vw; margin-top:100px;">
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:15px;">  
                    <div>TOTAL NFTS</div>
                    <div align="left">{{nfts.length}}</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-handshake fa-2x"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden; ">
                  <div class="media-body" style="float:left; margin-top:15px;">  
                    <div>MAIN WALLET ADDRESS</div>
                    <div align="left">{{nfts.length}}</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-handshake fa-2x" style="color:gold;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:15px;">  
                    <div>TOTAL WORTH</div>
                    <div align="left">{{worth.toLocaleString('ko-KR')}}$</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-handshake fa-2x"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; margin-top:15px;">  
                    <div>HIGHEST PRICE</div>
                    <div align="left">{{highestPrice.toLocaleString('ko-KR')}}$</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <i class="fas fa-handshake fa-2x"></i>
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
        
  
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div class="col-3" v-for="(nft,idx) in nfts" :key="idx">
                    <div class="card col-3" style="padding:0px; width:175px;">
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" v-if="nft.name">{{nft.name}}</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                          <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" >Detail</span>
                        </figcaption>
                      </figure>
                    </div>
                  </div>
              </div>
            </div>
          </div>
        </div>

        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; left:900px; width:800px">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->
                <div class="row" >
                  <div align="left" >Highest Value</div>
                  <hr style="margin-top:15px 0;">
                  <div></div>
              </div>
            </div>
          </div>
        </div>
    



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
						<label for="name" class="form__label">Account</label>
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
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import { useRouter } from 'vue-router'
import LookupNFTs from '@/utils/LookupNFT.js'
import {ref, computed } from 'vue'
// import axios from 'axios'
import {useStore} from 'vuex'
import TransferToken from '@/utils/TransferNFT.js'





export default {
  name: 'NftTransfer',
  components: {
    Sidebar,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    // const store = useStore()
    const nfts = ref([])
		const receiveAccount = ref('')
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

		const sendToken = () => {
			// console.log(tokenNum.value)
			TransferToken(receiveAccount.value ,tokenNum.value)
		}
    
    // console.log(nfts)
    

    const worth = computed(() => {
      return store.state.nftValues.map(function(x) {return parseInt(x.productPrice.substring(0,1)+x.productPrice.substring(2,5))}).reduce(function(a,b) { return a+b;},0)
    })

    const highestPrice = computed(() => {
      return Math.max.apply(null, store.state.nftValues.map(function(x) {return parseInt(x.productPrice.substring(0,1)+x.productPrice.substring(2,5))}))
      })

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
      highestPrice
    }
  }
}
</script>

<style lang="scss" scoped>
.sidenav {
  text-align: left;
  box-sizing: border-box;
  padding: 1rem 1rem;
  flex-wrap: wrap;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-top: 0;
  border-style: solid;
  z-index: 1000;
  transition: all .15s ease;
  background-color: #333333!important;
  display: block;
  position: fixed;
  top: 0;
  bottom: 0;
  width: 100%;
  padding-left: 0;
  padding-right: 0;
  max-width: 250px!important;
  transform: translateX(0);
  left: 0;
  border-width: 0 1px 0 0;
}

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


</style>