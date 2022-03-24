package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.BrandDeleteReq;
import com.ssafy.cstars.api.request.BrandPostReq;
import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.domain.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<BrandRes>  GetBrandList();
    Brand getBrand(Long brandId);
    int createBrand(BrandPostReq BrandInfo);
    int deleteBrand(BrandDeleteReq BrandInfo);
}
