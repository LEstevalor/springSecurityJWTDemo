package com.it.controller;

import com.it.domain.ResponseResult;
import com.it.domain.User;
import com.it.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    //退出登录
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
