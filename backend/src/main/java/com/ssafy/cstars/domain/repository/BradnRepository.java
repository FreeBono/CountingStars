package com.ssafy.cstars.domain.repository;

import com.ssafy.cstars.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BradnRepository extends JpaRepository<Brand, Long> {
    List<Brand> findAll();

}
