package com.ssafy.cstars.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("IpfsResponse")
public class IpfsRes {
    @ApiModelProperty(name="IPFS name")
    String name;
    @ApiModelProperty(name="IPFS 설명")
    String description;
    @ApiModelProperty(name="IPFS 일련번호")
    String serialNumber;
    @ApiModelProperty(name="IPFS 제작년도")
    String dateOfManufacture;
    @ApiModelProperty(name="IPFS 브랜드네임")
    String brandName;
    @ApiModelProperty(name="IPFS 제조국가")
    String countryOfManufacture;
    @ApiModelProperty(name="IPFS 제품분류")
    String productClassification;
    @ApiModelProperty(name="IPFS 제품상세분류")
    String detailProductClassification;
    @ApiModelProperty(name="IPFS 소재")
    String material;
    @ApiModelProperty(name="IPFS 제품색상")
    String productColor;
    @ApiModelProperty(name="IPFS 제품가격")
    String productPrice;
    @ApiModelProperty(name="IPFS 이미지")
    String image;
}
