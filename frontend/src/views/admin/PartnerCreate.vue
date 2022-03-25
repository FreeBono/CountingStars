<template>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
    <sidebar/>
      <div class="header">
        <p class="head_title">거래처 등록</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box ">
          <div class="container">
          <!-- <div style="width:40%; align-items:center; margin-top:8rem;">
            <FileUpload v-model="brandInfo.imageUrl" @file-upload="imageData"/>
          </div> -->
            <div class="form-tag" style="width: 100%;">
              <b-form-input class="input_tag" type="text" v-model="brandInfo.name" placeholder=" 브랜드명" maxlength="30"></b-form-input>
              <b-form-input class="input_tag my-3" type="text" v-model="brandInfo.imageUrl" placeholder=" 브랜드 이미지 URL"></b-form-input>
              <b-form-input class="input_tag my-3" type="text" v-model="brandInfo.endDate" placeholder=" 계약 만료 일자" maxlength="30"></b-form-input>
              <b-form-input class="input_tag" type="text" v-model="brandInfo.address" placeholder=" 지갑주소" maxlength="30"></b-form-input>
            </div>
          </div>
            <!-- 버튼 부분 -->
          <!-- <div class="container2"> -->
            <div class="createBtn_position2" style="justify-content: flex-end;">
              <button type="button" class="btn createBtn" @click="createBrand" style="width: 70px;">등록</button>
              <button type="button" class="btn cancleBtn" @click="goPatnerMain" style="width: 70px">취소</button>
            </div>
          <!-- </div> -->
            <!-- 버튼 끝 -->
        </div>
        <!-- 내용 들어갈 곳 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import FileUpload from "@/components/common/FileUpload.vue"

import api from "@/services/api.js"
import { useRouter } from 'vue-router'
import { ref } from 'vue';


export default {
  name: 'PartnerCreate',
  components: {
    Sidebar,
    FileUpload,
  },
  setup() {
    const router = useRouter()
    const brandInfo = ref({
      name: null,
      endDate : null,
      address : null,
      imageUrl : null,
    })

    // const brandImg = ref(null)
    // const brandImgFile = ref(null)

    // 이미지 등록
    // const imageData = (event) => {
    
    //   brandImg.value = event.nftImg
    //   brandImgFile.value = event.nftImgFile
    // }

    // json으로 변환
    // const transferJSON = async function (url) {
    //   const data = {
    //     image: url,
    //     brandName: brandInfo.value.name
    //   }

      // const response = await pinata(state.value.nftImgFile);
      
      // data.image = "https://gateway.pinata.cloud/ipfs/" + response.data.IpfsHash; // ipfs:// + response.data.IpfsHash를 넣어야 하나? 다른 NFT는 다 이렇게 넣던데

      // const jsonResponse = await pinataJson(data);

      // console.log(jsonResponse.data.IpfsHash); // json ipfs 주소
      // publishToken(jsonResponse.data.IpfsHash)
    // }

    // 거래처관리 페이지로 가기
    function goPatnerMain() {
      router.push({name: 'PartnerManagement'})
    }

    // 거래처 등록
    const createBrand = () => {
      console.log(brandInfo.value, '브랜드 확인')
      api.post('/brand', brandInfo.value)
      .then((res) => {
        console.log(res)
        // 거래처 메인으로 가기
        router.push({name: 'PartnerManagement'})
      })
    }
      
    return {
      goPatnerMain,
      brandInfo,
      createBrand,
      // imageData,
      // brandImg,
      // brandImgFile,
      // transferJSON,
    }
  }
}
</script>

<style scoped>
.input_tag {
  position: relative;
  top: 50px;
  width: 90%;
  height: 40px;
  background-color: #e6f5f4 !important;
  border: none !important;
  left: 5%;
}

/* noticeform css */
#textarea-rows {
  position: relative;
  margin: auto;
  width: 90% !important;
  height: 300px;
  border: none !important;
  background-color: #e6f5f4 !important;
  top: 100px;
}

.createBtn_position2 {
  position: relative;
  display: flex;
  justify-content: flex-end;
  top: 2.5rem;
  right: 2.5rem;
}

.cancleBtn {
  background-color: #FC5E5E !important;
}

.cancleBtn:hover {
background-color: #fa8e8e !important;
}

/* 페이지 이름 */
.head_title {
  color: white;
  display: flex;
  margin-left: 300px;
  margin-top: 30px;
}

.container {
  display: flex;
  align-content: center;
  justify-content: center;
  /* flex-wrap: wrap-reverse; */
  position: relative;
  height: 80%;
  /* margin: auto; */
}

.form-tag {
  position: relative;
  /* margin: auto; */
}

.content_box {
  /* display: flex; */
  /* position: relative; */
  /* justify-content: center; */
}

.content_outside_box {
  display: flex;
  justify-content: center;
}
</style>