<template>
  <!-- <h1>공지사항 페이지 네브바 자리</h1> -->
  <sidebar/>
  <div class="wrapper">
    <!-- 사이드 시작 -->
    <!-- <div data="vue" class="sidenav navbar navbar-vertical fixed-left navbar-expand-xs navbar-light bg-white ps">
      <div class="scrollbar-inner">
      <div class="navbar-inner">
        <ul class="navbar-nav">
          <li to="/mynft" class="nav-item">
            <a href="/mynft"  class="sidebar-menu-item nav-link" style="margin-top: 130px">
              <span class="nav-link-text">MY NFT<b class="caret"></b></span>
            </a>
            <div>
              <ul class="nav nav-sm flex-column">
                <a href="/nftwallettransfer" class="nav-link">
                  <span class="nav-link-text">NFT 조회</span>
                </a>
                <a href="/nfttransfer" class="nav-link">
                  <span class="nav-link-text">NFT 이전</span>
                </a>
              </ul>
            </div>
          </li>
          <li to="/notice" class="nav-item">
            <a href="/notice" class="sidebar-menu-item nav-link">
              <span class="nav-link-text">공지사항<b class="caret"></b></span>
            </a>
          </li> -->

          <!-- ADMIN일 경우만 보이게 -->
          <!-- <li to="/admin" class="nav-item">
            <a href="/admin" class="sidebar-menu-item nav-link">
              <span class="nav-link-text">ADMIN<b class="caret"></b></span>
            </a>
          </li>
          <li to="/partner" class="nav-item">
            <a href="/partner" class="sidebar-menu-item nav-link">
              <span class="nav-link-text">거래처 관리<b class="caret"></b></span>
            </a>
          </li>
          <li to="/nftcreate" class="nav-item">
            <a href="/nftcreate" class="sidebar-menu-item nav-link">
              <span class="nav-link-text">NFT 발급<b class="caret"></b></span>
            </a>
          </li>
        </ul>
        </div>
      </div>
    </div> -->
    <!-- 새로운 사이드 끝 -->
    
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
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
                <tbody>
                  <tr>
                    <td>번호</td>
                    <td>유형</td>
                    <td>제목 : for문 돌려서 채우기</td>
                    <td>작성일</td>
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
        <nav aria-label="Page navigation">
          <div class="pagination_outside">
            <ul class="pagination">
              <li class="page-item"><a class="page-link" href="#">Previous</a></li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
          </div>
        </nav>
        <!-- 페이지네이션 끝 -->
        
        <!-- 블록 이미지 부분 테두리 따기 -->
          <div class="box_img">
            <img class="mx-5" src="icon.png" alt="블록 상자" style="width: 300px;">
            <img class="mx-5" src="icon.png" alt="블록 상자2" style="width: 300px;">
          </div>
        <!-- 블록 이미지 끝 -->
      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
// import Sidebar2 from '@/components/sidebar2.vue'
import { useRouter } from 'vue-router'
import Sidebar from '@/components/Sidebar.vue'

export default {
  name: 'MainNotice',
  components: {
    // Sidebar2
    Sidebar,
  },
  setup() {
    const router = useRouter()

    // 공지사항 글 생성
    function goNoticeForm() {
      router.push({name: 'NoticeForm'})
    }
    return {
      goNoticeForm
    }
  },
  methods: {
    
  }
  
}
</script>

<style scoped>

.sidenav {
  text-align: left;
  box-sizing: border-box;
  padding: 1rem 1rem;
  flex-wrap: wrap;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding-top: 0;
  border-style: solid;
  z-index: 1000;
  transition: all .15s ease;
  background-color: #ffffff !important;
  display: block;
  position: fixed;
  top: 0;
  bottom: 0;
  width: 100%;
  padding-left: 0;
  padding-right: 0;
  max-width: 250px!important;
  transform: translateX(0);
  left: 0;
  border-width: 0 1px 0 0;
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
  background-color: #ffd700;
}

.header {
  display: flex;
  position: relative;
  background-color: #ffd700;
  height: 380px;
  left: 150px;
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
  bottom: 130px;
  left: 150px;
}

.content_box {
  width: 50%;
  height: 600px;
  background-color: white;
  margin: auto;
  border: 1px;
  border-radius: 10px;
  box-shadow: 3px 3px 10px 1px #d8d7d7;
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

.createBtn_position {
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
  margin-top: 10px;
}

.createBtn {
  background-color: #ffd700 !important;
}

.createBtn:hover {
background-color: #fae779 !important;
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


/* 테이블 부분 */
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

</style>