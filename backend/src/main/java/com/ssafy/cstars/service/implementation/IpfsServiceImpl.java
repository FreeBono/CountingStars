package com.ssafy.cstars.service.implementation;

import com.google.gson.Gson;
import com.ssafy.cstars.api.request.IpfsPostReq;
import com.ssafy.cstars.api.response.IpfsRes;
import com.ssafy.cstars.service.IpfsService;
import com.ssafy.cstars.util.IpfsUtil;
import com.ssafy.cstars.util.ReadExcelUtil;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service("IpfsService")
public class IpfsServiceImpl implements IpfsService{

    @Override
    public IpfsRes addFileToIpfs(IpfsPostReq ipfsInfo, MultipartFile imageFile) throws IOException, ClassNotFoundException {
        // ipfs 연결, docker ip4 주소와 연결
        IPFS ipfs = new IPFS("/ip4/172.17.0.1/tcp/5001");

        // MultipartFile 이미지파일 -> 파일로 바꾸기
        NamedStreamable.FileWrapper image = new NamedStreamable.FileWrapper(IpfsUtil.multipartFileToFile(imageFile));
        // ipfs 추가
        MerkleNode addResult = ipfs.add(image).get(0);
        // pin id 받기
        String pinId = addResult.hash.toBase58();
        System.out.println(pinId);

        // ipfs 이미지 pid 담기
        ipfsInfo.setImage(pinId);

        // json 문자열로 바꾸기
        Gson gson = new Gson();
        String ipfsInfoGson = gson.toJson(ipfsInfo);

        // 객체를 byte[]로 바꿔서 파일 새로 만들기
        NamedStreamable.ByteArrayWrapper file
                = new NamedStreamable.ByteArrayWrapper(ipfsInfo.getSerialNumber() + ".json", IpfsUtil.convertObjectToBytes(ipfsInfoGson));
        // ipfs 추가
        addResult = ipfs.add(file).get(0);
        // pin id 받기
        pinId = addResult.hash.toBase58();
        System.out.println(pinId);


        // -----------------------------get 테스트-------------------------------------------------------

        // -----------------------------get 테스트-------------------------------------------------------


        // ipfs에서 찾아오기
        byte[] ipfsCat = ipfs.cat(Multihash.fromBase58(pinId));
        // byte[] -> Object
        Object ipfsObject = IpfsUtil.convertBytesToObject(ipfsCat);
        // Object(Json String) -> IpfsRes
        IpfsRes ipfsRes = gson.fromJson(ipfsObject.toString(), IpfsRes.class);

        // 이미지 가져오기
        ipfsCat = ipfs.cat(Multihash.fromBase58(ipfsRes.getImage()));
        // byte[] -> base64 인코딩 -> image에 담기
        ipfsRes.setImage( new Base64().encodeToString(ipfsCat) );

        return ipfsRes;

//        try {
//
//        }catch (Exception e) {
//            System.out.println("IPFS 발행 및 NFT 발행 에러");
//            return null;
//        }
    }

    @Override
    public IpfsRes multiAddFileToIpfs(MultipartFile excelFile) throws IOException, ClassNotFoundException {
        ReadExcelUtil.readExcelFile(excelFile);

        return null;
    }

    @Override
    public IpfsRes getMetadataFromCid(String cid) {
        try {
            // ipfs 연결, docker ip4 주소와 연결
            IPFS ipfs = new IPFS("/ip4/172.17.0.1/tcp/5001");

            Gson gson = new Gson();
            // ipfs에서 찾아오기
            byte[] ipfsCat = ipfs.cat(Multihash.fromBase58(cid));
            // byte[] -> Object
            Object ipfsObject = IpfsUtil.convertBytesToObject(ipfsCat);
            // Object(Json String) -> IpfsRes
            IpfsRes ipfsRes = gson.fromJson(ipfsObject.toString(), IpfsRes.class);

            System.out.println(ipfsRes);

            // 이미지 가져오기
            ipfsCat = ipfs.cat(Multihash.fromBase58(ipfsRes.getImage()));
            // byte[] -> base64 인코딩 -> image에 담기
            ipfsRes.setImage( new Base64().encodeToString(ipfsCat) );

            return ipfsRes;

        }catch (Exception e) {
            System.out.println("서버 에러 : 연결 실패 또는 해당 CID를 찾지 못함");
            return null;
        }
    }

}
