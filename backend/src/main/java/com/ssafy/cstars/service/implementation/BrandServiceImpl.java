package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.api.request.BrandDeleteReq;
import com.ssafy.cstars.api.request.BrandPostReq;

import com.ssafy.cstars.api.response.BrandRes;
import com.ssafy.cstars.domain.entity.Brand;
import com.ssafy.cstars.domain.entity.BrandAdmin;
import com.ssafy.cstars.domain.repository.BradnRepository;
import com.ssafy.cstars.domain.repository.BrandAdminRepository;
import com.ssafy.cstars.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BrandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    BradnRepository brandRepository;
    @Autowired
    BrandAdminRepository brandAdminRepository;


    @Override
    public List<BrandRes> GetBrandList() {

        List<Brand> brands = brandRepository.findAll();

        List<BrandRes> res = new ArrayList<>();

        brands.forEach( brand -> {

            List<BrandAdmin> byName = brandAdminRepository.findByName(brand.getName());
            List<String> adminEmail = new ArrayList<>();

            byName.forEach( name -> {
                adminEmail.add(name.getEmail());
            });
            res.add(BrandRes.of(brand, adminEmail));
        });

        return res;
    }

    @Override
    public Brand getBrand(Long brandId){
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    public int createBrand(BrandPostReq brandInfo){

        Brand brand = Brand.builder()
                .name(brandInfo.getName())
                .endDate(brandInfo.getEndDate())
                .wallet(brandInfo.getWallet())
                .imageUrl(brandInfo.getImageUrl())
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
