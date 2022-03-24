package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPutReq")
public class UserPutReq {
    @ApiModelProperty(name="유저 번호")
    Long userId;
    @ApiModelProperty(name="지갑 주소")
    String wallet;
}
