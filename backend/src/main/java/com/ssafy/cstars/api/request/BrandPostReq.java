package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("BrandPostReq")
public class BrandPostReq {

    @ApiModelProperty(name = "브랜드 이름")
    String name;

    @ApiModelProperty(name = "계약 만료 일자")
    Date endDate;

    @ApiModelProperty(name = "브랜드 지갑 주소")
    String address;

}
