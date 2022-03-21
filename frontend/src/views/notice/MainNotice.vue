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
        <!-- <div class="content_box"> -->
          <div class="container mt-7">

            <!-- 템플릿 테이블 시작 -->

            <div class="col">
              <div class="card shadow">
                <div class="card-header border-0">
                  <h3 class="mb-0">Notice</h3>
                </div>
                <div class="table-responsive">
                  <table class="table align-items-center table-flush">
                    <thead class="thead-light">
                      <tr>
                        <th scope="col">No.</th>
                        <th scope="col">유형</th>
                        <th scope="col">제목</th>
                        <th scope="col">Users</th>
                        <th scope="col">작성일</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody id="my-table" :items="noticeItems.value" :per-page="perPage" :current-page="currentPage" v-for="(noticeitem, idx) in noticeItems" :key="idx">
                      <tr>

                        <th scope="row">
                          <div class="media align-items-center">
                            <a href="#" class="avatar rounded-circle mr-3">
                              <img alt="Image placeholder" src="@/assets/cslogo.png">
                            </a>
                            <div class="media-body">
                              <span class="mb-0 text-sm">{{ noticeitem.noticeId }}</span>
                            </div>
                          </div>
                        </th>

                        <td>
                          공지
                        </td>
                        <td>
                          <span class="badge badge-dot mr-4">
                            <i class="bg-info"></i>
                          </span>
                          <span class="mb-0 text-sm">{{ noticeitem.title }}</span>
                        </td>
                        <td>
                          <div class="avatar-group">
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Ryan Tompson">
                              <img alt="Image placeholder" src="@/assets/광.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Romina Hadid">
                              <img alt="Image placeholder" src="@/assets/동.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Alexander Smith">
                              <img alt="Image placeholder" src="@/assets/현.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Jessica Doe">
                              <img alt="Image placeholder" src="@/assets/호.jpg" class="rounded-circle">
                            </a>
                          </div>
                        </td>
                        <td>
                          <div class="d-flex align-items-center">
                            <span class="mr-2">{{ makeDate(noticeitem.date) }}</span>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <!-- 페이지 네이션 -->
                <div class="card-footer py-4">
                  <b-pagination
                    @click="pageClick"
                    v-model="currentPage"
                    :total-rows="rowws"
                    :per-page="perPage"
                    aria-controls="my-table"
                    align="center"
                  ></b-pagination>
                </div>
                <!-- 페이지네이션 끝 -->
              </div>
            </div>
          <!-- 템플릿 테이블 끝 -->
  
          </div>
          <div class="createBtn_position">
            <button type="button" class="btn createBtn" @click="goNoticeForm" >글생성</button>
          </div>
        </div>
        <!-- 페이지네이션 -->
        <!-- <b-pagination
          @click="pageClick"
          v-model="currentPage"
          :total-rows="rowws"
          :per-page="perPage"
          aria-controls="my-table"
          align="center"
        ></b-pagination> -->
        <!-- 페이지네이션 끝 -->
      <!-- </div> -->
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import '@/assets/style/notice/noticeSide.css'
// import '@/assets/style/notice/noticeTable.css'
import '@/assets/style/notice/table.css'

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