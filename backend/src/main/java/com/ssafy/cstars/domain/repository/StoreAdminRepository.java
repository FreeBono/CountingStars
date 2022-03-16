package com.ssafy.cstars.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.cstars.domain.entity.StoreAdmin;

@Repository
public interface StoreAdminRepository extends JpaRepository<StoreAdmin, Long>{
    
    Optional<StoreAdmin> findByStore(String store);

    Optional<StoreAdmin> findByEmail(String email);

    Boolean existsByStore(String store);

    Boolean existsByEmail(String email);
}
