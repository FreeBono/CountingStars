<template>
  <div class="wrapper">
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
                <a href="/nftwalletsearch" class="nav-link">
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
          <!-- ADMIN일 경우만 보이게 -->

        </ul>
        </div>
      </div>
    </div>
    <!-- 새로운 사이드 끝 -->

    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <p class="head_title">My NFT</p>
      </div>

      <!-- 카드 부분 -->
      <!-- for문 돌려서 카드 6개 띄우고 누르면 상세보기로 -->
      <!-- 페이지네이션 추가 -->
      <div>
        <div class="content_outside_box">
          <div class="mt--6 container">
            <div class="row">
              <div class="col col-lg-4">
                <!-- 카드 -->
                <div class="card" @click="goMyNftDeatil" style="background-color: #9950F4; width: 20rem;">
                  <img class="card=img-top" src="icon.png" alt="공지사항">
                  <div class="card-body">
                    <h4 class="card-title h2 bm-0">NFT</h4>
                  </div>
                </div>
              </div>
              <div class="col col-lg-4">
                <div class="card" @click="goPartenrManagement" style="background-color: #526EFF; width: 20rem;">
                  <img class="card=img-top" src="icon.png" alt="거래처">
                  <div class="card-body">
                    <h4 class="card-title h2 bm-0">NFT</h4>
                  </div>
                </div>
              </div>
              <div class="col col-lg-4">
                <div class="card" @click="goNftCreate" style="background-color: #FC5E5E; width: 20rem;">
                  <img class="card=img-top" src="icon.png" alt="nft발급">
                  <div class="card-body">
                    <h4 class="card-title h2 bm-0">NFT</h4>
                  </div>
                </div>
              </div>

              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 카드 끝-->

        <!-- 블록 이미지 부분 테두리 따기 -->
          <div class="box_img">
            <img class="mx-5" src="icon.png" alt="블록 상자" style="width: 300px;">
            <img class="mx-5" src="icon.png" alt="블록 상자2" style="width: 300px;">
          </div>
        <!-- 블록 이미지 끝 -->
    <!-- 내용 들어갈 곳 끝 -->
    </div>
  </div>
</template>

<script>
import "@/assets/style/notice/noticeSide.css"
import { useRouter } from 'vue-router'

export default {
  name: 'MyNft',
  setup() {
    const router = useRouter()

    function goMyNftDeatil() {
      router.push({name: 'MyNftDetail'})
    }
    return {
      goMyNftDeatil,
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

.row {
  font-size: 1rem;
  line-height: 1.5;
  color: #525f7f;
  text-align: left;
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}

.col {
  display: flex;
  justify-content: center;
  padding-left: 50px;
  padding-right: 50px;
}

.card {
  font-size: 1rem;
  line-height: 1.5;
  color: white;
  text-align: center;
  box-sizing: border-box;
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  background-clip: border-box;
  border-radius: .375rem;
  margin-bottom: 30px;
  box-shadow: 3px 3px 10px 1px #adabab;
  border: none;
}

/* 카드 누르면 작아지게? 테두리 뜨게? 아무튼 효과 주기 */
.card:hover {
  cursor: pointer;
}

.content_outside_box {
  bottom: 100px;
}

.container {
  /* position: relative; */
  display: flex;
  justify-content: center;
  /* flex-wrap: wrap; */
}
</style>