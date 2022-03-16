package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.api.request.BrandDeleteReq;
import com.ssafy.cstars.api.request.BrandPostReq;
import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.domain.entity.Brand;
import com.ssafy.cstars.domain.repository.BradnRepository;
import com.ssafy.cstars.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BrandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    BradnRepository brandRepository;


    @Override
    public Page<Brand> GetBrandList(Pageable pageable) {

        Page<Brand> brands = brandRepository.findAll(pageable);
        if(brands.isEmpty()) return null;
        return brands;
    }

    @Override
    public Brand getBrand(Long brandId){
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    public int createBrand(BrandPostReq brandInfo){

        Brand brand = Brand.builder()
                .name(brandInfo.getName())
                .build();

        if(brand == null) return 500;

        brandRepository.save(brand);

        return 200;
    }

    @Override
    public int deleteBrand(BrandDeleteReq brandInfo){

        Brand brand = getBrand(brandInfo.getBrandId());

        if(brand == null) return 500;

        brandRepository.delete(brand);

        return 200;
    }

}
