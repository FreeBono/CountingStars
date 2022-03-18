package com.ssafy.cstars.domain.repository;

import java.util.Optional;

import com.ssafy.cstars.domain.entity.BrandAdmin;
import com.ssafy.cstars.domain.entity.RefreshTokenBrandAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenBrandAdminRepository extends JpaRepository<RefreshTokenBrandAdmin, Long> {
  Optional<RefreshTokenBrandAdmin> findByToken(String token);

  Boolean existsByToken(String token);

  @Modifying
  int deleteByBrandAdmin(BrandAdmin brandAdmin);
}
