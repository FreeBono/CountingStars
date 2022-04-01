import XLSX from "xlsx";

export default async function encodeImageFileAsURL(excelFile) {
  const reader = new FileReader(); //FileReader를 생성한다.
  let tmpResult = {};

  //성공적으로 읽기 동작이 완료된 경우 실행되는 이벤트 핸들러를 설정한다.
  reader.onload = function(e) {
      let data = reader.result;
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


     
    //  var workbook = XLSX.read(data, {type: 'binary'});
     
    //  //엑셀파일의 시트 정보를 읽어서 JSON 형태로 변환한다.
    //  workbook.SheetNames.forEach(function(item, index, array) {
    //      EXCEL_JSON = XLSX.utils.sheet_to_json(workbook.Sheets[item]);
    //  }); //end. forEach
     
  }; //end onload
  
  //파일객체를 읽는다. 완료되면 원시 이진 데이터가 문자열로 포함됨.

  reader.readAsBinaryString(excelFile);
//   const test = reader.readAsBinaryString(excelFile);

//   console.log(test);
}