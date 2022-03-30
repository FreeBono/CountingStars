package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.api.request.UserTransactionPostReq;
import com.ssafy.cstars.api.response.UserTransactionRes;
import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.domain.entity.UserTransaction;
import com.ssafy.cstars.domain.repository.UserRepository;
import com.ssafy.cstars.domain.repository.UserTransactionRepository;
import com.ssafy.cstars.domain.repository.UserTransactionRepositorySupport;
import com.ssafy.cstars.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserTransactionService")
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTransactionRepositorySupport userTransactionRepositorySupport;

    private User getUser(Long userId) {

        return userRepository.findById(userId).orElse(null);

    }


    @Override
    public int createUserTransaction(UserTransactionPostReq userTransactionReq) {

        User user = getUser(userTransactionReq.getUserId());

        UserTransaction userTransaction = UserTransaction.builder()
            .user(user)
            .count(userTransactionReq.getCount())
            .build();

        if(userTransaction == null) return 500;

        userTransactionRepository.save(userTransaction);

        return 200;
    }


    @Override
    public List<UserTransactionRes> getUserTransactionList(Long userId) {

        List<UserTransaction> byUser = userTransactionRepository.findByUserId(userId);

        List<UserTransactionRes> res = new ArrayList<>();

        byUser.forEach( trans -> {

            res.add(UserTransactionRes.of(trans));

        });


        return res;
    }

    @Override
    public Page<String> getRank(Pageable pageable) {
        Page<String> rank = userTransactionRepositorySupport.rank(pageable);

        if(rank.isEmpty()) return null;

        return rank;
    }


}
