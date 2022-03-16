<template>
  <div class="container">
    <h1>Pinata API test</h1>
    <div>
      <div class="row mb-3">
        <label for="serialNumber" class="col-sm-2 col-form-label">일련번호</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="serialNumber" v-model="state.serialNumber">
        </div>
      </div>
      <div class="row mb-3">
        <label for="dateOfManufacture" class="col-sm-2 col-form-label">제조날짜</label>
        <div class="col-sm-10">
          <input type="date" class="form-control" id="dateOfManufacture" v-model="state.serialNumber">
        </div>
      </div>
      <div class="row mb-3">
        <label for="brandName" class="col-sm-2 col-form-label">브랜드명</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="brandName" v-model="state.brandName">
        </div>
      </div>
      <div class="row mb-3">
        <label for="countryOfManufacture" class="col-sm-2 col-form-label">제조국가</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="countryOfManufacture" v-model="state.serialNumber">
        </div>
      </div>
      <!-- productClassification
      <fieldset class="row mb-3">
        <legend class="col-form-label col-sm-2 pt-0">상품분류</legend>
        <div class="col-sm-10">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="의류" checked>
            <label class="form-check-label" for="gridRadios1">
              의류
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="악세사리">
            <label class="form-check-label" for="gridRadios2">
              악세사리
            </label>
          </div>
          <div class="form-check disabled">
            <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="가방">
            <label class="form-check-label" for="gridRadios3">
              가방
            </label>
          </div>
        </div>
      </fieldset> -->
      <div class="row mb-3">
        <label for="productClassification" class="col-sm-2 col-form-label">상품분류</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="productClassification" v-model="state.productClassification">
        </div>
      </div>
      <div class="row mb-3">
        <label for="detailProductClassification" class="col-sm-2 col-form-label">상세분류</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="detailProductClassification" v-model="state.detailProductClassification">
        </div>
      </div>
      <div class="row mb-3">
        <label for="material" class="col-sm-2 col-form-label">소재</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="material" v-model="material">
        </div>
      </div>
      <div class="row mb-3">
        <label for="productColor" class="col-sm-2 col-form-label">색상</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="productColor" v-model="state.productColor">
        </div>
      </div>
      <div class="row mb-3">
        <label for="productPrice" class="col-sm-2 col-form-label">가격</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="productPrice" v-model="state.productPrice">
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">이미지</label>
          <div class="col-sm-10">
            <div class="d-flex justify-content-center">
              <label class="front__text-hover btn btn-info" for="input-file" style="cursor: pointer;">업로드</label>
              <input @change="changeImgFile" type="file" id="input-file" style="display: none;"><!-- C -->
            </div>
            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper mb-5">
              <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
              <img class="card__thumb" v-bind:src="nftImg" >
            </div>
      </div>
        </div>
        <button @click="transferJSON" class="btn btn-primary">NFT 등록</button>
    </div>
  </div>
</template>

<script>
// import pinata from '../services/pinata'
// import pinataJson from '../services/pinataJson'
import { ref } from 'vue';
import { onMounted } from 'vue';

export default {
  name : 'Pinata',
  setup() {
    const state = ref({
      serialNumber: 'AAA11111',
      dateOfManufacture: new Date('2022-03-16'),
      brandName: 'Chanel',
      countryOfManufacture: 'Italy',
      productClassification: 'Bag',
      detailProductClassification: 'Class Bag',
      material: 'cowhide',
      productColor: 'black',
      productPrice: '5,700$',
      nftImg: null,
      nftImgFile: null,
    })

    const changeImgFile = async function (event) {
      if( event.target.files && event.target.files.length > 0 ) {
        
        state.value.nftImgFile = event.target.files[0];

        state.value.nftImg = URL.createObjectURL(state.value.nftImgFile); // 파일 경로로 바꿔서 추가
        //await pinata(file);
        console.log(state.value.nftImg)
      }
    }

    const transferJSON = async function (url) {
      const data = {
        serialNumber: this.serialNumber,
        dateOfManufacture: state.dateOfManufacture,
        brandName: brandName,
        countryOfManufacture: countryOfManufacture,
        productClassification: productClassification,
        detailProductClassification: detailProductClassification,
        material: material,
        productColor: productColor,
        productPrice: productPrice,
        nftImgURI: url,
      }

      console.log(data);

      //await pinataJson(data);
    }



    return {
      onMounted, state,
      changeImgFile, transferJSON
    }
  },
  props: {
    msg: String
  }
}
</script>

<style>

</style>