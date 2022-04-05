package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.IpfsPostReq;
import com.ssafy.cstars.api.response.IpfsRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IpfsService {
    IpfsRes addFileToIpfs(IpfsPostReq ipfsInfo, MultipartFile imageFile) throws IOException, ClassNotFoundException;
    IpfsRes multiAddFileToIpfs(MultipartFile excelFile) throws IOException, ClassNotFoundException;
    IpfsRes getMetadataFromCid(String cid);
}
