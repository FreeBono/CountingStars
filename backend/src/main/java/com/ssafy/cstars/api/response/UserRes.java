package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRes")
public class UserRes {
    @ApiModelProperty(name = "User Id", notes = "Unique identifier of the Contact")
    Long userId;
    @ApiModelProperty(name = "User Address")
    String address;
    @ApiModelProperty(name = "User Name")
    String username;

    public static UserRes of(User user) {
        UserRes res = new UserRes();

        res.setUserId(user.getId());
        res.setAddress(user.getAddress());
        res.setUsername(user.getUsername());

        return res;
    }
}
