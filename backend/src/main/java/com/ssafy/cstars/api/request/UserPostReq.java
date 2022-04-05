package com.ssafy.cstars.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPostReq")
public class UserPostReq {
    @ApiModelProperty(name = "유저 이메일")
    String email;
}
