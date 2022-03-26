<template>
  <!-- <h2>My NFT 상세보기</h2> -->
  <sidebar/>
  
  <div class="test">
  <div class="wrapper">

    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <div style="position:absolute; margin-left:100px; margin-top: 50px; color:white;" @click="toast"> My NFTs</div>
        <div class="row-vh d-flex" style="margin-left:80px; width:90%; margin-top:100px;">
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
          <div class="card" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:25%; border-radius:10px; text-align:left;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden; ">
                  <div class="media-body" style="float:left; margin-top:15px;">  
                    <div style="txt-align:left;">MAIN WALLET ADDRESS</div>
                    <div align="left" style="word-break:break-all;" @click="copyToClickBoard" v-if="myWallet">{{myWallet.substring(0,8) + '...' + myWallet.substring(34,42)}}</div>
                    <div align="left" style="word-break:break-all;" @click="copyToClickBoard" v-else>지갑을 설정해주세요..</div>
                    <div align="left" style="word-break:break-all; display:none;" id="copytext" @click="copyToClickBoard" >{{myWallet}}</div>
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
        
  
        <div class="content_box row-vh d-flex flex-row" style="position:absolute; top : 280px; min-width:590px; overflow-y:scroll; max-height:600px;">
          <div  class="container-fluid">
            <div class="searchBarTag mt-3">
              <!-- <div class="container justify-content-center"> -->


                <!-- 필터링 부분 -->
                <div class="searchbarr mb-4">
                  <b-form-select v-model="brandSelected" :options="brandOpt" style="width: 150px; height: 40px; font-size: 15px;" @change="brandSel()" ></b-form-select>
                  <b-form-select class="mx-2" v-model="categorySelected" :options="categoryOpt" style="width: 170px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
                  <b-form-select v-model="searchSelected" :options="searchOpt" style="width: 100px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
                  <b-form-input class="mx-2" b-form-input style="width: 250px; height: 40px; font-size: 15px;" placeholder="검색할 색상 소재를 입력하세요." v-model="word" @keydown.enter="searchTitle()" autocomplete="off"></b-form-input>
                  <b-button class="searchBtn mr-1" @click="searchTitle()">검색</b-button>
                  <b-button class="resetSearchBtn" @click="searchInit()">초기화</b-button>
                </div>
                <!-- 필터링 부분 끝 -->


                <div class="row" >
                  <div align="left" >NFT 목록</div>
                  <div class="col-3" v-for="(nft,idx) in nfts" :key="idx" >
                    <div class="card col-3" style="padding:0px; width:85%;" v-if="nft.status ===0">
                      <figure class="card__thumb" style="margin:0px; height:250px;">
                        <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                        <figcaption class="card__caption" style="left:5%;">
                          <h2 class="card__title" style="color:white;" v-if="nft.name">{{nft.name}}</h2>
                          <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                          <span class="card__button " data-bs-toggle="modal" data-bs-target="#exampleModal" style="cursor:pointer;" >Detail</span>
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
  </div>
</template>

<script>
import api from "@/services/api.js"
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import { useRouter } from 'vue-router'
import LookupNFTs from '@/utils/LookupNFT.js'
import {ref, computed } from 'vue'
// import axios from 'axios'
import {useStore} from 'vuex'
import TransferToken from '@/utils/TransferNFT.js'
import checkAccount from '@/utils/CheckMainWallet.js'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'





export default {
  name: 'NftTransfer',
  components: {
    Sidebar,
  },
  setup() {
    const toast = () => {
        createToast(
        { title: 'some title', description: 'some good description', },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'success', showIcon:true, position:'bottom-right', }
        )
    }

    // 필터 부분
    const src = ref([]) // 초기 nft를 저장할 배열

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
    // nft 디테일로 가기
    function goMyNftDetail() {
      router.push({name: 'MyNftDetail'})
    }
    if (store.state.nftValues.length === 0) {
      LookupNFTs()
    }
    nfts.value = store.state.nftValues

    // 필터 부분
    src.value = nfts.value

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

    // 메인 지갑 설정
    const myWallet = ref(store.state.auth.user.address)
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

          store.state.auth.user.address = res.adderss
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


    
    // 필터 부분
    const brandSelected = ref(null)
    const categorySelected = ref(null)
    const searchSelected = ref("color")


      // filter 사용될 데이터들
    const brandOpt = ref([
        { value: null, text: '브랜드' },
        { value: 'Chanel', text: 'CHANEL' },
        { value: 'cartier', text: 'CARTIER' },
        { value: 'louis vuitton', text: 'LOUIS VUITTON' },
        { value: 'gucci', text: 'GUCCI' },
        { value: 'versace', text: 'VERSACE' },
        { value: 'fendi', text: 'FENDI' },
        { value: 'prada', text: 'PRADA' },
        // { value: true, text: '기타' },
      ])
    
    const categoryOpt = ref([
        { value: null, text: '카테고리' },
        { value: 'bag', text: 'BAG' },
        // { value: true, text: '지갑' },
        // { value: true, text: '의류' },
        // { value: true, text: '악세사리' },
        // { value: true, text: '기타' },
      ])
    
    const searchOpt = ref([
        { value: "color", text: '색상' },
        { value: "type", text: '소재' },
      ])

    // const src = ref([]) // 초기 nft를 저장할 배열
    const word = ref("")
    const str = ref(null)
    const rowws = ref(null)
    // const nftLists = ref(store.state.searchednft)
    console.log(nfts.value, '😀리스트 확인😀')


    const searchPaging = () => {
      rowws.value = store.state.nftValues.length;
    //   if (store.state.nftValues.length === 0) {
    //   LookupNFTs()
    // }
      // currentPage = 1
      // router.go()
      console.log(store.state.nftValues.length, 'nfts.value.length')
      console.log(rowws.value, 'serchpaging')
    }

    // 검색 초기화
    const searchInit = () => {
      categorySelected.value = null;
      brandSelected.value = null;
      word.value = "";
      nfts.value = src.value;
      // this.searchPaging();
      searchPaging()
      // router.go()
    }

    // 전체 검색
    const searchTotal = () => {
      nfts.value = src.value;
      console.log(nfts.value, 'searchTotal')
      console.log(src.value, 'srccccccccccccccccc')
      // this.searchPaging();
      searchPaging()
      // console.log(searchPaging, '여기 작동하나?')
    }

    // 카테고리 셀렉
    const headerSel = () => {
      word.value ="";
      console.log(nfts.value,' headerSel----작동 확인----')

      if(categorySelected.value == null){ // 카테고리 선택을 안했을 때
        if(brandSelected.value == null){ //  선택을 안했을 때
        console.log(categorySelected.value,' headerSel----작동 확인----')
        console.log(brandSelected.value, '되나여기')
          searchTotal(); // 전체 목록 불러오기
        } else{ // 브랜드 선택을 했을 때
            nfts.value = src.value.filter((nft) => { // 브랜드에 해당하는 게시글 불러오기
            console.log(nfts.value,' headerSel----작동 확인----')
            return nft.brandName == brandSelected.value;
          });
          // searchPaging();
        }
      } else{ // 카테고리 선택을 했을 때
        if(brandSelected.value == null){ // 브랜드 선택이 안 되어 있을 때
          nfts.value = src.value.filter((nft) => { // 카테고리에 해당하는 게시글 불러오기
          console.log(nft.productClassification, '선택했을 때')
            return nft.productClassification == categorySelected.value;
          });
          // this.searchPaging();
        } else{ // 브랜드 선택이 되어 있을 때
          nfts.value = src.value.filter((nft) => { // 카테고리와 브랜드에 해당하는 게시글 불러오기
            return nft.productClassification == categorySelected.value && nft.brandName == brandSelected.value;
          });
          // this.searchPaging();
        }
      }
    }
    
    // 브랜드 선택
    const brandSel = () => {
      word.value ="";
      console.log(nfts.value, 'brandSel 확인')

      if(brandSelected.value == null){ // 브랜드을 선택 안했을 때
        if(categorySelected.value == null){ // 카테고리 선택을 안했을 때
          searchTotal(); // 전체 목록 불러오기
        } else{ // 카테고리 선택을 했을 때
          nfts.value = src.value.filter((nft) => { // 카테고리에 해당하는 게시글 불러오기
          console.log(nfts.value, 'brandSel 작동 확인')
            return nft.productClassification == categorySelected.value;
          });
          // this.searchPaging();
        }
      } else{ // 브랜드 선택을 했을 때
        if(categorySelected.value == null){ // 카테고리 선택이 안 되어 있을 때
          nfts.value = src.value.filter((nft) => { // 브랜드에 해당하는 게시글 불러오기
            return nft.brandName == brandSelected.value;
          });
          // this.searchPaging();
        } else{ // 카테고리 선택이 되어 있을 때
          nfts.value = src.value.filter((nft) => {  // 카테고리와 브랜드에 해당하는 게시글 불러오기
            return nft.productClassification == categorySelected.value && nft.brandName == brandSelected.value;
          });
          // this.searchPaging();
        }
      }
    }

    const searchTitle = () => {
      if(searchSelected.value == "color"){ // 색상이 선택 되었을 때
        if (word.value == "") { // 아무 것도 입력 되지 않았을 때
          alert("내용을 입력해주세요.")
        } else {
          if(categorySelected.value == null && brandSelected.value == null){
              nfts.value = src.value.filter((nft) => {
                if(nft.productColor.toLowerCase().includes(word.value.toLowerCase())){
                  return nft
                }
              });
              // this.searchPaging();
          }else{
            nfts.value = src.value.filter((nft) => {
              if(nft.productColor.toLowerCase().includes(word.value.toLowerCase())){
                if(categorySelected.value != null && nft.brandName != null){ 
                  return (nft.productClassification == categorySelected.value && nft.brandName == brandSelected.value)
                }
                else if(categorySelected.value != null){
                  return nft.productClassification == categorySelected.value
                }
                else if(brandSelected.value != null){
                  return nft.brandName == brandSelected.value
                }
              }
            });
            // this.searchPaging();
          }
        }
      }else if(searchSelected.value == "type"){ // 분류가 선택 되었을 때
        if (word.value == "") { // 아무 것도 입력 되지 않았을 때
          alert("소재를 입력해주세요.")
        } else {
          if(categorySelected.value == null && brandSelected.value == null){
            nfts.value = src.value.filter((nft) => {
              if(nft.material!=null && nft.material.toLowerCase().includes(word.value.toLowerCase())){
                return nft
              }
            });
            // this.searchPaging();  
          }else{
            nfts.value = src.value.filter((nft) => {
              if(nft.material!=null && nft.material.toLowerCase().includes(word.value.toLowerCase())){
                if(categorySelected.value != null && brandSelected.value != null) 
                  return (nft.productClassification == categorySelected.value && nft.brandName == brandSelected.value)
                else if(categorySelected.value != null)
                  return nft.productClassification == categorySelected.value
                else if(brandSelected.value != null)
                  return nft.brandName == brandSelected.value
              }
            });
            // this.searchPaging();
          }
        }
      }
    }

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
      checkAccount,
      walletAddress,
      privatekey,
      sendWalletInfo,
      myWallet,
      copyToClickBoard,
      toast,

      brandSelected,
      categorySelected,
      searchSelected,
      brandOpt,
      categoryOpt,
      searchOpt,
      src,
      word,
      str,
      searchTotal,
      searchInit,
      searchPaging,
      headerSel,
      brandSel,
      searchTitle,
    }
  },
}
</script>

<style lang="scss" scoped>

// 필터링 부분
.searchbarr {
  display: flex;
}

.searchBtn {
  width: 60px;
  padding: 0; 
  height: 40px; 
  font-size: 15px;
  color: #333333;
  background-color: #fff !important;
  border-color: transparent;
  border: 1px solid transparent !important;
  box-shadow: 1px 1px 2px 2px #ececf0;
}

.searchBtn:hover {
  background-color: #32CCBC !important;
  color: white;
}

.resetSearchBtn {
  padding: 0; 
  width: 60px; 
  height: 40px; 
  font-size: 15px;
  color: #333333;
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