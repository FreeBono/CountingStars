package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.BrandDeleteReq;
import com.ssafy.cstars.api.request.BrandPostReq;
import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.domain.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BrandService {

    Page<Brand> GetBrandList(Pageable pageable);
    Brand getBrand(Long brandId);
    int createBrand(BrandPostReq BrandInfo, MultipartFile imageFile) throws IOException, ClassNotFoundException;
    int deleteBrand(BrandDeleteReq BrandInfo);
}
