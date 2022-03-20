<template>
  <!-- <h1>공지사항 페이지 네브바 자리</h1> -->
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <sidebar/>
      <div class="header">
        <p class="head_title" >공지사항</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <!-- 테이블 -->
            <div class="notice_board">
              <div class="notice_board_head">
                <p class="mt-3">
                  Total
                  <em>몇 개</em>
                </p>
              </div>
              <table>
                <colgroup>
                  <col width="56px">
                  <col width="102px">
                  <col width="*">
                  <col width="158px">
                </colgroup>
                <thead>
                  <tr>
                    <th>No.</th>
                    <th>유형</th>
                    <th>제목</th>
                    <th>작성일</th>
                  </tr>
                </thead>
                <tbody id="my-table" :items="noticeItems.value" :per-page="perPage" :current-page="currentPage" v-for="(noticeitem, idx) in noticeItems" :key="idx">
                  <tr>
                    <td>{{ noticeitem.noticeId }}</td>
                    <td>공지</td>
                    <td>{{ noticeitem.title }}</td>
                    <td>{{ makeDate(noticeitem.date) }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
              <!-- 테이블 끝 -->
          </div>
          <div class="createBtn_position">
            <button type="button" class="btn createBtn" @click="goNoticeForm" >글생성</button>
          </div>
        </div>
        <!-- 페이지네이션 -->
        <b-pagination
          @click="pageClick"
          v-model="currentPage"
          :total-rows="rowws"
          :per-page="perPage"
          aria-controls="my-table"
          align="center"
        ></b-pagination>
        <!-- 페이지네이션 끝 -->
        
        <!-- 블록 이미지 부분 테두리 따기 -->
          <div class="box_img">
            <img class="mx-5" src="@/assets/icon.png" alt="블록 상자" style="width: 300px;">
            <img class="mx-5" src="@/assets/icon.png" alt="블록 상자2" style="width: 300px;">
          </div>
        <!-- 블록 이미지 끝 -->
      </div>
      <!-- <button @click="getNotice">이거</button> -->
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import '@/assets/style/notice/noticeSide.css'
import '@/assets/style/notice/noticeTable.css'

import api from "@/services/api.js"
import { onMounted, ref } from 'vue';
import Sidebar from '@/components/Sidebar.vue'
import { useRouter } from 'vue-router'
import axios from 'axios';

export default {
  name: 'MainNotice',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()
    const myapi = ref(api)
    const noticeItems = ref(null)
    const currentPage = ref(null)
    const rowws = ref(null)
    const perPage = ref(null)

    // 공지사항 하나씩 불러오기
    myapi.value.get('/notice')
    .then((res) => {
      console.log(res)
      noticeItems.value = res.data.content
      console.log(noticeItems.value, 'List')

      currentPage.value = res.data.pageable['pageNumber']
      console.log(currentPage.value, 'currentPage 확인')

      rowws.value = res.data.totalElements
      console.log(rowws.value, 'rowws 전체 개수')

      perPage.value = res.data.pageable['pageSize']
      console.log(perPage.value, 'perP 확인')

    })

    const pageClick = () => {
      console.log(currentPage.value, 'currentPage 바뀌는지 확인')
      axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/notice?page=${currentPage.value}&size=10`
      })
      .then((res) => {
        console.log(res,'ffff페이지확인')
        noticeItems.value = res.data.content
      })
    }

    // 공지사항 글 생성 폼으로 보내기
    function goNoticeForm() {
      router.push({name: 'NoticeForm'})
    }

    // 등록일 슬라이싱
    const makeDate = (datetime) => {
      const old = ''+datetime
      return old.substring(0, 10)
    }

    onMounted(() => {
      // paginate()
      // pageClick()
    })

    return {
      goNoticeForm,
      noticeItems,
      currentPage,
      perPage,
      rowws,
      makeDate,
      pageClick,
      api,
      myapi,
      // nextPage,
    }
  },
  
  methods: {
    },
  computed: {
    rows() {
      return this.rowws
    }
  },
  watch : {
    // noticeItems : {
    //   handler() {
        
    //     console.log('작동하나')
    //   },
    //   deep : true
    // },
    // currentPage(neww) { 
    //   this.currentPage(10, neww - 1)
    //   console.log('여기도')
    // }
  }
}
</script>

<style scoped>

</style>