package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.Brand;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ApiModel("BrandListResponse")
public class BrandListRes {

    public static Page<BrandRes> of(Page<Brand> brandList){
        List<BrandRes> list = new ArrayList<>();

        Pageable pageable = brandList.getPageable();
        long total = brandList.getTotalElements();

        brandList.forEach(brand -> {
            list.add(BrandRes.of(brand));
        });

        return new PageImpl<BrandRes>(list, pageable, total);

    }

}
