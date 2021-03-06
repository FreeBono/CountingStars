package com.ssafy.cstars.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.cstars.domain.entity.BrandAdmin;

@Repository
public interface BrandAdminRepository extends JpaRepository<BrandAdmin, Long> {
    Optional<BrandAdmin> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    List<BrandAdmin> findByName(String name);


}
