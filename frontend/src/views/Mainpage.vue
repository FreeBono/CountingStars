<template>

  <div>
    <!-- Navbar -->
    <Navbar />
    
    <!-- 최상위 gif -->
    <img src="@/assets/MAINGIF.gif" alt="" style="width:100%; height: 100vh;">

    <!-- ABOUT -->
    <div class="container-fluid" style="margin-top:30px;" >
      <div class="row">
        <div class="col">
          <p class="mt-3 mx-1" id="small-title" style="margin-bottom: 80px;" align="right">ABOUT</p>
          <div class="about">
            <p id="title1">블록체인 기반의</p>
            <p id="title2" style="margin">디지털 명품 인증 솔루션</p>
            <p style="margin-top: 35px;">
              대체 불가능한 토큰(NFT)을 통해 명품 업계가 공통적으로 갖고 있는 이슈를 해결함으로써 전반적인 고객 경험을 향상시킵니다
              위 변조가 불가능한 블록체인 기술을 활용하여 모든 고객에게 투명성을 제공하고 신뢰성을 확보합니다
            </p>
            <p style="margin-top:35px;">
              Counting Stars는 명품사의 제품 구매자들을 위한 디지털 보증 서비스부터 중고시장 판매 시 제품 이력 및 진품 증명 서비스를 제공합니다
              발급된 NFT 조회부터, 소유권 이전 등 모든 내역을 확인하고 사용할 수 있습니다
            </p>
            <p style="margin-top:35px;">
              모든 명품 파트너사 혹은 명품 구매자들은 규모에 상관없이 누구나 손쉽게 블록체인을 통한 명품 보증 서비스를 사용할 수 있습니다 
              <span>발급된 디지털 인증서를 자신의 지갑를 통해 확인할 수 있으며 모든 기록을 한 눈에 확인할 수 있습니다</span>
            </p>
            <p style="margin-top:35px;"></p>
          </div>
        </div>
        <div class="col" style="padding:0px; height: 100vh;">
          <img src="@/assets/GIF2.gif" alt="" style="width:100%;height: 100vh; ">
        </div>
      </div>
    </div>
    <!-- <LoginModal/> -->
    <!-- Button trigger modal -->
    <!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
      Launch demo modal
    </button> -->

    <!-- Modal -->
    
    <!-- EXPERIENCE -->
    <p id="small-title" style="margin-top:80px; margin-right: 70px; margin-bottom : 100px;" align="right">EXPERIENCE</p>
    <p id="common-title" style="margin-bottom: 50px;">명품 시장에 기여할 수 있는 긍정적인 효과</p>
    
    <div class="container">
      <div class="row" style="justify-content: center;">
        <div class="col-5 mr-2" align="justify">
          Counting Stars의 기획 배경은 파트너사들과의 협력이 명품시장의 발전으로 이어져 나갈 수 있다는 비전에서 탄생했습니다. 명품을 구매하는 고객의 구매 경험을 개선하기 위해 신뢰할 수 있고 지속 가능한 새로운 가치를 만들어 낼 수 있는 서비스를 제공합니다
        </div>
        <div class="col-5" align="justify">
          Counting Stars는 명품 브랜드들의 탄생 이례로 끊임없이 싸워왔던 진품 보증이라는 분야의 새로운 기술 허브로 발돋움하기 위한 서비스입니다. 우리는 블록체인의 잠재력을 통해 그동안 힘들다고만 생각해왔던 가품구매의 위험에서 소비자들을 보호하며 새로운 시대를 맞이할 것입니다.
        </div>
      </div>
      
      <hr id="hr-tag1" >
      <div class="row" style="margin-top: 130px;">
        <div class="col-4" align="left">
          <p style="text-align:center; line-height:200px; font-size:40px;">Partners</p>
        </div>
        <div class="col-8" >
          <div class="row-vh d-flex flex-row justify-content-between">
            <div CLASS="absolute-img"><img src="@/assets/Cartier.png" alt="" ></div>
            <div CLASS="absolute-img"><img src="@/assets/gucci.jpg" alt="" ></div>
            <div CLASS="absolute-img"><img src="@/assets/ddd.jpg" alt=""></div>
          </div>
        
          <div class="row-vh d-flex flex-row justify-content-between">
            <div CLASS="absolute-img"><img src="@/assets/Louis_Vuitton.png" alt=""></div>
            <div CLASS="absolute-img"><img src="@/assets/Prada.png" alt=""></div>
            <div CLASS="absolute-img"><img src="@/assets/Fendi-logo.png" alt=""></div>
          </div>
        </div>
      </div>
      <hr id="hr-tag2" >
    </div>

    <!-- BENEFITS -->
    <div>
      <p id="small-title" style="margin-top: 170px; margin-right: 70px; margin-bottom: 50px;" align="right">BENEFITS</p>
    </div>
    <div id="common-title" style="margin: 60px 0;">
      위 변조 불가능한 NFT 명품 보증시스템
    </div>

    <div class="container-fluid" style="margin-top:30px; height: 721px;">
      <div class="row">
        <div class="col" style="padding:0px; height: 100vh;">
          <img src="@/assets/gif3.gif" alt="" style="width:100%;height: 45rem; ">
        </div>
        <div class="col">
          <div class="about">
            <div style="margin-top:50px;">
              <h2>구매자를 위한 혜택</h2>
              <p style="margin-top:35px;">1. 상품의 진위 및 소유권 증명</p>
              <p >2. 제품 유통 과정, 소유주 변경 정보 엑세스</p>
              <p >3. 정보 투명성 향상을 통한 고객 만족도 강화</p>
            </div>
            <hr>
            <div style="margin-top:50px;">
              <h2>파트너를 위한 혜택</h2>
              <p style="margin-top:35px;">1. 위조로부터의 안전성 향상, 중고 시장 영향력 향상 </p>
              <p >2. 중개자 없이 구매자와의 신뢰관계 구축</p>
              <p >3. 블록체인을 통해 투명한 정보를 구매자에게 제공(?)</p>
            </div>
          </div>
        </div>
        
      </div>
    </div>



    <!-- governance -->
    <div class="governance" style="margin-top:20px;">
      <p style="padding-top:40px; margin-right:40px; margin-bottom : 50px;" align="right">GOVERNANCE</p>
      <div class="container-fluid" style="margin-top:30px;">
        <div class="row">
          
          <div class="col">
            <div class="about">
              <div style="margin-top:150px;">
                <h2 style="margin-top:50px;">명품인증을 위한 새로운 플랫폼</h2>
                <p style="margin-top:50px;">이 프로젝트는 지갑을 소유하고 있는 모든 참여자들에게 NFT를 소유, 이전할 수 있는 기능을 제공한다. </p>
                <p style="margin-top:50px;">Counting Stars는 브랜드의 크고 작음과 관계 없이 같은 견해를 공유하는 모든 브랜드들을 환영한다. 우리는 NFT를 통한 명품인증의 편의성을 제공할 뿐만 아니라 NFT를 통해 더 많은 추가기능을 만들어낼 수 있도록 다양한 솔루션들을 제공한다.</p>
        
              </div>
            </div>
          </div>
          <div class="col">
            <img src="@/assets/flow.png" alt="" style="width:85%; height:85%; ">
          </div>
        </div>
      </div>
    </div>

    <!-- carousel -->
    <!-- <div class="row-vh d-flex flex-row" style="width:650px; height: 175px; background-color:#f4f4f4;">
      <div style="height:175px; width:15%; line-height:175px;" >
        <img src="@/assets/icon.png" alt="" style="height:50%; width:70%;">
      </div>
        <div style="width:70%; padding-top:30px;" align="left">
          <div>
          Production
          </div>
          <div>
          wefiowjifoewjiojiojwoifjo
          </div>
        </div>
      <div style="height:175px; width:15%; background-color:white; line-height:175px;">
        <img src="@/assets/gori.png" alt="" style="height:30%; width:70%; margin-left:50px;" >
      </div>
    </div> -->
    <h1 style="margin-top: 120px; margin-bottom: 80px;">
      전체 가치체인을 통해 고객의 신뢰와 경험을 향상시킨다.
    </h1>
  
    <Carousel :items-to-show="2.5" :wrap-around="true">
    <Slide v-for="slide in 10" :key="slide">
      <div class="carousel__item">
        <div class="row-vh d-flex flex-row" style="width:650px; height: 175px; background-color:#f4f4f4;">
      <div style="height:175px; width:15%; line-height:175px;" >
        <img src="@/assets/icon.png" alt="" style="height:50%; width:70%;">
      </div>
        <div style="width:70%; padding-top:30px;" align="left">
          <div>
          Production
          </div>
          <div>
          wefiowjifoewjiojiojwoifjo
          </div>
        </div>
      <div style="height:175px; width:15%; background-color:white; line-height:175px;">
        <img src="@/assets/gori.png" alt="" style="height:30%; width:70%; margin-left:15px;" >
      </div>
    </div>

      </div>
    </Slide>

    <template #addons>
      <Navigation />
    </template>
  </Carousel>


    <!-- 문의사항 -->
    <h1 style="margin-top: 150px;">Want to know more?</h1>
    <p style="margin-top : 50px;">if you want to know about technological solution or have a questioin, contact us.</p>
    <button style="cursor: pointer; background-color: #ffd700; border:none; margin:20px; margin-top: 50px; width:251px; height:35px; color: #303030; border-radius:5px;">FOR GENERAL INQUIRIES</button><br>
    <button style="cursor: pointer; background-color: #ffd700; border:none; margin:20px; width:221px; height:35px; color : #303030; border-radius:5px;">FOR PRESS INQUIRIES</button>
    


    <!-- footer -->
    <hr style="height:2px; margin-top:180px;">
    <!-- <center style="margin-top:40px;">
      <div style="height :60px; width : 200px">
        <img src="@/assets/cslogo.png" alt="" style="height:100%; width:60px;">
        <span style="margin-left:10px;">Counting Stars</span>
      </div>
      <div style="margin-top:20px;">2022 Counting Stars. All rights reserved | SSAFY</div>
    </center> -->


  </div>
</template>

<script>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import Navbar from "../components/Navbar.vue"
import LoginModal from "../components/accounts/LoginModal.vue"
import testt from '@/services/testt'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
export default {
  name : "Mainpage",
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
    Navbar,
    LoginModal,
  },
  setup() {
  
  
    
    return {
      
    }
  }
}
</script>

<style scoped>
* {
  /* font-family: 'MaruBuri-Regular'; */
  /* font-family: 'Galmuri9'; */
  /* font-family: 'SUIT-Regular'; */
  /* font-family: 'MinSans-Regular'; */
  /* font-family: 'Chosunilbo_myungjo'; */
  /* font-family: 'ChosunKm'; */
  /* font-family: 'ChosunSm'; */
  /* font-family: "휴먼고딕", "human gothic"; */
  /* font-family: 'NanumMyeongjo'; */
  /* font-family: 'NanumBarunGothicLight'; */
  font-family: 'NanumBarunGothicYetHangul';
  /* font-family: 'Spoqa Han Sans Neo', 'Malgun Gothic', sans-serif; */
  /* font-family: 'Hahmlet', serif; */
}

#common-title {
  font-size: 3rem; 
  font-weight: 600;
}

#title1 {
  display: flex;
  position: relative;
  top: 10px;
  font-size: 3rem; 
  font-weight: 600;
}

#title2 {
  display: flex;
  position: relative;
  bottom: 10px;
  font-size: 3rem; 
  font-weight: 600;
}

#small-title {
  position: relative;
  font-size: 0.9rem; 
  font-weight: 600;
}

#hr-tag1 {
  display: flex;
  justify-content: center;
  position: relative;
  margin: auto;
  width: 85%;
  top: 80px;
}
#hr-tag2 {
  display: flex;
  justify-content: center;
  position: relative;
  margin: auto;
  width: 85%;
  top: 40px;
}

.about {
  padding-left: 50px;
  padding-right: 40px;
  text-align:left;
  margin-left:40px;
}

.absolute-img {
  width : 150px;
  height : 100px;
  position : relative;
  margin : auto;
  overflow : hidden;
}

.absolute-img > img {
  width :100%;
  height : 100%;
}

.governance {
  background-color: #333333;
  height : 100vh;
  color:white;
}

.modal-body {
  border-radius: 4rem;
  background: linear-gradient(135deg, #00c3ff, #eeef1c);
  background: linear-gradient(135deg, #fc00ff, #00dbde);
  background-image: linear-gradient(135deg, #ff00ba 0%, #fae713 100%);
  background-image: linear-gradient(150deg, #5a00ff 0%, #ff1ff7 100%, #ff1ff7 100%);
}

.modal-content{
  border-radius: 4rem;
}
</style>