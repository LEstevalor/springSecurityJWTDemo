package com.it.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@org.aspectj.lang.annotation.Aspect
@Slf4j
public class URLAspect {

    @Pointcut("execution(public * com.it.controller..*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //获取请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        log.info("URL访问路径 : {}",request.getRequestURL().toString());
        log.info("请求参数    : {}", JSONObject.toJSONString(joinPoint.getArgs()));
    }


}
