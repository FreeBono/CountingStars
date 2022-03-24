package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.UserTransactionPostReq;
import com.ssafy.cstars.api.response.UserTransactionRes;
import java.util.List;


public interface UserTransactionService {

    int createUserTransaction(UserTransactionPostReq userTransactionReq);

    List<UserTransactionRes> getUserTransactionList(Long userId);


}
