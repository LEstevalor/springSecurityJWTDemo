package com.it.service.impl;

import com.it.domain.LoginUser;
import com.it.domain.ResponseResult;
import com.it.domain.User;
import com.it.service.LoginService;
import com.it.utils.JwtUtil;
import com.it.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager 用户认证（需在SecurityConfig配置类中重写方法配置到Bean）
        UsernamePasswordAuthenticationToken upa = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(upa);

        //认证失败，给提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        //认证成功，使用userid生成jwt，jwt存入ResponseResult（响应返回结果）中，另外可以查询用户信息存入缓存中(Redis)
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login:"+userId, loginUser);

        return new ResponseResult(200, "登录成功", map);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder的用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 清空redis
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }
}
