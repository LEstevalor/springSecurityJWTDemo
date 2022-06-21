package com.it.service;

import com.it.domain.ResponseResult;
import com.it.domain.User;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
