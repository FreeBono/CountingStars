<template>
  <h1>공지사항 상세보기</h1>
    <div class="wrapper">
      <!-- 사이드바 부분 -->
    <div data="vue" class="sidenav navbar navbar-vertical fixed-left navbar-expand-xs navbar-light bg-white ps">
      <div class="scrollbar-inner">
      <div class="navbar-inner">
        <ul class="navbar-nav">
          <li to="/" class="nav-item">
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
          </li>

          <!-- ADMIN일 경우만 보이게 -->
          <li to="/admin" class="nav-item">
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
    </div>
    <!-- 새로운 사이드 끝 -->

    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
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

        <!-- 블록 이미지 부분 테두리 따기 -->
          <div class="box_img">
            <img class="mx-5" src="@/assets/icon.png" alt="블록 상자" style="width: 300px;">
            <img class="mx-5" src="@/assets/icon.png" alt="블록 상자2" style="width: 300px;">
          </div>
        <!-- 블록 이미지 끝 -->

      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
    </div>

</template>

<script>
// import { useRouter } from 'vue-router'

export default {
  name : 'NoticeDetail',
  setup() {
    // const router = useRouter()
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
  background-color: #333333!important;
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


/* 디테일 테이블부분 */
.detail_area {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  word-wrap: break-word;
  word-break: keep-all;
}

.detail_info {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  word-wrap: break-word;
  word-break: keep-all;
}

.detail_content {
  font-size: 16px;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  word-wrap: break-word;
  word-break: keep-all;
  margin-bottom: 20px !important;
  border-bottom: 1px solid #e2e2e2;
}

.detail_table {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  border-collapse: collapse;
  border-spacing: 0;
  margin-bottom: 10px !important;
  margin-top: 10px !important;
  width: 90%;
  border-top: 1px solid black;
  border-bottom: 1px solid #E2E2E2;
  padding-top: 15px !important;
  padding-bottom: 15px !important;
  position: relative;
  margin: auto;
}

colgroup {
  font-size: 16px;
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  color: #000;
}

.tbody {
  font-size: 16px;
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
}

tr {
  font-size: 16px;
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
}

th {
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  margin: 0;
  border: 0;
  text-align: left;
  vertical-align: top;
  line-height: 40px;
  font-size: 0.875rem;
  padding: 0;
  padding-left: 15px;
}

td {
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  margin: 0;
  border: 0;
  padding: 0px !important;
  line-height: 26px;
  letter-spacing: -0.05em;
  font-weight: 400;
  text-align: left;
  vertical-align: middle;
  font-size: 0.875rem;
  min-height: 40px;
}

em {
  border-collapse: collapse;
  border-spacing: 0;
  line-height: 26px;
  text-align: left;
  font-size: 0.875rem;
  letter-spacing: -0.06em;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
  font-style: normal;
}

span {
  border-collapse: collapse;
  border-spacing: 0;
  line-height: 26px;
  font-weight: 400;
  text-align: left;
  font-size: 0.875rem;
  letter-spacing: -0.06em;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  vertical-align: baseline;
}

.notice_board_head {
  display: flex;
  margin-left: 45px;
}

/* 사이드바 부분 */
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
  background-color: #3adacf
;
}

.header {
  display: flex;
  position: relative;
  background-color: #3adacf
;
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
  background-color: #3adacf
 !important;
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