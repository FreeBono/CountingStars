<template>
  <!-- <h2>nft 발급 페이지</h2> -->
  <div class="wrapper">
    <div class="wrapper">
      <!-- 사이드바 부분 -->
    <div data="vue" class="sidenav navbar navbar-vertical fixed-left navbar-expand-xs navbar-light bg-white ps">
      <div class="scrollbar-inner">
      <div class="navbar-inner">
        <ul class="navbar-nav">
          <li to="/" class="nav-item">
            <a href="/myNft"  class="sidebar-menu-item nav-link" style="margin-top: 130px">
              <span class="nav-link-text">MY NFT<b class="caret"></b></span>
            </a>
            <div>
              <ul class="nav nav-sm flex-column">
                <a href="/nftSearch" class="nav-link">
                  <span class="nav-link-text">NFT 조회</span>
                </a>
                <a href="/nftTransfer" class="nav-link">
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
          <li to="/partnerManagement" class="nav-item">
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
        <p class="head_title">NFT 발급</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <div class="nft_img">
              <img src="icon.png" alt="nft이미지" style="width: 250px;">
            </div>
            <div>
              <p>nft 내용 불러오기</p>
            </div>
          </div>

          <!-- 관리자만 버튼 보이게 -->
          <div class="createBtn_position2">
            <button type="button" class="btn createBtn mx-2" @click="updateNotice" style="width: 60px">발급</button>
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
  </div>
</template>

<script>
import "@/assets/style/notice/noticeSide.css"

export default {
  name: 'NftCreate',
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

.nft_img {
  display: flex;
  justify-content: flex-start;
  /* align-items: center; */
  top: 150px;
  position: relative;
  left: 100px;
}
</style>