<template>
  <!-- <h1>공지사항 상세보기</h1> -->
    <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <sidebar/>
      <div class="header">
        <p class="head_title">공지사항</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <!-- 테이블 -->
            <div class="notice_board">
              <div class="notice_board_head">
                <p class="mt-5">
                  No.
                  <em>{{noticeContents.noticeId}}</em>
                </p>
              </div>
              <table class="detail_table mt-1 mb-1">
                <colgroup>
                  <col width="15%">
                  <!-- <col width="85%"> -->
                </colgroup>
                <tbody>
                  <tr >
                    <th class="" style="text-align: center;">Title</th>
                    <th class="pd-0" style="text-align: left;">
                      <em v-if="editBtn === false">[공지]</em>
                      <b-form-input 
                        v-if="editBtn === true" 
                        type="text" 
                        maxlength="30" 
                        style="height: 35px; text-align: left; width:400px;"
                        v-model="noticeContents.title"
                        >
                      </b-form-input>
                      <span class="mx-1" v-else>{{noticeContents.title}}</span>
                    </th>
                  </tr>
                  <tr>
                  </tr>
                </tbody>
              </table>
              <b-form-textarea 
              v-if="editBtn === true"
              id="textarea-rows"
              rows="8" 
              v-model="noticeContents.content" 
              class="form-control"
              style="font-size: 1.1rem;"
              >
              </b-form-textarea>
              <p v-else class="content-tag mt-5 mx-3" style="white-space: wrap; text-align: left; font-size: 1.1rem;">{{ noticeContents.content }}</p>
            </div>
              <!-- 테이블 끝 -->
          </div>

          <!-- 관리자만 버튼 보이게 -->
          <!-- 수정 버튼 누르기 전 -->
          <div v-show="editBtn === false">
            <div class="createBtn_position2">
              <button type="button" class="btn createBtn " @click="editNotice" style="width: 60px">수정</button>
              <button type="button" class="btn deleteBtn" @click="deleteNotice" style="width: 60px">삭제</button>
              <button type="button" class="btn backBtn " @click="goNoticeMain" style="width: 60px">목록</button>
            </div>
          </div>
          <!-- 수정 버튼 누른 후 (저장) -->
          <div v-show="editBtn === true">
            <div class="createBtn_position2">
              <button v-if="editBtn === true" type="button" class="btn createBtn " @click="updateNotice" style="width: 60px">수정</button>
              <button type="button" class="btn deleteBtn" @click="editCancel" style="width: 60px">취소</button>
              <button type="button" class="btn backBtn " @click="goNoticeMain" style="width: 60px">목록</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>

</template>

<script>
// 테이블, 사이드 css

import Sidebar from '@/components/Sidebar.vue'
import api from "@/services/api.js"
import { onMounted, ref } from 'vue';
import router from '@/router'
import { useStore } from "vuex";
import axios from 'axios';

import { useRouter } from 'vue-router'

export default {
  name : 'NoticeDetail',
  components: {
    Sidebar,
  },
  setup() {
    const store = useStore();
    const router = useRouter()
    const myapi = ref(api)
    const noticeNo = store.state.noticeNo
    const noticeContents = ref({
      noticeId: null,
      title: null,
      content: null,
      date: null,
    })
    const editBtn = ref(false)

    
    // 메인으로 보내기
    function goNoticeMain() {
      router.push({name: 'MainNotice'})
    }

    // 디테일 번호 가져와서 내용 불러오기
    const getNotice = () => {
      myapi.value.get(`/notice/${noticeNo}`)
      .then((res) => {
        noticeContents.value.noticeId = res.data.noticeId
        noticeContents.value.content = res.data.content
        noticeContents.value.title = res.data.title
        noticeContents.value.date = res.data.date
        console.log(noticeContents.value.noticeId, '번호 계속 뜨는지 확인')
      })
    }

    // 공지 삭제
    const deleteNotice = () => {
      const noticeInfo = {
        noticeId: store.state.noticeNo,
        adminId: 1,
      }
      axios({
        method: 'delete',
        url: 'http://localhost:8080/api/v1/notice',
        data: {noticeId: noticeInfo.noticeId, adminId: noticeInfo.adminId}
      })
      .then(() => {
        console.log(noticeInfo.noticeId, '삭제 된 데이터 확인', noticeInfo.adminId)
        router.push({name: 'MainNotice'})
      })
    }

    // 수정 버튼 눌렀을 때 변경
    const editNotice = () => {
      editBtn.value = true
      console.log(editBtn.value, '수정 버튼 변화')
    }

    // 수정 취소
    const editCancel = () => {
      editBtn.value = false
      getNotice()
    }

    // 공지 수정
    const updateNotice = () => {
      const updateInfo = {
        adminId: 1,
        content: noticeContents.value.content,
        title: noticeContents.value.title,
        noticeId: store.state.noticeNo,
      }
      axios({
        method: 'put',
        url: 'http://localhost:8080/api/v1/notice',
        data: {
              noticeId: updateInfo.noticeId, 
              adminId: updateInfo.adminId, 
              content: updateInfo.content,
              title: updateInfo.title
              }
      })
      .then(() => {
        console.log(updateInfo.content, '수정 확인', updateInfo.title)
        editBtn.value = false
        console.log(editBtn.value, '수정 저장 버튼')
      })
    }

    onMounted(() => {
      getNotice()
    })

    return {
      noticeNo,
      goNoticeMain,
      noticeContents,
      deleteNotice,
      updateNotice,
      editBtn,
      editNotice,
      editCancel,
      getNotice,
    }
  },
}
</script>

<style scoped>
.content-tag {
  height: 200px;
  display: flex;
  justify-content: flex-start;
  /* align-content: center; */
  flex-wrap: wrap;
  border-bottom: 1px solid #d8d7d7;
}

/* 테이블 css */
table {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  border-spacing: 0;
  width: 100%;
  border-collapse: collapse;
  border-top: 1px solid #d8d7d7;
}

.notice_board {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  width: 90%;
  position: relative;
  margin: auto;
}

.notice_board_head {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  margin-bottom: 8px;
}

p {
  font-size: 0.875rem;
}

em {
  font-size: 0.875rem;
  margin-left: 3px;
  font-style: normal !important;
}

colgroup {
  border-spacing: 0;
  font-size: 16px;
  box-sizing: border-box;
}

col {
  width: 56px;
  font-size: 16px;
}

thead {
  font-size: 16px;
  box-sizing: border-box;
  vertical-align: baseline;
}

tr {
  font-size: 16px;
  box-sizing: border-box;
  vertical-align: baseline;
  margin: 0;
  padding: 0;
  border: 0;
}

th {
  border-spacing: 0;
  box-sizing: border-box;
  font-size: 0.875rem;
  height: 50px;
  vertical-align: middle;
  border-bottom: 1px solid #E2E2E2;
}

tbody {
  /* display: flex; */
  /* justify-content: space-evenly; */
  font-size: 16px;
  border-spacing: 0;
  box-sizing: border-box;
  vertical-align: baseline;
}

td {  
  margin: 0;
  padding: 0;
  border: 0;
  /* display: flex; */
  /* flex-wrap: wrap; */
  /* align-items: center; */
  /* justify-content: space-evenly; */
  height: 50px;
  box-sizing: border-box;
  border-spacing: 0;
  vertical-align: middle;
  text-align: center;
  font-size: 0.875rem;
  border-bottom: 1px solid #E2E2E2;
}

* {
  font-family: 'MinSans-Regular';
}

.nav-link {
  text-align: left;
  font-size: 17px;
  /* color: black; */
  /* margin-left: 5px; */
  margin-bottom: 15px;
}

.nav-link:hover {
  background-color: #2dce89;
}

.header {
  display: flex;
  position: relative;
  background-color: #2dce89;
  height: 380px;
  left: 7px;
  padding-bottom: 72px;
}

.main-content {
  position: relative;
  background-color: #f5f6fc;
}

.nav-link-text {
  /* color: white; */
  color: black;
  font-size: 24px;
  margin-left: 40px;
  font-family: 'MinSans-Regular';
}

.nav-link-text:hover {
  color: black;
  cursor: pointer;
}

.head_title {
  color: white;
  position:absolute; 
  margin-left:100px; 
  margin-top: 50px;
  font-size: 2rem;
}

.content_outside_box {
  position: relative;
  bottom: 200px;
  left: 40px;
}

.content_box {
  width: 50%;
  height: 600px;
  background-color: white;
  margin: auto;
  border: 1px;
  border-radius: 10px;
  box-shadow: 3px 3px 10px 1px #d8d7d7;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  align-items: center;
}

.pagination_outside {
  display: flex;
  position: relative;
  bottom: 130px;
  /* left: 150px; */
}

.pagination {
  margin: auto;
}

.createBtn_position2 {
  display: flex;
  justify-content: flex-end !important;
  margin-right: 20px;
  margin-top: 10px;
}

.createBtn {
  background-color: #2dce89
 !important;
}

.createBtn:hover {
background-color: #28b9b0d6 !important;
}

.deleteBtn {
  background-color: #FC5E5E !important;
}

.deleteBtn:hover {
background-color: #fa8e8e !important;
}

.backBtn {
  background-color: #333333 !important;
  color: white !important;
}

.backBtn:hover {
background-color: #727171 !important;
}

.box_img {
  display: flex;
  position: relative;
  justify-content: center;
  opacity: 0.5;
  bottom: 100px;
  z-index: -1;
  /* left: 150px; */
}


</style>