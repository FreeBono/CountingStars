<template>
  <h1>공지사항 상세보기</h1>
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
                    <th style="text-align: left;">Title</th>
                    <th class="pd-0">
                      <em>[공지]</em>
                      <span>{{noticeContents.title}}</span>
                    </th>
                  </tr>
                  <tr>
                  </tr>
                </tbody>
              </table>
              <p class="content-tag" style="white-space: wrap;">{{ noticeContents.content }}</p>
            </div>
              <!-- 테이블 끝 -->
          </div>

          <!-- 관리자만 버튼 보이게 -->
          <!-- <div v-if=""> -->
            <div class="createBtn_position2">
              <button type="button" class="btn createBtn " @click="updateNotice" style="width: 60px">수정</button>
              <button type="button" class="btn deleteBtn" @click="deleteNotice" style="width: 60px">삭제</button>
              <button type="button" class="btn backBtn " @click="goNoticeMain" style="width: 60px">목록</button>
            <!-- </div> -->
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
    // const noticeInfo = {
    //     noticeId: store.state.noticeNo,
    //     adminId: 1,
    //   }

    // 메인으로 보내기
    function goNoticeMain() {
      router.push({name: 'MainNotice'})
    }

    // 디테일 번호 가져와서 내용 불러오기
    myapi.value.get(`/notice/${noticeNo}`)
    .then((res) => {
      console.log(res.data)
      noticeContents.value.noticeId = res.data.noticeId
      noticeContents.value.content = res.data.content
      noticeContents.value.title = res.data.title
      noticeContents.value.date = res.data.date
      console.log(noticeContents.value.noticeId,'확인')
    })

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
      // console.log(deleteNotice(),' 작동 함?')
    }

    // 게시글 삭제
    // deleteBoardForm() {
    //   http({
    //     method: 'delete',
    //     url: `/board/remove/${this.board_no}`,
    //     headers: this.getToken()
    //   })
    //   .then(() => {
    //     this.$router.push({name: 'MainBoard'})
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
    // }

  onMounted({
    noticeNo,
  })

    return {
      noticeNo,
      goNoticeMain,
      noticeContents,
      deleteNotice,
      // noticeInfo,
    }
  },
}
</script>

<style scoped>
.content-tag {
  height: 200px;
  display: flex;
  justify-content: center;
  align-content: center;
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
  background-color: #3adacf;
}

.header {
  display: flex;
  position: relative;
  background-color: #3adacf;
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
  font-size: 36px;
  margin: auto;
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
  background-color: #3adacf
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