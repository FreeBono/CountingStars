<template>
  <!-- <h2>nft 발급 페이지</h2> -->
  <div>
  <sidebar/>
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <div class="header">
        <div class="head_title">NFT 발급</div>
      </div>
      <div class="content_outside_box">
        <div class="content_box row-vh d-flex flex-row">
          <div style="width:40%; align-items:center; margin-top:8rem;">
            <FileUpload @file-upload="imageData"/>
          </div>
          <div  id="container" style="width:60%;">
            <!-- <h1 style="margin-top:50px;"> further information </h1> -->
            <!-- <div class="underline"></div> -->
            
            <form action="#" method="post" id="contact_form">
              
              <div class="telephone">
                <label for="name"></label>
                <input type="text" placeholder="일련번호" name="telephone" id="telephone_input" v-model="state.serialNumber" required >
              </div>
              <div class="telephone">
                <label for="name"></label>
                <input type="text" placeholder="상품명" name="telephone" id="telephone_input" v-model="state.nftName" required>
              </div>
              <div class="telephone">
                <label for="name"></label>
                <input type="text" placeholder="가격" name="telephone" id="telephone_input" v-model="state.price" required>
              </div>
              <div class="telephone">
                <label for="name"></label>
                <input type="text" placeholder="설명칸" name="telephone" id="telephone_input" v-model="state.description" required >
              </div>
              <div class="name">
                <label for="name"></label>
                <input type="text" placeholder="브랜드명" name="name" id="name_input" v-model="state.brandName" required>
              </div>
              <div class="email">
                <label for="email"></label>
                <input type="email" placeholder="제조국가" name="email" id="email_input" v-model="state.country" required>
              </div>
              <div class="name">
                <label for="name"></label>
                <input type="text" placeholder="제조일자" name="name" id="name_input" v-model="state.productDate" required>
              </div>
              <div class="email">
                <label for="email"></label>
                <input type="email" placeholder="상품 분류" name="email" id="email_input" v-model="state.category" required>
              </div>
              <div class="name">
                <label for="name"></label>
                <input type="text" placeholder="소재" name="name" id="name_input" v-model="state.material" required>
              </div>
              <div class="email">
                <label for="email"></label>
                <input type="email" placeholder="색상" name="email" id="email_input" v-model="state.color" required>
              </div>
              
            

              <div class="submit" align="right">
                <button type="button" class="btn btn-primary" @click="transferJSON">발급</button>
              </div>
            </form><!-- // End form -->
          </div><!-- // End #container -->
        </div>

      </div>
    </div>
    <!-- 내용 들어갈 곳 끝 -->
  </div>
  </div>
</template>

<script>
import {ref} from 'vue'
import { create } from "ipfs-http-client";
import store from '@/store'
import Sidebar from '@/components/Sidebar.vue'
import FileUpload from "@/components/common/FileUpload.vue"
import publishToken from '@/utils/PublishNFT'
import encodeImageFileAsURL from '../../services/encodeImageFileAsURL'
import getMetadataFromIpfs from '../../services/getMetadataFromIpfs'
import { createToast } from 'mosha-vue-toastify';

export default {
  name: 'NftCreate',
  components: {
    Sidebar,
    FileUpload,
  },
  setup() {
    const state = ref({
      serialNumber: 'AAA11111',
      productDate: '2022-03-16',
      brandName: 'Chanel',
      country: 'Italy',
      category: 'Class Bag',
      material: 'cowhide',
      color: 'black',
      price: '5,700$',
      description: '설명 칸',
      nftName: 'nft 이름',
      nftImg: null,
      nftImgFile: null,
    })

    const imageData = (event) => {
    
      state.value.nftImg = event.nftImg
      state.value.nftImgFile = event.nftImgFile
      // console.log(state.value.nftImg, '이미지')
      // console.log(state.value.nftImgFile, '이미지 파일')
      encodeImageFileAsURL(state.value.nftImgFile)
    }

    const transferJSON = async function() {
      const data = {
        name: "Luxury",
        description: "It contains a warranty for luxury goods.",
        serialNumber: state.value.serialNumber,
        dateOfManufacture: state.value.productDate,
        brandName: state.value.brandName,
        countryOfManufacture: state.value.country,
        productClassification: state.value.category,
        material: state.value.material,
        productColor: state.value.color,
        productPrice: state.value.price,
        image: store.state.ipfsData,
        description: state.value.description,
        nftName: state.value.nftName,
      }

      const ipfs = create();
      const response = await ipfs.add(JSON.stringify(data));
      const ipfsHash = response.path;

      // const response = await pinata(state.value.nftImgFile);
      // data.image = "https://gateway.pinata.cloud/ipfs/" + response.data.IpfsHash; // ipfs:// + response.data.IpfsHash를 넣어야 하나? 다른 NFT는 다 이렇게 넣던데
      // const jsonResponse = await pinataJson(data);

      console.log(ipfsHash); // json ipfs 주소
      publishToken(ipfsHash)
      createToast(
        { title: 'Send NFT issue request',  },
        // {position:'bottom-right',showIcon:true,toastBackgroundColor:'#44ec3e'}
        { type:'success', showIcon:true, position:'bottom-right', }
        )

      // getMetadataFromIpfs(ipfsHash);
    }

    return {
      state,
      imageData,
      transferJSON,
    }
  }
}
</script>

<style scoped>
.content_outside_box {
  display: flex;
  justify-content: center;
}

.head_title {
  color: white;
  position:absolute; 
  margin-left:100px; 
  margin-top: 50px;
}

.nft_img {
  display: flex;
  justify-content: flex-start;
  top: 150px;
  position: relative;
  left: 100px;
}

.join_step {
    display: flex;
    justify-content: center;
    align-items: center;
}
.join_tit h6 {
    font-size: 1.625rem;
    line-height: 1.4;
    letter-spacing: -0.6px;
    font-weight: 300;
    margin-bottom: 30px;
} 
.join_tit span.now {
    width: 20px;
    height: 20px;
    line-height: 20px;
    background: #222222;
    color: white;
    font-size: 0.875rem;
    letter-spacing: -0.25px;
    text-align: center;
}

.join_tit span {
    font-family: 'Roboto', sans-serif;
    display: block;
    text-align: center;
    width: 12px;
    height: 12px;
    background: #cccccc;
    margin-left: 30px;
    border-radius: 50%;
    margin: 0 15px;
}

.join_tbox_txt {
    font-size: 1.25rem;
    line-height: 2rem;
    letter-spacing: -0.6px;
    font-weight: 400;
}



/* 3333333333333333333333333333333333333333 */
@import url(https://fonts.googleapis.com/css?family=Montserrat:400,700);

html {
  font-family: 'Montserrat', Arial, sans-serif;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
}


button {
  overflow: visible;
}

button, select {
  text-transform: none;
}

button, input, select, textarea {
  color: #5A5A5A;
  font: inherit;
  margin: 0;
}

input {
  line-height: normal;
}

textarea {
  overflow: auto;
}

#container {
  /* border: solid 3px #474544; */
  max-width: 768px;
  margin: 60px auto;
  position: relative;
}

form {
  padding: 37.5px;
  margin: 50px 0;
}

h1 {
  color: #474544;
  font-size: 32px;
  font-weight: 700;
  letter-spacing: 7px;
  text-align: center;
  text-transform: uppercase;
}

.underline {
  border-bottom: solid 2px #474544;
  margin: -0.512em auto;
  width: 650px;
  margin-top: 10px;
}

.icon_wrapper {
  margin: 50px auto 0;
  width: 100%;
}

.icon {
  display: block;
  fill: #474544;
  height: 50px;
  margin: 0 auto;
  width: 50px;
}

.email {
	float: right;
	width: 45%;
}

input[type='text'], [type='email'], select, textarea {
	background: none;
  border: none;
	border-bottom: solid 2px #474544;
	color: #474544;
	font-size: 1.000em;
  font-weight: 400;
  letter-spacing: 1px;
	margin: 0em 0 1.875em 0;
	padding: 0 0 0.875em 0;
  text-transform: uppercase;
	width: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	-o-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-ms-transition: all 0.3s;
	-o-transition: all 0.3s;
	transition: all 0.3s;
}

input[type='text']:focus, [type='email']:focus, textarea:focus {
	outline: none;
	padding: 0 0 0.875em 0;
}

.message {
	float: none;
}

.name {
	float: left;
	width: 45%;
}

select {
  background: url('https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-arrow-down-32.png') no-repeat right;
  outline: none;
  -moz-appearance: none;
  -webkit-appearance: none;
}

select::-ms-expand {
  display: none;
}

.subject {
  width: 100%;
}

.telephone {
  width: 100%;
}

textarea {
	line-height: 150%;
	height: 150px;
	resize: none;
  width: 100%;
}

::-webkit-input-placeholder {
	color: #474544;
}

:-moz-placeholder { 
	color: #474544;
	opacity: 1;
}

::-moz-placeholder {
	color: #474544;
	opacity: 1;
}

:-ms-input-placeholder {
	color: #474544;
}

.btn {
  /* background: none;
  border: solid 2px #474544;
  color: #474544;
  cursor: pointer;
  display: inline-block;
   */
  
  background-color:#28B9B0;
  border : solid 0px;
  width : 80px;
  height : 40px;
  color:black;
  border-radius : 5px;
}



@media screen and (max-width: 768px) {
  #container {
    margin: 20px auto;
    width: 95%;
  }
}

@media screen and (max-width: 480px) {
  h1 {
    font-size: 26px;
  }
  
  .underline {
    width: 68px;
  }
  
  #form_button {
    padding: 15px 25px;
  }
}

@media screen and (max-width: 420px) {
  h1 {
    font-size: 18px;
  }
  
  .icon {
    height: 35px;
    width: 35px;
  }
  
  .underline {
    width: 53px;
  }
  
  input[type='text'], [type='email'], select, textarea {
    font-size: 0.875em;
  }
}
</style>