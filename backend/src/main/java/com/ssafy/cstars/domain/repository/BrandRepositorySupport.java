package com.ssafy.cstars.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.cstars.domain.entity.Brand;
import com.ssafy.cstars.domain.entity.QBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class BrandRepositorySupport {

    @Autowired
    JPAQueryFactory jpqQueryFactory;
    QBrand qBrand = QBrand.brand;

    public Page<Brand> findAll(Pageable pageable){
        QueryResults<Brand> brandes = jpqQueryFactory
                .select(qBrand)
                .from(qBrand)
                .orderBy(qBrand.id.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        if(brandes == null) return Page.empty();

        return new PageImpl<Brand>(brandes.getResults(), pageable, brandes.getTotal());
    }


}
