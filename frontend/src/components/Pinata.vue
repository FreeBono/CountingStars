<template>
  <div class="container">
    <button @click="TransferToken()">ㅇㅇㅇ</button>
    <button @click="SearchToken()">에고고</button>
    <button @click="qwe()">테스트</button>
    
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
          <input type="date" class="form-control" id="dateOfManufacture" v-model="state.dateOfManufacture">
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
          <input type="text" class="form-control" id="countryOfManufacture" v-model="state.countryOfManufacture">
        </div>
      </div>
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
          <input type="text" class="form-control" id="material" v-model="state.material">
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
        <FileUpload @file-upload="imageData"/>
          <!-- <div class="col-sm-10">
            <div class="d-flex justify-content-center">
              <label class="front__text-hover btn btn-info" for="input-file" style="cursor: pointer;">업로드</label>
              <input @change="changeImgFile" type="file" id="input-file" style="display: none;">
            </div>
            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper mb-5">
              <img class="card__thumb" v-bind:src="nftImg" >
            </div>
          </div> -->
          <img id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper mb-5" :src="state.downImage" />
      </div>
        <div class="d-flex justify-content-center">
          <label class="front__text-hover btn btn-info" for="input-file" style="cursor: pointer;">엑셀 파일 업로드</label>
          <input id="input-file" type="file" @change="changeExcelFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display: none;">
        </div>

        <button @click="transferJSON" class="btn btn-primary">NFT 등록</button>
    </div>
  </div>
  <DoughnutChart :chartData="testData" style="width:700px;"/>
  <!-- <Block/> -->
</template>

<script>
import { ref,computed } from 'vue';
import axios from "axios";
import { onMounted } from 'vue';
import TransferToken from '../utils/TransferNFT'
import SearchToken from '../utils/SearchNFT'
import qwe from '../utils/LookupNFT'
import { DoughnutChart,BarChart,LineChart, } from 'vue-chart-3';
import FileUpload from "@/components/common/FileUpload.vue"
import { Chart, registerables } from "chart.js";
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
import { create } from "ipfs-http-client";
import encodeImageFileAsURL from '../services/encodeImageFileAsURL'
import store from '@/store'
import XLSX from "xlsx";

Chart.register(...registerables);

export default {
  name : 'Pinata',
  components: { DoughnutChart, BarChart,LineChart, FileUpload, },
  setup() {
    
    // var web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/dc8ab5b698624450b473183f2d33e5b3'));
    const state = ref({
      serialNumber: 'AAA11111',
      dateOfManufacture: new Date('2022-03-16'),
      brandName: 'Chanel',
      countryOfManufacture: 'Italy',
      productClassification: 'Bag',
      detailProductClassification: 'Class Bag',
      material: 'cowhide',
      productColor: 'black',
      productPrice: '5,700',
      nftImg: null,
      nftImgFile: null,
      downImage: null,
    })

    const test = ref('');

    const imageData = (event) => {
      state.value.nftImg = event.nftImg
      state.value.nftImgFile = event.nftImgFile
      encodeImageFileAsURL(state.value.nftImgFile)
      test.value = store.state.ipfsData
    }

    const transferJSON = async function () {
      const metadata = {
        name: "Luxury",
        description: "It contains a warranty for luxury goods.",
        serialNumber: state.value.serialNumber,
        dateOfManufacture: state.value.dateOfManufacture,
        brandName: state.value.brandName,
        countryOfManufacture: state.value.countryOfManufacture,
        productClassification: state.value.productClassification,
        detailProductClassification: state.value.detailProductClassification,
        material: state.value.material,
        productColor: state.value.productColor,
        productPrice: state.value.productPrice,
        image: test.value,
      }

      // http://172.17.0.1:5001
      // const ipfs = create('/ip4/127.0.0.1/tcp/5001');

      const ipfs = create({
        // host: "/ipfs",
        // // port: 443,
        // protocol: "https",
      });

      console.log("동작하는건가???");

      // api/vo/add
      ipfs
      .add(JSON.stringify(metadata))
      .then(res => {
        console.log("ipfs add 요청 성공");
        console.log(res);
      })
      .error( e => {
        console.log("ipfs add 요청 에러")
        console.log(e);
      });

      // axios
      // .post('http://172.17.0.1:5001/api/v0/add', metadata)
      // .then( res => {
      //   console.log(res);
      // })
      // .catch( e => {
      //   console.error(e);
      // });

      // axios({
      //   method: 'post',
      //   url : `http://172.17.0.1:5001/api/v0/add`
      // }, metadata)
      // .then(res => console.log(res.data))
      // .catch(err => console.log(err))

      // const ipfs = create(
      //   host: "127.0.0.1",
      //   port: 5001,
      //   protocol: "http"
      // });


      // const uploadIpfs = await ipfs.add(JSON.stringify(metadata));

      // console.log(uploadIpfs.path);

      // axios
      // .post('http://127.0.0.1:5001/api/v0/add', metadata)
      // .then( res => {
      //   console.log(res);
      // })
      // .catch( e => {
      //   console.log(e);
      // });

      // console.log(state.value.nftImgFile);

      // console.log(state.value.nftImg);


      // encodeImageFileAsURL(state.value.nftImgFile).then(res  => {
      //   console.log(res);
      // })

      // console.log(base64File);

      // const base64File = await encodeImageFileAsURL(state.value.nftImgFile);

      // reader.readAsArrayBuffer()
      // const imageEncoding = base64.replace()
      // const bufferImage = Buffer.write(state.value.nftImgFile , 'base64');

      // const ipfsUpload = await ipfs.add(bufferImage);

      // console(ipfsUpload.path);


      // const formData = new FormData();

      // formData.append('metadata', new Blob([JSON.stringify(metadata)] , {type: "application/json"}));
      // formData.append('image', state.value.nftImgFile);

      // var cid = "";

      // axios
      // .post(`http://127.0.0.1:8081/api/v1/ipfs`, formData)
      // .then(function (response) {
      //   console.log(response);

      //   const resData = response.data;

      //   state.value.downImage = "data:image/jpeg;base64," + resData.image;

      //   // cid = response.data.message;

      // })
      // .catch(function (error) {
      //   console.log(error);
      // })

    }

    const changeExcelFile = async function (event) {
      const file = event.target.files[0];
      const reader = new FileReader(); //FileReader를 생성한다.
      let tmpResult = {};

      //성공적으로 읽기 동작이 완료된 경우 실행되는 이벤트 핸들러를 설정한다.
      reader.onload = function(e) {
          // let data = reader.result;
          let data = e.target.result;
          //바이너리 형태로 엑셀파일을 읽는다.
          let workbook = XLSX.read(data, {type: 'binary'});
          workbook.SheetNames.forEach(sheetName => {
            workbook.Sheets[sheetName].A1.w = "test1";
            workbook.Sheets[sheetName].B1.w = "test2";
            workbook.Sheets[sheetName].C1.w = "test3";
            workbook.Sheets[sheetName].D1.w = "test4";

            console.log(workbook.Sheets[sheetName].A1);
            const roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
            tmpResult = roa;
          });
          console.log(tmpResult);
      };
      //파일객체를 읽는다. 완료되면 원시 이진 데이터가 문자열로 포함됨.
      reader.readAsBinaryString(file);

      // const formData = new FormData();
      // var excel = event.target.files[0];
      // formData.append('excel', excel);
      // // var cid = "";
      // axios
      // .post(`http://127.0.0.1:8081/api/v1/ipfs/excel`, formData)
      // .then(function (response) {
      //   console.log(response);
      // })
      // .catch(function (error) {
      //   console.log(error);
      // })
    }

    const testData = {
      labels: ['Paris', 'Nîmes', 'Toulon', 'Perpignan', 'Autre'],
      datasets: [
        {
          data: [30, 40, 60, 70, 5],
          backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4', '#A5C8ED'],
        },
      ],
    };

    return {
      onMounted, state,
      imageData, transferJSON, changeExcelFile,
      TransferToken,
      SearchToken,
      qwe,
      testData,
  
    }
  },
  props: {
    msg: String
  }
}
</script>

<style>

</style>
