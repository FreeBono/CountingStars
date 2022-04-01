package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * IPFS API ([Post] /ipfs/) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("IPFSPostReq")
public class IpfsPostReq implements Serializable {
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
    @ApiModelProperty(name="IPFS 이미지주소")
    String image;
}
