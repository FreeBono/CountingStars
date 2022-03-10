package com.ssafy.cstars.domain.repository;

import com.ssafy.cstars.domain.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 공지사항 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findAll(Pageable pageable);
    Optional<Notice> findById(Long id);
}
