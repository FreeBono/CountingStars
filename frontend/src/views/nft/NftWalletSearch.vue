<template>
  
  <sidebar/>
  <div class="wrapper">
   
    <div class="main-content">
      <div class="header">
        <p class="head_title">NFT 조회</p>
      </div>
      
        <div class="searchBarTag mt-3">
          <div class="container ">
            <div class="row" style="width:100%;">
         
                
                <div class="input-group mb-3" style="width:75%;">
                  <select style="height:45px; border:0px; width:6rem;">
                    <option>지갑 주소</option>
                    <option>아이디</option>
                  </select>
                    
               
                  <input type="text" class="form-control input-text" style="" placeholder="Search products...." aria-label="Recipient's username" aria-describedby="basic-addon2" v-model="walletAddress">
                  <div class="input-group-append"> <button class="btn btn-outline-warning btn-lg" type="button" style="z-index:5;"><i class="fa fa-search" @click="searchWallet"></i></button> </div>
                </div>
           
            </div>
            <div class="row" v-if="nfts.length ===0">weew</div>
            <div class="row"  v-else>
          
              <div class="col-3" v-for="(nft,idx) in nfts" :key="idx">
                <div class="card col-3" style="padding:0px; ">
                  <figure class="card__thumb" style="margin:0px; height:450px;">
                    <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                    <figcaption class="card__caption" style="left:27%;">
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
import {ref, onBeforeUpdate, onUpdated} from 'vue'
import {useStore} from 'vuex'


export default {
  name: 'NftWalletSearch',
  components: {
    Sidebar,
  },
  setup() {
    // const values = ref([])
    const store = useStore()
    const router = useRouter()
    // const datas = 'ipfs://QmVHcbX4KFHGfdkWbaFNT6x66LKrHaKCdR1THD42pbMWc5'.substring(7)
    // console.log(datas)
    const walletAddress = ref('')
    const nfts = ref([])
    
    
    
    function goNumberSearch() {
      router.push({name: 'NftNumberSearch'})
    }
    
    const searchWallet = () => {
      console.log('실행')
      searchNFTs(walletAddress.value)
      setTimeout(()=> {
      nfts.value.push(...store.state.searchednft)
      store.state.searchednft = []
      },3000)
      
  
    }

    
    
    
 

    return {
      goNumberSearch,
      searchWallet,
      walletAddress,
      nfts,
   
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