package com.ssafy.cstars.domain.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Admin extends BaseEntity {
    String email; //  게시글 제목
    String password; // 게시글 내용
}
