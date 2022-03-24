package com.ssafy.cstars.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    Long id = null;

    @Column(name="name", unique = true)
    String name;

    @Column(name="end_date")
    Date endDate;

    @Column(name="wallet")
    String wallet;

    @Column(name = "image_url")
    String imageUrl;


}
