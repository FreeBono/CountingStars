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
@ApiModel("UserReq")
public class UserReq {

    @ApiModelProperty(name = "user id")
    Long userId;

}
