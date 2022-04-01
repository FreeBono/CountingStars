package com.ssafy.cstars.service;

import com.querydsl.core.Tuple;
import com.ssafy.cstars.api.request.UserTransactionPostReq;
import com.ssafy.cstars.api.response.UserTransactionRankDto;
import com.ssafy.cstars.api.response.UserTransactionRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserTransactionService {

    int createUserTransaction(UserTransactionPostReq userTransactionReq);

    List<UserTransactionRes> getUserTransactionList(Long userId);


    Page<UserTransactionRankDto> getRank(Pageable pageable);
}
