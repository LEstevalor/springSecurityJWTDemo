package com.it.controller;

import com.it.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/hh")
    public String testVisit() {
        return "YES";
    }

    @RequestMapping("/xx")
    @PreAuthorize("hasAnyAuthority('admin')")   //hasAnyAuthority是个方法，会判断是否具有admin权限
    public String test() {
        return "springboot可访问";
    }
}
