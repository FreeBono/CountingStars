package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BrandDeleteReq")
public class BrandDeleteReq {
    @ApiModelProperty(name = "브랜드 ID")
    Long brandId;

}
