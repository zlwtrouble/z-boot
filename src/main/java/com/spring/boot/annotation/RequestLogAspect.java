package com.spring.boot.annotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spring.boot.common.utils.IpUtils;
import com.spring.boot.vo.BaseResultVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoliwei
 * @description:
 * @date 2019/1/28 11:06
 **/


@Component
@Aspect
public class RequestLogAspect {
    private final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.spring.boot.controller..*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //记录请求开始执行时间：
        long beginTime = System.currentTimeMillis();
        //获取请求信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        //获取代理地址、请求地址、请求类名、方法名
        String remoteAddress = IpUtils.getIpAddr(request);
        String requestURI = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();

        //获取请求参数：
        Signature signature = pjp.getSignature();
        //获取请求参数类型
        String parameterNames = signature.getDeclaringTypeName();
        //获取请求参数值
        Object[] aparameterValuesrgs = pjp.getArgs();
        StringBuilder sb = new StringBuilder();
        //组合请求参数，进行日志打印
        sb.append(JSON.toJSONString(aparameterValuesrgs));
//        if (parameterNames != null && parameterNames.length > 0) {
//            for (int i = 0; i < parameterNames.length; i++) {
//                if (parameterNames[i].equals("bindingResult")) {
//                    break;
//                }
//                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
//                    sb.
//                            append("[").
//                            append(parameterNames[i]).append("=").append(parameterValues[i])
//                            .append("]");
//                } else {
//                    sb.
//                            append("[").
//                            append(parameterNames[i]).append("=")
//                            .append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
//                            .append("]");
//                }
//            }
//        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            //请求操纵失败
            //记录错误日志
            logger.error("(ง•̀_•́)ง (っ•̀ω•́)っ          切面处理请求错误！ IP信息(ง•̀_•́)ง->： 【{}}】 " +
                            "URI信息(ง•̀_•́)ง->：【{}】 请求映射控制类(ง•̀_•́)ง->：【{}】 " +
                            "请求方法(ง•̀_•́)ง->：【{}】 请求参数列表(ง•̀_•́)ง->：【{}】", remoteAddress, requestURI, clazzName, methodName,
                    sb.toString());
            throw throwable;
        }
        //请求操作成功
        String resultJosnString = "";
        if (result != null) {
            if (result instanceof BaseResultVo) {
                resultJosnString = JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
            } else {
                resultJosnString = String.valueOf(result);
            }
        }
        //记录请求完成执行时间：
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - beginTime;
        //记录日志
        logger.info("请求操作成功！ 请求耗时：【{}】 " +
                        "IP信息(◍'౪`◍)ﾉﾞ->： 【{}}】  URI信息(◍'౪`◍)ﾉﾞ->：【{}】 " +
                        "请求映射控制类(◍'౪`◍)ﾉﾞ->：【{}】 请求方法(◍'౪`◍)ﾉﾞ->：【{}】 " +
                        "请求参数列表(◍'౪`◍)ﾉﾞ->：【{}】 返回值(◍'౪`◍)ﾉﾞ->：【{}】", usedTime, remoteAddress, requestURI, clazzName,
                methodName, sb.toString(), resultJosnString);

        return result;
    }
}

