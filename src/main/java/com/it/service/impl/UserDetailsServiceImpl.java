package com.it.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.it.domain.LoginUser;
import com.it.domain.User;
import com.it.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);  //eq查询  select * from 表 where user_name = ?
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 查询对应的权限信息
        List<String> list = new ArrayList<>(Arrays.asList("test","admin","user"));   //我们先写死权限信息，后续再来优化


        //把数据封装成UserDetails返回 ——> 创建其对应的实现类LoginUser
        return new LoginUser(user, list);
    }
}
