<template>
  <!-- <h2>거래처 관리 페이지</h2> -->
  <div class="wrapper">
    <!-- 사이드 시작 -->
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
                <a href="/nftwalletsearch" class="nav-link">
                  <span class="nav-link-text">NFT 조회</span>
                </a>
                <a href="/nftwallettransfer" class="nav-link">
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
          <li to="/nftMake" class="nav-item">
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
        <p class="head_title">NFT 조회(지갑)</p>
      </div>
      <div class="content_outside_box">
        <div class="btnContainer">
          <button type="button" class="btn numberSearchBtn" @click="goNumberSearch">일련번호로 조회</button>
        </div>

        <!-- 지갑 검색 부분 -->
        
        <div class="searchBarTag mt-3">
          <!-- <input class="search_input" type="text"> -->
          <input class="form-control" type="text">
          <button type="button" class="btn backBtn">지갑 조회</button>
        </div>

        <div class="content_box mt-5">
          <div class="container">
            <!-- 테이블 -->
            <div class="notice_board">
              <div class="notice_board_head">
                <p class="mt-3"></p>
              </div>
              <table>
                <colgroup>
                  <col width="56px">
                  <col width="*">
                  <col width="158px">
                </colgroup>
                <thead>
                  <tr>
                    <th>No.</th>
                    <th>브랜드</th>
                    <th>NFT 발행일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>번호</td>
                    <td>제목 : for문 돌려서 채우기</td>
                    <td>날짜</td>
                  </tr>
                </tbody>
              </table>
            </div>
              <!-- 테이블 끝 -->
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
import "@/assets/style/notice/noticeSide.css"
import "@/assets/style/notice/noticeTable.css"
import { useRouter } from 'vue-router'

export default {
  name: 'NftWalletSearch',
  setup() {
    const router = useRouter()

    function goNumberSearch() {
      router.push({name: 'NftNumberSearch'})
    }

    return {
      goNumberSearch,
    }
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

table {
  /* position: relative; */
  border-top: none;
}

thead {
  /* display: flex; */
  /* margin-top: ; */
  border-bottom: 1px solid #333333;
}

.deleteBtn {
  background-color: #333333 !important;
  color: white !important;
}

.backBtn:hover {
background-color: #727171 !important;
}

.btnContainer {
  position: relative;
  bottom: 20px;
}


.form-control {
  position: relative;
  margin-left: 0 !important;
  width: 30% !important;
  height: 45px;
  border: none !important;
  background-color: white !important;
  top: 0 !important;
}

.searchBarTag{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.backBtn {
  height: 45px;
}

.numberSearchBtn {
  background-color: #333333;
  color: white !important;
  
}

.numberSearchBtn:hover {
  background-color: #727171;
}
</style>