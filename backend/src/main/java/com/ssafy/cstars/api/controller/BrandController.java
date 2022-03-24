package com.ssafy.cstars.api.controller;

import com.ssafy.cstars.api.request.BrandDeleteReq;
import com.ssafy.cstars.api.request.BrandPostReq;
import com.ssafy.cstars.api.response.BaseResponseBody;
import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.service.BrandService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@Api(value = "브랜드 API", tags = {"Brand"})
@RestController
@RequestMapping("/api/v1/brand")
@Controller
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping()
    @ApiOperation(value = "브랜드 리스트 조회", notes = "<strong>브랜드</strong> 전체 목록을 조회한다")
    @ApiResponses({
            @ApiResponse(code =200 , message = "SUCCESS", response = BrandRes.class),
            @ApiResponse(code =404 , message = "NOT FOUND", response = BrandRes.class),
            @ApiResponse(code =500 , message = "SERVER ERROR", response = BrandRes.class),
    })
        public ResponseEntity<List<BrandRes>> getBrandList(){

            List<BrandRes> brands = brandService.GetBrandList();

            if(brands != null){
                return ResponseEntity.status(200).body(brands);
            }else{
                return ResponseEntity.status(500).body(null);
            }
    }

    @PostMapping()
    @ApiOperation(value = "브랜드 등록", notes = "<strong>브랜드</strong> 등록한다")
    @ApiResponses({
            @ApiResponse(code =200 , message = "SUCCESS", response = BrandRes.class),
            @ApiResponse(code =401, message = "ACCESS DENIED", response = BrandRes.class),
            @ApiResponse(code =500 , message = "SERVER ERROR", response = BrandRes.class),
    })
    public ResponseEntity<BaseResponseBody> createBrand(@RequestBody @ApiParam(value = "브랜드 등록", required = true) BrandPostReq brandInfo){

        int statusCode = brandService.createBrand(brandInfo);

        return createResponseEntityToStatusCode(statusCode);

    }

    @DeleteMapping
    @ApiOperation(value = "브랜드 삭제", notes = "<strong>브랜드</strong> 삭제한다 ")
    @ApiResponses({
            @ApiResponse(code =200 , message = "SUCCESS", response = BrandRes.class),
            @ApiResponse(code =401 , message = "ACCESS DENIED", response = BrandRes.class),
            @ApiResponse(code =500 , message = "SERVER ERROR", response = BrandRes.class),
    })
    public ResponseEntity<BaseResponseBody> deleteBrand(@RequestBody @ApiParam(value = "브랜드 삭제", required = true) BrandDeleteReq brandInfo){

        int statusCode = brandService.deleteBrand(brandInfo);

        return createResponseEntityToStatusCode(statusCode);

    }




    private ResponseEntity<BaseResponseBody> createResponseEntityToStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
            case 401:
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "ACCESS DENIED"));
            default:
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }
}
