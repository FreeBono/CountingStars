<template>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
    <sidebar/>
      <div class="header">
        <p class="head_title">공지사항 등록</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <div>
              <b-form-input class="input_tag" type="text" v-model="noticeInfo.title" placeholder=" 제목" maxlength="30"></b-form-input>
            </div>
            <b-form-textarea 
              id="textarea-rows"
              rows="8" 
              v-model="noticeInfo.content" 
              @keyup.enter="createNotice"
              class="form-control" >
            </b-form-textarea>
          </div>
          <div class="createBtn_position2">
            <button type="button" class="btn createBtn" @click="createNotice" style="width: 70px;">등록</button>
            <button type="button" class="btn cancleBtn mx-2" @click="goNoticeMain" style="width: 70px">취소</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
// import '@/assets/style/notice/noticeSide.css'
// import '@/assets/style/notice/noticeTable.css'
// import '@/assets/style/notice/noticeForm.css'

import Sidebar from '@/components/Sidebar.vue'

import api from "@/services/api.js"
import { useRouter } from 'vue-router'
import { ref } from 'vue';

export default {
  name: 'NoticeForm',
  components: {
    Sidebar,

  },
  setup() {
    const router = useRouter()
    const noticeInfo = ref({
      adminId: 1,
      content: '',
      title: '',
    })

    function goNoticeMain() {
      router.push({name: 'MainNotice'})
    }

    const createNotice = () => {
      console.log(noticeInfo.value)
      api.post('/notice', noticeInfo.value)
      .then((res) => {
        console.log(res)
        // 해당 번호의 디테일로 가기
        router.push({name: 'MainNotice'})
        // router.push({name: 'NoticeDetail'})
      })
    }

    return {
      goNoticeMain,
      noticeInfo,
      createNotice,
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

.content_outside_box {
  display: flex;
  justify-content: center;
}

.head_title {
  color: white;
  display: flex;
  margin-left: 300px;
  margin-top: 30px;
}

</style>