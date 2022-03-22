<template>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
    <sidebar/>
      <div class="header">
        <p class="head_title">거래처 등록</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <div>
              <b-form-input class="input_tag" type="text" v-model="brandInfo.name" placeholder=" 브랜드명" maxlength="30"></b-form-input>
            </div>
            <div>
              <b-form-input class="input_tag mt-2" type="text"  placeholder=" 이미지" maxlength="30"></b-form-input>
            </div>
            <div>
              <b-form-input class="input_tag my-2" type="text"  placeholder=" 계약만료일자(계약일?)" maxlength="30"></b-form-input>
            </div>
            <div>
              <b-form-input class="input_tag" type="text"  placeholder=" 지갑주소" maxlength="30"></b-form-input>
            </div>

          </div>
          <div class="createBtn_position2">
            <button type="button" class="btn createBtn" @click="createBrand" style="width: 70px;">등록</button>
            <button type="button" class="btn cancleBtn mx-2" @click="goPatnerMain" style="width: 70px">취소</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import '@/assets/style/notice/noticeSide.css'
import '@/assets/style/notice/noticeTable.css'

import Sidebar from '@/components/Sidebar.vue'

import api from "@/services/api.js"
import { useRouter } from 'vue-router'
import { ref } from 'vue';


export default {
  name: 'PartnerCreate',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()
    const brandInfo = ref({
      name: null,
    })

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
  margin-right: 50px;
  margin-top: 10px;
  top: 110px;
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
</style>