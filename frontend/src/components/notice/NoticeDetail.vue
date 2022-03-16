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
import "@/assets/style/notice/noticeDetail.css"
import "@/assets/style/notice/noticeSide.css"
import "@/assets/style/notice/noticeTable.css"
// import "@/assets/style/notice/noticeTable.css"
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
</style>