<template>
  <sidebar/>
  <!-- <h2>My NFT 상세보기</h2> -->
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <p class="head_title">MY NFT</p>
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
            <button type="button" class="btn createBtn" @click="sendNft" style="width: 100px">소유권이전</button>
            <button type="button" class="btn backBtn mx-2" @click="goMyNft" style="width: 60px">목록</button>
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
import { useRouter } from 'vue-router'

export default {
  name: 'MyNftDetail',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()

    function goMyNft() {
      router.push({name: 'MyNft'})
    }
    function sendNft() {
      router.push({name: 'NftTransfer'})
    }
    
    return {
      goMyNft,
      sendNft,
    }
  }
}
</script>

<style scoped>
.nft_img {
  display: flex;
  justify-content: flex-start;
  /* align-items: center; */
  top: 150px;
  position: relative;
  left: 100px;
}
</style>