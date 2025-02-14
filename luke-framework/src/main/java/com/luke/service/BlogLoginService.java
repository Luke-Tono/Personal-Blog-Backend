package com.luke.service;

import com.luke.domain.ResponseResult;
import com.luke.domain.entity.User;

public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
