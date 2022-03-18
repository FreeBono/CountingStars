package com.ssafy.cstars.domain.repository;

import com.ssafy.cstars.domain.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 관리자모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Page<Admin> findAll(Pageable pageable);
    Optional<Admin> findById(Long id);
    Optional<Admin> findByEmail(String email);
    Boolean existsByEmail(String email);
}
