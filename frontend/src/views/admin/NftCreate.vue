<template>
  <!-- <h2>nft 발급 페이지</h2> -->
  <sidebar/>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <p class="head_title">NFT 발급</p>
      </div>
      <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <div class="nft_img">
              <img src="@/assets/icon.png" alt="nft이미지" style="width: 250px;">
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
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import "@/assets/style/notice/noticeSide.css"

export default {
  name: 'NftCreate',
  components: {
    Sidebar,
  },
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