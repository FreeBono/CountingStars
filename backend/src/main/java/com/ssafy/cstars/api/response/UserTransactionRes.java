package com.ssafy.cstars.api.response;


import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.domain.entity.UserTransaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("UserTransactionResponse")
public class UserTransactionRes {

    @ApiModelProperty(name = "transaction date")
    LocalDateTime date;

    @ApiModelProperty(name = "transaction count")
    Integer TCount;


    public static UserTransactionRes of(UserTransaction trans) {
        UserTransactionRes res = new UserTransactionRes();

        res.setDate(trans.getRegisterDate());
        res.setTCount(trans.getTCount());
        return res;

    }
}
