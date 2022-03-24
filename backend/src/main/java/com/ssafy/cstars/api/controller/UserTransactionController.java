package com.ssafy.cstars.api.controller;

import com.ssafy.cstars.api.request.UserReq;
import com.ssafy.cstars.api.request.UserTransactionPostReq;
import com.ssafy.cstars.api.response.BaseResponseBody;
import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.api.response.UserTransactionRes;
import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.service.UserTransactionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "유저 거래 API", tags = {"UserTransaction"})
@RestController
@RequestMapping("api/v1/userTransaction")
@Controller
public class UserTransactionController {

    @Autowired
    UserTransactionService userTransactionService;

    @GetMapping()
    @ApiOperation(value="NFT 거래 통계", notes = "날짜 별 <strong>NFT거래</strong> 조회합니다. ")
    @ApiResponses({
            @ApiResponse(code =200 , message = "SUCCESS", response = BrandRes.class),
            @ApiResponse(code =404 , message = "NOT FOUND", response = BrandRes.class),
            @ApiResponse(code =500 , message = "SERVER ERROR", response = BrandRes.class),
    })
    public ResponseEntity<List<UserTransactionRes>> getUserTransaction(@RequestBody @ApiParam(value = "user 거래 조회", required = true) UserReq user){


        List<UserTransactionRes> list = userTransactionService.getUserTransactionList(user);


    if(list != null){
        return ResponseEntity.status(200).body(list);
    }else{
        return ResponseEntity.status(500).body(null);
    }
    }

    @PostMapping()
    @ApiOperation(value = "NFT 거래 등록", notes = "날짜 별 <strong>NFT거래</strong> 등록합니다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> createUserTransaction(@RequestBody @ApiParam(value = "NFT거래등록", required = true) UserTransactionPostReq userInfo){

        int statusCode = userTransactionService.createUserTransaction(userInfo);

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
