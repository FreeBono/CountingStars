package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel("UserTransactionReq")
public class UserTransactionPostReq {

    @ApiModelProperty(name = "User id")
    Long userId;

    @ApiModelProperty(name = "NFT 거래 번호")
    Integer count;

}
