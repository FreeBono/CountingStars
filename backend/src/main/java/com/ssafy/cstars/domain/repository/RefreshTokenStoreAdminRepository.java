package com.ssafy.cstars.domain.repository;

import java.util.Optional;

import com.ssafy.cstars.domain.entity.RefreshTokenStoreAdmin;
import com.ssafy.cstars.domain.entity.StoreAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenStoreAdminRepository extends JpaRepository<RefreshTokenStoreAdmin, Long> {
  Optional<RefreshTokenStoreAdmin> findByToken(String token);

  Boolean existsByToken(String token);

  @Modifying
  int deleteByStoreAdmin(StoreAdmin storeAdmin);
}
