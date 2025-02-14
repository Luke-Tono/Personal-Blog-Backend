package com.luke.service;

import com.luke.domain.ResponseResult;
import com.luke.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}