<template>

  <!-- <h2>거래처 관리 페이지</h2> -->
  <sidebar/>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <p class="head_title">NFT 조회(지갑)</p>
      </div>
      <div class="content_outside_box">
        <!-- <div class="btnContainer">
          <button type="button" class="btn numberSearchBtn" @click="goNumberSearch">일련번호로 조회</button>
        </div> -->

        <!-- 지갑 검색 부분 -->
        
        <div class="searchBarTag mt-3">
          <div class="container justify-content-center">
            <div class="row">
              <div class="col-md-8">
                
                <div class="input-group mb-3">
                  <select style="height:45px; border:0px; width:6rem">
                    <option>지갑 주소</option>
                    <option>아이디</option>
                  </select>
                    
               
                  <input type="text" class="form-control input-text" placeholder="Search products...." aria-label="Recipient's username" aria-describedby="basic-addon2" >
                  <div class="input-group-append"> <button class="btn btn-outline-warning btn-lg" type="button" style="z-index:5;"><i class="fa fa-search"></i></button> </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-3" v-for="(nft,idx) in nfts" :key="idx">
                <div class="card col-3" style="padding:0px; ">
                  <figure class="card__thumb" style="margin:0px; height:450px;">
                    <img :src="nft.image" alt="Picture by Kyle Cottrell" class="card__image" style="width:100%; height:100%;">
                    <figcaption class="card__caption" style="left:27%;">
                      <h2 class="card__title" v-if="nft.name">{{nft.name}}</h2>
                      <p class="card__snippet">{{nft.brandName}} , {{nft.productPrice}}</p>
                      <a href="" class="card__button">transfer</a>
                    </figcaption>
                  </figure>
                </div>
              </div>


            </div>
          </div>
        </div>

     
        <div class="card">
		<figure class="card__thumb">
			<img src="https://source.unsplash.com/71u2fOofI-U/300x510" alt="Picture by Nathan Dumlao" class="card__image">
			<figcaption class="card__caption">
				<h2 class="card__title">This Is Your Body And Brain On Coffee</h2>
				<p class="card__snippet">Drinking more caffeine during the coronavirus lockdown? Here's how it can affect you over time and advice on making it better for you.</p>
				<a href="" class="card__button">Read more</a>
			</figcaption>
		</figure>
	</div>

       



     
        <!-- 페이지네이션 -->
        <nav aria-label="Page navigation">
         
        </nav>
        <!-- 페이지네이션 끝 -->
        
        <!-- 블록 이미지 부분 테두리 따기 -->
        <div class="box_img">
          <img class="mx-5" src="@/assets/icon.png" alt="블록 상자" style="width: 300px;">
          <img class="mx-5" src="@/assets/icon.png" alt="블록 상자2" style="width: 300px;">
        </div>
        <!-- 블록 이미지 끝 -->
      </div>
    </div>
      <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import "@/assets/style/notice/noticeTable.css"
import { useRouter } from 'vue-router'
import qwe from '@/utils/LookupNFT.js'
import {ref} from 'vue'
import axios from 'axios'
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
    const nfts = ref([])
    // qwe()
    function goNumberSearch() {
      router.push({name: 'NftNumberSearch'})
    }
    
    store.state.nftValues.forEach(element => {
      axios({
      method : 'get',
      url : 'https://gateway.pinata.cloud/ipfs/'+element.substring(7)
      })
      .then(res => {
        nfts.value.push(res.data)
        console.log(res.data)
      })
      .catch(err => {
        console.log(err)
      })
      
    });
    
    
 

    return {
      goNumberSearch,
      qwe,
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

table {
  /* position: relative; */
  border-top: none;
}

thead {
  /* display: flex; */
  /* margin-top: ; */
  border-bottom: 1px solid #333333;
}

.deleteBtn {
  background-color: #333333 !important;
  color: white !important;
}

.backBtn:hover {
background-color: #727171 !important;
}

.btnContainer {
  position: relative;
  bottom: 20px;
}


.form-control {
  position: relative;
  margin-left: 0 !important;
  width: 30% !important;
  height: 45px;
  border: none !important;
  background-color: white !important;
  top: 0 !important;
}

.searchBarTag{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.backBtn {
  height: 45px;
}

.numberSearchBtn {
  background-color: #333333;
  color: white !important;
  
}

.numberSearchBtn:hover {
  background-color: #727171;
}

.container {
    /* margin-top: 200px */
}

.btn:hover {
    color: #fff
}

.input-text:focus {
    box-shadow: 0px 0px 0px;
    border-color: #f8c146;
    outline: 0px
}

.form-control {
    border: 1px solid #f8c146
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
</style>