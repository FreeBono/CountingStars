package com.ssafy.cstars.api.controller;

import com.ssafy.cstars.api.request.IpfsPostReq;
import com.ssafy.cstars.api.response.BaseResponseBody;
import com.ssafy.cstars.api.response.IpfsRes;
import com.ssafy.cstars.service.IpfsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ipfs 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "ipfs API", tags = {"ipfs"})
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/ipfs")
@Controller
public class IpfsController {

    @Autowired
    IpfsService ipfsService;

    @PostMapping()
    @ApiOperation(value = "IPFS 등록", notes = "<strong>IPFS</strong>을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<IpfsRes> addFileToIpfs(@RequestPart(value = "metadata") @ApiParam(value = "IPFS 정보", required = true) IpfsPostReq ipfsInfo,
                                                          @RequestPart(value = "image")  @ApiParam(value = "IPFS 이미지", required = true) MultipartFile imageFile)  throws IOException, ClassNotFoundException{
        // 배포할 privateKey or 지갑 주소도 받아야 함

        IpfsRes ipfsRes = ipfsService.addFileToIpfs(ipfsInfo, imageFile);

        if(ipfsRes != null)
            return ResponseEntity.status(200).body(ipfsRes);
        else
            return ResponseEntity.status(500).body(null);

//        if("200".equals(result[0]))
//            return ResponseEntity.status(200).body(BaseResponseBody.of(200, result[1]));
//        else
//            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Connection Fail"));
    }

    @GetMapping("/{cid}")
    @ApiOperation(value = "IPFS 등록", notes = "<strong>IPFS</strong>을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<IpfsRes> getMetadataFromCid(@PathVariable(name = "cid") @ApiParam(value = "cid", required = true) String cid) {
        IpfsRes ipfsRes = ipfsService.getMetadataFromCid(cid);

        if(ipfsRes != null)
            return ResponseEntity.status(200).body(ipfsRes);
        else
            return ResponseEntity.status(500).body(null);
    }
}
