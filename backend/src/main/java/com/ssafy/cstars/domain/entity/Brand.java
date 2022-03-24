package com.ssafy.cstars.domain.entity;

import jdk.nashorn.internal.ir.BaseNode;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Brand extends BaseEntity {

    @Column(name="name", unique = true)
    String name;

    @Column(name="end_date")
    Date endDate;

    @Column(name="address")
    String address;

    @Column(name = "image_url")
    String imageUrl;


}
