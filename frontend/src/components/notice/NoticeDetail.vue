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
                  <em>몇 번째 글</em>
                </p>
              </div>
              <table class="detail_table mt-1 mb-1">
                <colgroup>
                  <col width="15%">
                  <col width="85%">
                </colgroup>
                <tbody>
                  <tr >
                    <th style="text-align: left;">Title</th>
                    <td class="pd-0">
                      <em>[공지]</em>
                      <span>날짜(등록일)</span>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
              <!-- 테이블 끝 -->
          </div>

          <!-- 관리자만 버튼 보이게 -->
          <div class="createBtn_position2">
            <button type="button" class="btn createBtn mx-2" @click="updateNotice" style="width: 60px">수정</button>
            <button type="button" class="btn deleteBtn" @click="deleteNotice" style="width: 60px">삭제</button>
            <button type="button" class="btn backBtn mx-2" @click="goNoticeMain" style="width: 60px">목록</button>
          </div>
        </div>

      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>

</template>

<script>
import '@/assets/style/notice/noticeTable.css'
import '@/assets/style/notice/noticeSide.css'

import Sidebar from '@/components/Sidebar.vue'
import api from "@/services/api.js"
import { ref } from 'vue';

// import { useRouter } from 'vue-router'

export default {
  name : 'NoticeDetail',
  components: {
    Sidebar,
  },
  setup() {
    const noticeNo = ref({
      notice_id: null,
    })
    // const router = useRouter()
    const getNoticeItem = () => {
      console.log(noticeNo.value)
      api.get(`notice/${notice_id}`)
      .then((res) => {
        console.log(res)
      })
    }

    return {
      getNoticeItem
    }
  },
}
</script>

<style scoped>

</style>