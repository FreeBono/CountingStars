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
              <b-form-input class="input_tag" type="text" v-model="noticeInfo.title" placeholder=" 제목"></b-form-input>
            </div>
            <b-form-textarea 
              id="textarea-rows"
              rows="8" 
              v-model="noticeInfo.content" 
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
import '@/assets/style/notice/noticeSide.css'
import '@/assets/style/notice/noticeTable.css'
import '@/assets/style/notice/noticeForm.css'

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
      .then(() => {
        router.push({name: 'NoticeDetail'})
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
  width: 88%;
  height: 40px;
}


</style>