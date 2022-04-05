<template>
  <div class="container">
    <div>
      <h3 class="mb-5">NFT 일괄 등록</h3>

      <h5>1. 엑셀 파일을 먼저 등록해 주세요.</h5>
      <div class="d-flex justify-content-center mb-5">
        <label class="front__text-hover btn btn-success" for="input-excel-file" style="cursor: pointer;">엑셀 파일 업로드</label>
        <input id="input-excel-file" type="file" @change="changeExcelFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display: none;">
      </div>

      <h5>2. 이미지 파일들을 전부 등록 해주세요. 이미지 파일명은 명품 일련번호와 같아야 합니다.</h5>
      <div class="d-flex justify-content-center mb-5">
        <label class="front__text-hover btn btn-info" for="input-image-file" style="cursor: pointer;">이미지 파일 업로드</label>
        <input @change="changeImageFile" type="file" id="input-image-file" style="display: none;" multiple>
      </div>

      <h5>3. NFT 일괄 발행을 눌러주세요.</h5>
      <!-- @click="createNFT" -->
      <button @click="createNFT" class="btn btn-primary mb-5">NFT 일괄 발행</button>

      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">이미지</th>
            <th scope="col">일련번호</th>
            <th scope="col">제조날짜</th>
            <th scope="col">브랜드명</th>
            <th scope="col">제조국가</th>
            <th scope="col">상품분류</th>
            <th scope="col">상품상세분류</th>
            <th scope="col">소재</th>
            <th scope="col">색상</th>
            <th scope="col">가격</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(metadata, idx) in excelData" :key="idx" >
            <th scope="row">{{idx + 1}}</th>
            <!-- 기본 이미지로 바꾸기 -->
            <td><img v-if='metadata.image != null' :src='metadata.image' style="width:50px; height:50px;">
                <img v-else src='@/assets/uploadicon.jpg' style="width:50px; height:50px;">
            </td>
            <td><input class="form-control" type="text" v-model="metadata.serialNumber"/></td>
            <td><input class="form-control" type="text" v-model="metadata.dateOfManufacture"/></td>
            <td><input class="form-control" type="text" v-model="metadata.brandName"/></td>
            <td><input class="form-control" type="text" v-model="metadata.countryOfManufacture"/></td>
            <td><input class="form-control" type="text" v-model="metadata.productClassification"/></td>
            <td><input class="form-control" type="text" v-model="metadata.detailProductClassification"/></td>
            <td><input class="form-control" type="text" v-model="metadata.material"/></td>
            <td><input class="form-control" type="text" v-model="metadata.productColor"/></td>
            <td><input class="form-control" type="text" v-model="metadata.productPrice"/></td>
          </tr>
        </tbody>
      </table>
    </div>
    <!--------------------------- NFT Add All From Excel END --------------------------->
  </div>
</template>

<script>
import { ref } from 'vue';
import { onMounted } from 'vue';
import 'mosha-vue-toastify/dist/style.css'
import { create } from "ipfs-http-client";
import publishToken from '@/utils/PublishNFT'
import encodeImageFileExcel from '@/services/encodeImageFileExcel'
import getMetadataFromIpfs from '@/services/getMetadataFromIpfs'

export default {
  name : 'Pinata',
  setup() {
    const excelData = ref([]);
    excelData.value = []

    // 엑셀 변경되면 읽기
    const changeExcelFile = async function (event) {
      const file = event.target.files[0];
      var XLSX = require("xlsx");
      const reader = new FileReader(); //FileReader를 생성한다.
      let tmpResult = [];

      // 성공적으로 읽기 동작이 완료된 경우 실행되는 이벤트 핸들러를 설정한다.
      reader.onload = function(e) {
          // let data = reader.result;
          let data = e.target.result;

          // 바이너리 형태로 엑셀파일을 읽는다.
          let workbook = XLSX.read(data, {type: 'binary'});
          workbook.SheetNames.forEach(sheetName => {
            workbook.Sheets[sheetName].A1.w = "serialNumber";
            workbook.Sheets[sheetName].B1.w = "dateOfManufacture";
            workbook.Sheets[sheetName].C1.w = "brandName";
            workbook.Sheets[sheetName].D1.w = "countryOfManufacture";
            workbook.Sheets[sheetName].E1.w = "productClassification";
            workbook.Sheets[sheetName].F1.w = "detailProductClassification";
            workbook.Sheets[sheetName].G1.w = "material";
            workbook.Sheets[sheetName].H1.w = "productColor";
            workbook.Sheets[sheetName].I1.w = "productPrice";

            // console.log(workbook.Sheets[sheetName].A1);
            const roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
            tmpResult = roa;
          });
          // console.log(tmpResult);

          excelData.value = tmpResult;

          // console.log(excelData.value);
      };

      // 파일객체를 읽는다. 완료되면 원시 이진 데이터가 문자열로 포함됨.
      reader.readAsBinaryString(file);
    }

    const changeImageFile = (event) => {
      // 파일을 올렸을 때만 동작
      if( event.target.files && event.target.files.length > 0 ){
        var files = [...event.target.files];
        var metadata = [...excelData.value];
        // console.log(metadata);
        // console.log(files);
        
        for(var i = files.length - 1; i >= 0; i--) {
          // 확장자 자르고 이름
          var fileName = files[i].name.slice(0, files[i].name.indexOf('.'));

          for(var j = metadata.length - 1; j >= 0; j--) {

            // 각 엘리먼트와 이름 비교
            if(metadata[j].serialNumber == fileName) {
              // base64로 encoding된 이미지 파일 metadata에 넣기
              encodeImageFileExcel(metadata[j], files[i]);

              // j번부터 1개 요소 제거
              metadata.splice(j, 1);
              break;
            }
          }
        } // for end

        // console.log(excelData.value);
      }
    }

    const createNFT = async function () {
      // 각 이미지, 일련번호, 제조날짜, 브랜드명, 제조국가, 상품분류, 상품상세 분류, 소재, 색상, 가격
      // 위 속성 중 하나라도 빠져있으면 nft 발행 안함 
      // 발행 후 excelData.value에서 빼버리기 
      // 빼고 남은 것들은 데이터가 잘못 들어가 있어 등록이 안된 NFT들 정보 추가 및 수정 가능
      const ipfs = create();
      // const ipfs = create('/ip4/127.0.0.1/tcp/5001'); // 로컬에서 동작 안하면

      for(var i = excelData.value.length - 1; i >= 0; i--) {
        var element = excelData.value[i];

        // 분기문에서 null, undefined, '', 0 은 false로 변환
        if( !element.image ||
          !element.serialNumber ||
          !element.dateOfManufacture ||
          !element.brandName ||
          !element.countryOfManufacture ||
          !element.productClassification ||
          !element.detailProductClassification ||
          !element.material ||
          !element.productColor ||
          !element.productPrice) continue;

        const metadata = {
          name: "Luxury",
          description: "It contains a warranty for luxury goods.",
          serialNumber: element.serialNumber,
          dateOfManufacture: element.dateOfManufacture,
          brandName: element.brandName,
          countryOfManufacture: element.countryOfManufacture,
          productClassification: element.productClassification,
          detailProductClassification: element.detailProductClassification,
          material: element.material,
          productColor: element.productColor,
          productPrice: element.productPrice,
          image: element.image,
        }

        // api/v0/add
        const response = await ipfs.add(JSON.stringify(metadata));
        const ipfsHash = response.path;

        excelData.value.splice(i, 1);

        await publishToken(ipfsHash);
        const result = await getMetadataFromIpfs(ipfsHash);

        console.log(result);
      }
    }

    return {
      onMounted,
      changeExcelFile,
      excelData,
      changeImageFile,
      createNFT
    }
  },
  props: {
    msg: String
  }
}
</script>

<style>
/* input {
  width:50px;
  height:20px;
  font-size:5px;
} */
</style>
