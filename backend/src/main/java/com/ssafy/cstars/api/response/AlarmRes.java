package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.Alarm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AlarmResponse")
public class AlarmRes {


    @ApiModelProperty(name = "product name")
    String productName;

    @ApiModelProperty(name = "alarm endDate")
    String registerDate;

    @ApiModelProperty(name = "alarm receiver")
    String receiver;

    @ApiModelProperty(name = "alarm sender")
    String sender;

    @ApiModelProperty(name = "alarm status")
    boolean status;

    @ApiModelProperty(name = "alarm brandName")
    String brand;

    public static AlarmRes of(Alarm alarm){
        AlarmRes res = new AlarmRes();

        res.setProductName(alarm.getProductName());
        res.setRegisterDate(alarm.getRegisterDate());
        res.setReceiver(alarm.getReceiver());
        res.setSender(alarm.getSender());
        res.setStatus(alarm.isStatus());
        res.setBrand(alarm.getBrand());

        return res;
    }


}
