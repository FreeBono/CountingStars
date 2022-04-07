<template>
  <div class="wrapper">
      <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <sidebar/>
      <div class="header">
        <p class="head_title" >NFT 발급</p>

        <!-- 위에 카드부분 -->
      <div class="row-vh d-flex" style="position: relative; left: 2.5rem; top: 30px; margin: 0 auto; justify-content: center; width: 85%; margin-top: 120px; justify-content: space-evenly">
          <div class="card col-4" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:23%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; text-align:left;" >  
                    <div class="sub__title">1. 일괄 발급</div>
                    <div class="sub__content mt-3">엑셀 파일을 업로드해 주세요</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <div class="d-flex justify-content-center mb-5">
                      <label class="front__text-hover" for="input-excel-file" style="cursor: pointer;"><i class="fa fa-file-upload fa-3x" style="color: #198754;"></i></label>
                      <input id="input-excel-file" type="file" @change="changeExcelFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display: none;">
                    </div>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card col-4" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:23%; border-radius:10px; text-align:left;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden; ">
                  <div class="media-body" style="float:left;">  
                    <div class="sub__title" style="text-align:left;">2. 이미지 업로드</div>
                    <div class="sub__content mt-2 mx-2" align="left" style="word-break:break-all;">명품 일련번호와 일치하는 이미지를 업로드해 주세요</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top:22px;">
                    <div class="d-flex justify-content-center mb-5">
                      <label class="front__text-hover" for="input-image-file" style="cursor: pointer; margin-left: 1rem;"><i class="fas fa-images fa-3x" style="color: #EB6312;"></i></label>
                      <input @change="changeImageFile" type="file" id="input-image-file" style="display: none;" multiple>
                    </div>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card col-4" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:23%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; text-align:left;">  
                    <div class="sub__title">3. NFT 일괄 발급</div>
                    <div class="sub__content mt-2" align="left">입력 후  NFT 발급 버튼을 눌러주세요</div>
                  </div>
                  <div class="align-self-center" align="right" style="float: right; margin-left: 1rem; margin-top: 22px;">
                    <button @click="createNFT" class="btn btn-primary mb-5">NFT 발급<i class="fas fa-donate fa-2x mx-2" style="color:gold;"></i></button>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
          <div class="card col-4" style="box-shadow:none; background-color:white; margin-right:20px; height:120px; width:23%; border-radius:10px;">
            <div class="card-content">            
              <div class="card-body">
                <div class="media" style="overflow:hidden;">
                  <div class="media-body" style="float:left; text-align:left;">  
                    <div class="sub__title">NFT 개별 발급</div>
                    <div class="sub__content mt-2" align="left">개별 발급</div>
                  </div>
                  <div class="align-self-center" align="right" style="float:right; margin-top: 18px;" @click="goNftcreate">
                    <i class="next__icon fas fa-arrow-circle-right fa-3x" style="color: #A8EB12;"></i>
                  </div>    
                </div>
              </div>
            </div> 
          </div>
        </div>
      <!-- 카드부분 끝 -->
      </div>
      <!-- 헤더 끝 -->

      <div class="content_outside_box">
        <div class="container-fluid mt-7">
          <div class="col">
            <div class="card shadow" style="height: 800px;">
                <div class="card-header border-0 my-2">
                  <h3 class="mb-0">NFT CREATE</h3>
                  <div class="chooseBtnPosition">
                    <button  type="button" class="btn btn-primary chooseShowBtn" data-bs-toggle="modal" data-bs-target="#exampleModal">
                      보여질 속성
                    </button>
                  </div>
                </div>
                <div class="table-responsive">
                  <table class="table align-items-center table-flush table-hover">
                    <thead class="thead-light">
                      <tr>
                        <th scope="col" v-if="checkref.order"><input id="label-order" type="checkbox" v-model="checkref.order"></th>
                        <th scope="col" v-if="checkref.image"><input id="label-image" type="checkbox" v-model="checkref.image"></th>
                        <th scope="col" v-if="checkref.serialNumber"><input id="label-serialNumber" type="checkbox" v-model="checkref.serialNumber"></th>
                        <th scope="col" v-if="checkref.nftName"><input id="label-nftName" type="checkbox" v-model="checkref.nftName"></th>
                        <th scope="col" v-if="checkref.description"><input id="label-description" type="checkbox" v-model="checkref.description"></th>
                        <th scope="col" v-if="checkref.dateOfManufacture"><input id="label-dateOfManufacture" type="checkbox" v-model="checkref.dateOfManufacture"></th>
                        <th scope="col" v-if="checkref.brandName"><input id="label-brandName" type="checkbox" v-model="checkref.brandName"></th>
                        <th scope="col" v-if="checkref.countryOfManufacture"><input id="label-countryOfManufacture" type="checkbox" v-model="checkref.countryOfManufacture"></th>
                        <th scope="col" v-if="checkref.productClassification"><input id="label-productClassification" type="checkbox" v-model="checkref.productClassification"></th>
                        <th scope="col" v-if="checkref.material"><input id="label-material" type="checkbox" v-model="checkref.material"></th>
                        <th scope="col" v-if="checkref.productColor"><input id="label-productColor" type="checkbox" v-model="checkref.productColor"></th>
                        <th scope="col" v-if="checkref.productPrice"><input id="label-productPrice" type="checkbox" v-model="checkref.productPrice"></th>
                      </tr>
                      <tr>
                        <th scope="col" v-if="checkref.order"><label class="front__text-hover" for="label-order" style="cursor: pointer; margin-left: 1rem;">#</label></th>
                        <th scope="col" v-if="checkref.image"><label class="front__text-hover" for="label-image" style="cursor: pointer; margin-left: 1rem;">이미지</label></th>
                        <th scope="col" v-if="checkref.serialNumber"><label class="front__text-hover" for="label-serialNumber" style="cursor: pointer; margin-left: 1rem;">일련번호</label></th>
                        <th scope="col" v-if="checkref.nftName"><label class="front__text-hover" for="label-nftName" style="cursor: pointer; margin-left: 1rem;">명품이름</label></th>
                        <th scope="col" v-if="checkref.description"><label class="front__text-hover" for="label-description" style="cursor: pointer; margin-left: 1rem;">명품설명</label></th>
                        <th scope="col" v-if="checkref.dateOfManufacture"><label class="front__text-hover" for="label-dateOfManufacture" style="cursor: pointer; margin-left: 1rem;">제조날짜</label></th>
                        <th scope="col" v-if="checkref.brandName"><label class="front__text-hover" for="label-brandName" style="cursor: pointer; margin-left: 1rem;">브랜드명</label></th>
                        <th scope="col" v-if="checkref.countryOfManufacture"><label class="front__text-hover" for="label-countryOfManufacture" style="cursor: pointer; margin-left: 1rem;">제조국가</label></th>
                        <th scope="col" v-if="checkref.productClassification"><label class="front__text-hover" for="label-productClassification" style="cursor: pointer; margin-left: 1rem;">상품분류</label></th>
                        <th scope="col" v-if="checkref.material"><label class="front__text-hover" for="label-material" style="cursor: pointer; margin-left: 1rem;">소재</label></th>
                        <th scope="col" v-if="checkref.productColor"><label class="front__text-hover" for="label-productColor" style="cursor: pointer; margin-left: 1rem;">색상</label></th>
                        <th scope="col" v-if="checkref.productPrice"><label class="front__text-hover" for="label-productPrice" style="cursor: pointer; margin-left: 1rem;">가격</label></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(metadata, idx) in excelData" :key="idx" >
                        <th scope="row" v-if="checkref.order">{{idx + 1}}</th>
                        <!-- 기본 이미지로 바꾸기 -->
                        <td v-if="checkref.image"><img v-if='metadata.image != null' :src='metadata.image' style="width:50px; height:50px;">
                          <img v-else src="https://i.ibb.co/LvwqPbz/uploadicon.jpg" style="width:50px; height:50px;">
                        </td>
                        <td v-if="checkref.serialNumber"><input class="form-control" type="text" v-model="metadata.serialNumber"/></td>
                        <td v-if="checkref.nftName"><input class="form-control" type="text" v-model="metadata.nftName"/></td>
                        <td v-if="checkref.description"><input class="form-control" type="text" v-model="metadata.description"/></td>
                        <td v-if="checkref.dateOfManufacture"><input class="form-control" type="text" v-model="metadata.dateOfManufacture"/></td>
                        <td v-if="checkref.brandName"><input class="form-control" type="text"  v-model="metadata.brandName"/></td>
                        <td v-if="checkref.countryOfManufacture"><input class="form-control" type="text" v-model="metadata.countryOfManufacture"/></td>
                        <td v-if="checkref.productClassification"><input class="form-control" type="text" v-model="metadata.productClassification"/></td>
                        <td v-if="checkref.material"><input class="form-control" type="text"  v-model="metadata.material"/></td>
                        <td v-if="checkref.productColor"><input class="form-control" type="text" v-model="metadata.productColor"/></td>
                        <td v-if="checkref.productPrice"><input class="form-control" type="text" v-model="metadata.productPrice"/></td>
                      </tr>
                    </tbody>
                  </table>
            </div>
          </div>
          </div>
        </div>
    <!--------------------------- NFT Add All From Excel END --------------------------->

    <!----------------------------- MODAL---------------------------------------------->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="color: #333333;">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">표시할 항목을 선택하세요</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body" style="margin-left: 30px">
                <div>
                  <div class="row">
                    <div class="row">
                      <div class="col p-3 border bg-light"><input id="modal-label-order" type="checkbox" v-model="checkref.order">
                        <label class="front__text-hover" for="modal-label-order" style="cursor: pointer; margin-left: 1rem;">번호</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-image" type="checkbox" v-model="checkref.image">
                        <label class="front__text-hover" for="modal-label-image" style="cursor: pointer; margin-left: 1rem;">이미지</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-serialNumber" type="checkbox" v-model="checkref.serialNumber">
                        <label class="front__text-hover" for="modal-label-serialNumber" style="cursor: pointer; margin-left: 1rem;">일련번호</label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col p-3 border bg-light"><input id="modal-label-nftName" type="checkbox" v-model="checkref.nftName">
                        <label class="front__text-hover" for="modal-label-nftName" style="cursor: pointer; margin-left: 1rem;">명품이름</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-description" type="checkbox" v-model="checkref.description">
                        <label class="front__text-hover" for="modal-label-description" style="cursor: pointer; margin-left: 1rem;">명품설명</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-dateOfManufacture" type="checkbox" v-model="checkref.dateOfManufacture">
                        <label class="front__text-hover" for="modal-label-dateOfManufacture" style="cursor: pointer; margin-left: 1rem;">제조날짜</label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col p-3 border bg-light"><input id="modal-label-brandName" type="checkbox" v-model="checkref.brandName">
                        <label class="front__text-hover" for="modal-label-brandName" style="cursor: pointer; margin-left: 1rem;">브랜드명</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-countryOfManufacture" type="checkbox" v-model="checkref.countryOfManufacture">
                        <label class="front__text-hover" for="modal-label-countryOfManufacture" style="cursor: pointer; margin-left: 1rem;">제조국가</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-productClassification" type="checkbox" v-model="checkref.productClassification">
                        <label class="front__text-hover" for="modal-label-productClassification" style="cursor: pointer; margin-left: 1rem;">상품분류</label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col p-3 border bg-light"><input id="modal-label-material" type="checkbox" v-model="checkref.material">
                        <label class="front__text-hover" for="modal-label-material" style="cursor: pointer; margin-left: 1rem;">소재</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-productColor" type="checkbox" v-model="checkref.productColor">
                        <label class="front__text-hover" for="modal-label-productColor" style="cursor: pointer; margin-left: 1rem;">색상</label>
                      </div>
                      <div class="col p-3 border bg-light"><input id="modal-label-productPrice" type="checkbox" v-model="checkref.productPrice">
                        <label class="front__text-hover" for="modal-label-productPrice" style="cursor: pointer; margin-left: 1rem;">가격</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color: #2dce89; border: none;">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!----------------------------- MODAL END------------------------------------------->

      </div>
    </div>
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
import Sidebar from '@/components/Sidebar.vue'
import router from '@/router';
import { useRouter } from 'vue-router';

export default {
  name : 'NftAllCreate',
  components: {
    Sidebar,
  },
  setup() {
    const checkref = ref({
      order: true,
      image: true,
      serialNumber: true,
      nftName: true,
      description: true,
      dateOfManufacture: true,
      brandName: true,
      countryOfManufacture: true,
      productClassification: true,
      material: true,
      productColor: true,
      productPrice: true,
    });

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
            workbook.Sheets[sheetName].B1.w = "nftName";
            workbook.Sheets[sheetName].C1.w = "description";
            workbook.Sheets[sheetName].D1.w = "dateOfManufacture";
            workbook.Sheets[sheetName].E1.w = "brandName";
            workbook.Sheets[sheetName].F1.w = "countryOfManufacture";
            workbook.Sheets[sheetName].G1.w = "productClassification";
            workbook.Sheets[sheetName].H1.w = "material";
            workbook.Sheets[sheetName].I1.w = "productColor";
            workbook.Sheets[sheetName].J1.w = "productPrice";

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
          !element.nftName ||
          !element.description ||
          !element.dateOfManufacture ||
          !element.brandName ||
          !element.countryOfManufacture ||
          !element.productClassification ||
          !element.material ||
          !element.productColor ||
          !element.productPrice) continue;

        const metadata = {
          serialNumber: element.serialNumber,
          nftName: element.nftName,
          description: element.description,
          dateOfManufacture: element.dateOfManufacture,
          brandName: element.brandName,
          countryOfManufacture: element.countryOfManufacture,
          productClassification: element.productClassification,
          material: element.material,
          productColor: element.productColor,
          productPrice: element.productPrice,
          image: element.image,
        }

        console.log(metadata);

        // api/v0/add
        const response = await ipfs.add(JSON.stringify(metadata));
        const ipfsHash = response.path;

        excelData.value.splice(i, 1);

        await publishToken(ipfsHash);

        // NFT 정상적으로 발급되는지 확인하는 코드
        // const result = await getMetadataFromIpfs(ipfsHash);
        // console.log(result);
      }
    }

    const router = useRouter()
    // nft 한 개 발급으로 
    const goNftcreate = () => {
      router.push({name: 'NftCreate'})
    }

    return {
      onMounted,
      checkref,
      changeExcelFile,
      excelData,
      changeImageFile,
      createNFT,
      goNftcreate,
    }
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>
.content_outside_box {
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #e9ecef;
  text-align: left;
  box-sizing: border-box;
  width: 100%;
  margin-left: auto;
  /* margin-top: 0.5rem!important; */
  /* margin-top: -4.5rem!important; */
  padding-left: 70px!important;
  padding-right: 70px!important;
  position: relative;
  bottom: 150px;
}

.notice-title {
  width: 50%; 
  text-overflow: ellipsis; 
  overflow: hidden; 
  white-space: wrap;
  position: relative;
  text-align: left;
  left: 20%;
}

.container-fluid {
  position: relative;
  right: 4px;
}

.head_title {
  color: white;
  position:absolute; 
  margin-left:100px; 
  margin-top: 50px;
  font-size: 2rem;
}

.card-header {
  margin-bottom: 0;
  padding: 1.25rem 1.5rem;
  border-bottom: 1px solid rgba(0, 0, 0, .05);
  background-color: #fff !important;
  text-align: left;
  font-size: 1.0625rem;
}

.card-header:first-child {
  border-radius: calc(.375rem - 1px) calc(.375rem - 1px) 0 0;
}

h2,
h3 {
  margin-top: 0;
  margin-bottom: .5rem;
}

h3,
.h3 {
  font-size: 1.0625rem;
}

h2,
h3,
.h2,
.h3 {
  font-family: inherit;
  font-weight: 600;
  line-height: 1.5;
  margin-bottom: .5rem;
  color: #32325d;
}

.sub__title {
  font-size: 1rem;
  /* font-family: 'ChosunSm'; */
}

.sub__content {
  font-size: 0.8rem;
}

.next__icon:hover {
  cursor: pointer;
}

.chooseBtnPosition {
  display: flex;
  justify-content: flex-end;
}

.chooseShowBtn {
  background-color: #fff !important;
  height: 40px;
  color: #333333 !important;
  border-color: transparent;
  border: 1px solid transparent !important;
  box-shadow: 1px 1px 2px 2px #ececf0;
  /* background-image: linear-gradient( 135deg, #90F7EC 10%, #32CCBC 100%); */
}

.chooseShowBtn:hover {
  background-color: #32CCBC !important;
  color: white;
}
</style>
