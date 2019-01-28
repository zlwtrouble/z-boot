package com.spring.boot.annotation;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: weicz
 * @Date: 2018/8/23 13:22
 */
@Aspect
@Component
@Slf4j
public class WebLogHeadAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final String PRE_TAG = "http request >>>>>>>>>>>>>>>>>>>> ";

    @Pointcut("execution(public * com.spring.boot.controller..*.*(..))")
    public void controller(){}

    @Pointcut("execution(public * com.spring.boot.controller..*.*(..))")
    public void api(){}

    @Before("controller() || api()")
    public void doBefore(JoinPoint joinPoint)  {
        try {
            startTime.set(System.currentTimeMillis());
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            log.info(PRE_TAG + "URL : " + request.getRequestURL().toString());
            log.info(PRE_TAG + "HTTP_METHOD : " + request.getMethod());
            log.info(PRE_TAG + "IP : " + request.getRemoteAddr());
            log.info(PRE_TAG + "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info(PRE_TAG + "ARGS : " + JSONObject.toJSONString(joinPoint.getArgs()));
        }catch (Exception e) {
            log.error("日志异常",e);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "controller()||api()")
    public void doAfterReturning(Object ret) {
        log.info(PRE_TAG + "SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}
