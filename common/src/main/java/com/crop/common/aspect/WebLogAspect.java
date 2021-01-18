package com.crop.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.crop.common.domain.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一日志处理切面
 * @author linmeng
 * @version 1.0
 * @date 2021年1月14日 17:32
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {

    //指定任意返回类型  任意模块  controller里面的任意类 下面的 任意返回类型 任意参数的方法
    @Pointcut("execution(public * com.crop.*.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint point)throws Throwable{}

    @AfterReturning(value = "webLog()",returning = "ret")
    public void doAfterReturning(Object ret)throws Throwable{}

    // ProceedingJoinPoint 有proceed方法执行用于在around方法里面区分切点执行前后
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        WebLog webLog = new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 判断方法上是否有ApiOperation注解,有的话获取该注解并将注解描述放到日志对象中
        if (method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }
        long endTime = System.currentTimeMillis();
        String urlStr  =  request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr,URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method,joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int)(endTime-startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(urlStr);
        Map<String,Object> logMap = new HashMap<>();
        logMap.put("url",webLog.getUrl());
        logMap.put("method",webLog.getMethod());
        logMap.put("parameter",webLog.getParameter());
        logMap.put("spendTime",webLog.getSpendTime());
        logMap.put("description",webLog.getDescription());
        log.info(Markers.appendEntries(logMap), JSONUtil.parse(webLog).toString());
        return result;
    }
    /**
     * 根据方法和传入的参数获取请求参数
     * @param method
     * @param args
     * @author linmeng
     * @date 2021年1月15日 16:17
     * @return java.lang.Object
     */
    private Object getParameter(Method method, Object[] args) {
        ArrayList<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody!=null){
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam!=null){
                HashMap<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (StringUtils.isNotBlank(requestParam.value())){
                    key = requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        int size = argList.size();
        if (size ==0){
            return null;
        }else if (size==1){
            return argList.get(0);
        }else {
            return argList;
        }
    }
}
