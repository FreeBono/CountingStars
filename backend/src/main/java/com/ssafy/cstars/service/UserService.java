package com.ssafy.cstars.service;

import com.ssafy.cstars.api.request.UserPutReq;
import com.ssafy.cstars.domain.entity.User;

public interface UserService {
    User getUser(Long userId);
    int modifyUser(UserPutReq userInfo);
}
