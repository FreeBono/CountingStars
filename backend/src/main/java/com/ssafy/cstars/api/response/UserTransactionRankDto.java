package com.ssafy.cstars.api.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class UserTransactionRankDto {
    private String username;
    private String address;
    private Long countTransaction;

    @QueryProjection
    public UserTransactionRankDto() {

    }

    @QueryProjection
    public UserTransactionRankDto(String username, String address, Long countTransaction) {
        this.username = username;
        this.address = address;
        this.countTransaction = countTransaction;
    }


}
