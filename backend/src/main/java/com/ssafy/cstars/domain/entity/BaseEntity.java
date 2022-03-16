package com.ssafy.cstars.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    Long id = null; // null, DB Auto Increment

    @CreatedDate
    private LocalDateTime registerDate;

    @LastModifiedBy
    private LocalDateTime modifyDate; // 수정한 날짜

    public void setModifiedDate() {
        this.modifyDate = LocalDateTime.now();
    }
}
