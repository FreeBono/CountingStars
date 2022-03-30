package com.ssafy.cstars.api.response;

import com.ssafy.cstars.domain.entity.UserTransaction;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("UserTransactionRankRes")
public class UserTransactionRankRes {

    public static Page<UserTransactionRes> of(Page<UserTransaction> rank) {
        List<UserTransactionRes> list = new ArrayList<>();

        Pageable pageable = rank.getPageable();
        long total = rank.getTotalElements();

        rank.forEach(userTransaction -> {
            list.add(UserTransactionRes.of(userTransaction));
        });

        return new PageImpl<UserTransactionRes>(list, pageable, total);
    }
}
