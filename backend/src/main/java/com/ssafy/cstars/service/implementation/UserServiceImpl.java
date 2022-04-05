package com.ssafy.cstars.service.implementation;

import com.ssafy.cstars.api.request.UserPostReq;
import com.ssafy.cstars.api.request.UserPutReq;
import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.domain.repository.UserRepository;
import com.ssafy.cstars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getAddress(UserPostReq userInfo) {
        return userRepository.findByEmail(userInfo.getEmail()).orElse(null);
    }

    @Override
    public int modifyUser(UserPutReq userInfo) {

        User user = getUser(userInfo.getUserId());

        if(user == null) return 500;

        user.modify(userInfo.getWallet());
        userRepository.save(user);

        return 200;
    }

}
