<template>
  <body>
    <nav class="main-menu">
      <ul>
        <li>
          <a href="/main">
            <i class="fa fa-home fa-2x"></i>
            <span class="nav-text">
              MainPage
            </span>
          </a>
        </li>
          <li class="has-subnav">
            <a href="/mynft">
              <i class="fa fa-wallet fa-2x"></i>
              <span class="nav-text">
                My NFT
              </span>
            </a>
          </li>
          <li class="has-subnav">
            <a href="/nftwalletsearch">
              <i class="fa fa-list fa-2x"></i>
              <span class="nav-text">
                NFT 조회
              </span>
            </a>
          </li>
          <li class="has-subnav">
            <a href="/nfttransfer">
              <i class="fa fa-folder-open fa-2x"></i>
              <span class="nav-text">
                NFT 이전
              </span>
            </a>
          </li>
          <li>
            <a href="/notice">
              <i class="fa fa-bar-chart-o fa-2x"></i>
              <span class="nav-text">
                공지사항
              </span>
          </a>
          </li>
          <li>
            <a href="/admin">
              <i class="fa fa-font fa-2x"></i>
              <span class="nav-text">
                ADMIN
              </span>
            </a>
          </li>
          <li>
            <a href="/partner">
              <i class="fa fa-table fa-2x"></i>
              <span class="nav-text">
                거래처 관리
              </span>
            </a>
          </li>
          <li>
            <a href="nftcreate">
              <i class="fa fa-map-marker fa-2x"></i>
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
export default {
  name: 'Sidebar',
}
</script>

<style scoped>

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
  /* right: 20px; */
}


.main-menu:hover,nav.main-menu.expanded {
  width:250px;
  overflow:visible;
}

.main-menu {
  background:#ffffff;
  border-right:1px solid #e5e5e5;
  position:absolute;
  top:0;
  bottom:0;
  height:100%;
  left:0;
  width: 60px;
  overflow: hidden;
  -webkit-transition:width .05s linear;
  transition:width .05s linear;
  -webkit-transform:translateZ(0) scale(1,1);
  z-index:1000;
}

.main-menu>ul {
  margin: 7px 0;
}

.main-menu li {
  position: relative;
  display: block;
  width: 200px;
}

.main-menu li>a {
  position:relative;
  display:table;
  border-collapse:collapse;
  border-spacing:0;
  color:#999;
  font-family: arial;
  font-size: 14px;
  text-decoration:none;
  -webkit-transform:translateZ(0) scale(1,1);
  -webkit-transition:all .1s linear;
  transition:all .1s linear;
  
}

.main-menu .nav-icon {
  position:relative;
  display:table-cell;
  width:60px;
  height:36px;
  text-align:center;
  vertical-align:middle;
  font-size:18px;
}

.main-menu .nav-text {
  position:relative;
  display:table-cell;
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
  background-color:#28b9b0d6;
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