<template>
  <body>
    <nav class="main-menu">
      <div class="logo-img-tag" @click="goMainPage">
        <img class="fa-2x" src="@/assets/cslogo.png" alt="로고부분" style="width:30px;">
        <p class="logo-title">Counting Stars</p>
      </div>
      <ul>
        <li >
          <a href="/">
            <i class="fa fa-home fa-2x" style="color: #5e72e4;"></i>
            <span class="nav-text">
              MainPage
            </span>
          </a>
        </li>
          <li class="has-subnav">
            <a href="/mynft">
              <i class="fa fa-wallet fa-2x" style="color: #9d6510;"></i>
              <span class="nav-text">
                My NFT
              </span>
            </a>
          </li>
          <li class="has-subnav">
            <a href="/nftwalletsearch">
              <i class="fa fa-search fa-2x" style="color: #fb6340;"></i>
              <span class="nav-text">
                NFT 조회
              </span>
            </a>
          </li>
          <li class="has-subnav">
            <a href="/nfttransfer">
              <i class="fa fa-people-arrows fa-2x" style="color: #2dce89;"></i>
              <span class="nav-text">
                NFT 이전
              </span>
            </a>
          </li>
          <li>
            <a href="/notice">
              <i class="fa fa-bullhorn fa-2x" style="color: #f5365c;"></i>
              <span class="nav-text">
                공지사항
              </span>
            </a>
          </li>
          <li>
            <a href="/admin">
              <i class="fa fa-font fa-2x" style="color: #11cdef;"></i>
              <span class="nav-text">
                ADMIN
              </span>
            </a>
          </li>
          <li>
            <a href="/partner">
              <i class="fa fa-handshake fa-2x" style="color: #6b6d72;"></i>
              <span class="nav-text">
                거래처 관리
              </span>
            </a>
          </li>
          <li>
            <a href="nftcreate">
              <i class="fa fas fa-cubes fa-2x" style="color: #ffd700;"></i>
              <!-- <i class="fa fas fa-cube fa-2x"></i> -->
              <span class="nav-text">
                NFT 발급
              </span>
            </a>
          </li>
      </ul>
    </nav>
  </body>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  name: 'Sidebar',
  setup() {
    const router = useRouter()

    function goMainPage() {
      router.push({name: 'Mainpage'})
    }

    return{
      goMainPage
    }
  }
}
</script>

<style scoped>
.logo-img-tag {
  position: relative;
  display: flex;
  flex-direction: column;
  border-spacing :0;
  /* 원래 흐린 회식 #999 */
  color:rgb(85, 84, 84);
  font-family: arial;
  font-size: 18px;
  -webkit-transform: translateZ(0) scale(1,1);
  -webkit-transition: all .1s linear;
  transition: all .1s linear;
  height: 36px;
  bottom: 10px;
  right: 8px;
}

.logo-img-tag:hover {
  cursor: pointer;
}

.logo-title {
  font-size: 20px;
  position: relative;
  display: block;
  width: 200px;
  text-align: right;
  bottom: 32px;
  font-weight: 800;
  color: #28b9b0d6;
}

.fa-2x {
  font-size: 2em;
}

.fa {
  position: relative;
  display: table-cell;
  width: 60px;
  height: 36px;
  text-align: center;
  vertical-align: middle;
  font-size:20px;
}


.main-menu:hover,nav.main-menu.expanded {
  width:250px;
  overflow:visible;
}

/* 오른쪽에 글자 튀어나오는 부분 */
.main-menu {
  background:#ffffff;
  border-right:1px solid #e5e5e5;
  position:absolute;
  top:0;
  bottom:0;
  height:100%;
  left:0;
  width: 70px;
  /* hidden 으로 바꿔주기 */
  overflow: hidden;
  -webkit-transition:width .05s linear;
  transition:width .05s linear;
  -webkit-transform:translateZ(0) scale(1,1);
  z-index:1000;
}

.main-menu>ul {
  margin: 7px 0;
}

/* 내용물 부분 크기 조절, 위치 조정 여기서 */
.main-menu li {
  position: relative;
  display: flex !important;
  width: 230px;
  right: 20px;
  margin-top: 15px;
  /* justify-content: center; */
}

.main-menu li>a {
  position:relative;
  display:table;
  border-collapse:collapse;
  border-spacing:0;
  /* 원래 흐린 회식 #999 */
  color:rgb(85, 84, 84);
  font-family: arial;
  font-size: 18px;
  text-decoration:none;
  -webkit-transform:translateZ(0) scale(1,1);
  -webkit-transition:all .1s linear;
  transition:all .1s linear;
  
}

.main-menu .nav-icon {
  position:relative;
  display:table-cell !important;
  width:60px;
  height:36px;
  text-align:center !important;
  vertical-align:middle;
  font-size:18px;
}

.main-menu .nav-text {
  position:relative;
  display:table-cell !important;
  vertical-align:middle;
  width: 190px;
}

.no-touch .scrollable.hover {
  overflow-y:hidden;
}

.no-touch .scrollable.hover:hover {
  overflow-y:auto;
  overflow:visible;
}

a:hover,a:focus {
  text-decoration:none;
}

nav {
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  -o-user-select:none;
  user-select:none;
}

nav ul,nav li {
  outline:0;
  margin:0;
  padding:0;
}

.main-menu li:hover>a,nav.main-menu li.active>a,.dropdown-menu>li>a:hover,.dropdown-menu>li>a:focus,.dropdown-menu>.active>a,.dropdown-menu>.active>a:hover,.dropdown-menu>.active>a:focus,.no-touch .dashboard-page nav.dashboard-menu ul li:hover a,.dashboard-page nav.dashboard-menu ul li.active a {
  color:#ffffff;
  background-color:#3adacf;
}
/* .main-menu li > a,nav.main-menu li.active>a,.dropdown-menu>li>a:hover,.dropdown-menu>li>a:focus,.dropdown-menu>.active>a,.dropdown-menu>.active>a:hover,.dropdown-menu>.active>a:focus,.no-touch .dashboard-page nav.dashboard-menu ul li:hover a,.dashboard-page nav.dashboard-menu ul li.active a {
  color:#ffffff;
  background-color:#28b9b0d6;
} */

.area {
  float: left;
  background: #e2e2e2;
  width: 100%;
  height: 100%;
}


</style>