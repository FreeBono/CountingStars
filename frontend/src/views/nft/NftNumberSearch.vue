<template>
  <!-- <h2>거래처 관리 페이지</h2> -->
  <sidebar/>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <p class="head_title">NFT 조회(일련번호)</p>
      </div>
      <div class="content_outside_box">
        <div class="btnContainer">
          <button type="button" class="btn numberSearchBtn" @click="goWalletSearch">지갑으로 조회</button>
        </div>

        <!-- 지갑 검색 부분 -->
        
        <div class="searchBarTag mt-3">
          <!-- <input class="search_input" type="text"> -->
          <input class="form-control" type="text">
          <button type="button" class="btn backBtn">조회</button>
        </div>

        <div class="content_box mt-5">
          <div class="container">
            <div class="nft_img">
              <img src="icon.png" alt="nft이미지" style="width: 250px;">
            </div>
            <div>
              <p>nft 내용 불러오기</p>
            </div>
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
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"
import "@/assets/style/notice/noticeTable.css"
import { useRouter } from 'vue-router'

export default {
  name: 'NftNumberSearch',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()

    function goWalletSearch() {
      router.push({name: 'NftWalletSearch'})
    }

    return {
      goWalletSearch,
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
  width: 70px;
}

.numberSearchBtn {
  background-color: #333333;
  color: white !important;
  
}

.numberSearchBtn:hover {
  background-color: #727171;
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