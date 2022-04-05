package com.ssafy.cstars.api.controller;

import com.ssafy.cstars.api.request.UserPostReq;
import com.ssafy.cstars.api.request.UserPutReq;
import com.ssafy.cstars.api.response.BaseResponseBody;
import com.ssafy.cstars.api.response.UserRes;
import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("api/v1/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "유저 지갑 조회", notes = "<strong>유저 지갑</strong>을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserRes> getAddress(@RequestBody @ApiParam(value = "유저 이메일", required = true) UserPostReq userInfo) {

        User user = userService.getAddress(userInfo);

        if (user != null) {
            return ResponseEntity.status(200).body(UserRes.of(user));
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping()
    @ApiOperation(value = "유저 수정(지갑등록)", notes = "<strong>유저(지갑등록)</strong>을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> modifyUser(@RequestBody @ApiParam(value = "유저 번호", required = true) UserPutReq userInfo) {
        int statusCode = userService.modifyUser(userInfo);
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
