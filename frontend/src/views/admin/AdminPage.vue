<template>
  <!-- <h2>관리자 페이지</h2> -->
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
    <sidebar/>
      <div class="header">
        <p class="head_title">ADMIN</p>
      </div>

      <!-- 카드 부분 -->
      <div>
        <div class="content_outside_box">
          <div class="mt--6 container">
            <div class="row">
                <div class="col col-lg-4">
                  <!-- 카드 -->
                  <div class="card" @click="goNoticeMain" style="background-color: #9950F4; width: 20rem;">
                    <img class="card=img-top" src="@/assets/icon.png" alt="공지사항">
                    <div class="card-body">
                      <h4 class="card-title h2 bm-0">공지사항</h4>
                    </div>
                  </div>
                </div>
                <div class="col col-lg-4">
                  <div class="card" @click="goPartenrManagement" style="background-color: #526EFF; width: 20rem;">
                    <img class="card=img-top" src="@/assets/icon.png" alt="거래처">
                    <div class="card-body">
                      <h4 class="card-title h2 bm-0">거래처관리</h4>
                    </div>
                  </div>
                </div>
                <div class="col col-lg-4">
                  <div class="card" @click="goNftCreate" style="background-color: #FC5E5E; width: 20rem;">
                    <img class="card=img-top" src="@/assets/icon.png" alt="nft발급">
                    <div class="card-body">
                      <h4 class="card-title h2 bm-0">NFT 발급</h4>
                    </div>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 카드 부분 -->

      <!-- <div class="content_outside_box">
        <div class="content_box">
          <div class="container">
            <div class="nft_img">
              <img src="icon.png" alt="nft이미지" style="width: 250px;">
            </div>
            <div>
              <p>nft 내용 불러오기</p>
            </div>
          </div> -->

          <!-- 관리자만 버튼 보이게 -->
          <!-- <div class="createBtn_position2">
            <button type="button" class="btn createBtn mx-2" @click="updateNotice" style="width: 60px">발급</button>
          </div>
        </div> -->

      <!-- </div> -->
      <!-- </div> -->
    <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import '@/assets/style/notice/noticeSide.css'
import { useRouter } from 'vue-router'

export default {
  name : 'AdminPage',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()

    function goNoticeMain() {
      router.push({name: 'MainNotice'})
    }
    function goPartenrManagement() {
      router.push({name: 'PartnerManagement'})
    }
    function goNftCreate() {
      router.push({name: 'NftCreate'})
    }

    // 글 저장하는 함수 생성(저장하고 디테일로 보내기)
    return {
      goNoticeMain,
      goPartenrManagement,
      goNftCreate,
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