package com.ssafy.cstars.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.cstars.api.request.IpfsPostReq;
import com.ssafy.cstars.api.response.BaseResponseBody;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.swagger.annotations.*;
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

    @PostMapping()
    @ApiOperation(value = "IPFS 등록", notes = "<strong>IPFS</strong>을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SUCCESS", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "ACCESS DENIED", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "FAIL", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> addFileToIpfs(@RequestPart(value = "metadata") @ApiParam(value = "IPFS 정보", required = true) IpfsPostReq ipfsInfo,
                                                          @RequestPart(value = "image")  @ApiParam(value = "IPFS 이미지", required = true) MultipartFile imageFile) throws IOException, ClassNotFoundException {

        System.out.println("테스트 잘 넘어오나???");
//        System.out.println(getServerIp());
//        System.out.println(ipfsInfo);

        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

        NamedStreamable.FileWrapper image = new NamedStreamable.FileWrapper(multipartFileToFile(imageFile));
        MerkleNode addResult = ipfs.add(image).get(0);
        String pinId = addResult.hash.toBase58();
        System.out.println(pinId);

        ipfsInfo.setImageUrl(pinId);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(ipfsInfo);

        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(ipfsInfo.getSerialNumber() + ".json", convertObjectToBytes(jsonStr));
        addResult = ipfs.add(file).get(0);

        // get

        byte[] ipfsCat = ipfs.cat(addResult.hash);
        Object ipfsObject = convertBytesToObject(ipfsCat);

        System.out.println(ipfsObject);

//        IPFSCluster ipfsCluster = new IPFSCluster("127.0.0.1", 9094);
//        ipfsCluster.pins.add(pinId);
//
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, addResult.hash.toBase58()));
    }

    /*
     * 객체에서 byte[]로 변환
     */
    public static byte[] convertObjectToBytes(Object obj) throws IOException {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        }
    }

    /*
     * byte[]에서 객체로 변환
     */
    public static Object convertBytesToObject(byte[] bytes)
            throws IOException, ClassNotFoundException {
        InputStream is = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        }
    }

    /**
     * multipartFile을 File로 변환한다.
     */
    public static File multipartFileToFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        return convFile;

    }

    private String getServerIp() {
        InetAddress local = null;
        try {
            local = InetAddress.getLocalHost();
        }
        catch ( UnknownHostException e ) {
            e.printStackTrace();
        }
        if( local == null ) {
            return "";
        }
        else {
            String ip = local.getHostAddress();
            return ip;
        }

    }
}
