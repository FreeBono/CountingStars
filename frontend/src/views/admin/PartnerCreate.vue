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


          <!-- 이미지 부분 -->
          <div class ="row">
            <label for="fileName" class="join-profile-img-edit" >
              <input ref="image" type="file" id="fileName" accept="image/*" @change="onInputImage" style="opacity: 0">
              <div class="align-items:center;">
                <div v-if="brandInfo.previewImg" style="height:250px; width : 100%;">
                  <img :src="brandInfo.previewImg" alt="" class="aa" >
                </div>
                <div v-else style="height:250px; width : 100%;">
                  <img src="@/assets/uploadicon.jpg" alt="" style="">
                </div>
              </div>
            </label>
          </div>
          <!-- 이미지 등록 부분 끝-->

            <div class="form-tag" style="width: 100%;">
              <b-form-input class="input_tag" type="text" v-model="brandInfo.name" placeholder=" 브랜드명" maxlength="30"></b-form-input>
              <!-- <b-form-input class="input_tag my-3" type="text" v-model="brandInfo.imageUrl" placeholder=" 브랜드 이미지 URL"></b-form-input> -->
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
import FormData from 'form-data';

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
      imageUrl: null,
      previewImg: null,
    })

    const brandImg = ref(null)
    const brandImgFile = ref(null)


    // 이미지 업로드
    const onInputImage = (event) => {
      // 이미지 파일 brandImg에 담는 것
      if( event.target.files && event.target.files.length > 0 ) {
        console.log(event, '첨부 확인')
        brandImgFile.value = event.target.files[0];
        brandImg.value = URL.createObjectURL(brandImgFile.value);
        
        // 이미지 미리보기 부분
        brandInfo.value.previewImg = brandImg.value
        
        console.log(brandImg.value, 'brandImg.value 확인')
        console.log(brandImgFile.value, 'brandImgFile.value 확인')
        console.log(brandInfo.value.previewImg, 'brandInfo.value.previewImg 확인')
      }
      brandImg.value = event.target.files[0];
      console.log(brandImg.value, 'brandImg.value 확인')
    }

    // 등록일 슬라이싱
    const changeUpper = (datetime) => {
      const old = ''+datetime
      return old.substring(0, 10)
    }


    // 거래처관리 페이지로 가기
    function goPatnerMain() {
      router.push({name: 'PartnerManagement'})
    }

    // 거래처 등록
    const createBrand = () => {
      const metadata = {
        name: brandInfo.value.name,
        endDate : brandInfo.value.endDate,
        address : brandInfo.value.address,
      }

      const formData = new FormData();
      formData.append('metadata', new Blob([JSON.stringify(metadata)] , {type: "application/json"}));
      formData.append('image', brandImg.value);
      console.log(formData, 'formData 확인')  
        
      console.log(brandInfo.value, '브랜드 확인')
      api.post('/brand', formData)
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

      brandImg,
      brandImgFile,
      changeUpper,
      onInputImage,

    }
  },
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