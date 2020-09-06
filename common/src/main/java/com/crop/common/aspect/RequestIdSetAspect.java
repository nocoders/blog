package com.crop.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 给每个请求设置 requestId
 * @author linmeng
 * @version 1.0
 * @date 6/9/2020 上午10:08
 */
//@Aspect
//@Component
//@Order(1)
//@Slf4j
public class RequestIdSetAspect {

//    @Value("${request.id}")
    private String requestIdKey;

//    @Pointcut("execution(public * com.crop.*.controller.*.*(..))")
    public void requestIdSet(){}

//    @Before("requestIdSet()")
    public void doBefore(JoinPoint point){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        if (StringUtils.isBlank(request.getHeader(requestIdKey))){
//            request.setAttribute();
        }
    }
}
