package com.yyc.aop;

import com.yyc.domain.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * controller aop日志
 *
 * @author yuchengyao
 */
@Slf4j
@Aspect
@Component
public class QuestionnaireLogAspect {

    /**
     * 定义Pointcut，Pointcut的名称，此方法不能有返回值，该方法只是一个标示
     */
    @Pointcut("execution(public * com.yyc.web.*.*(..)))")
    public void controllerAspect() {
    }

    /**
     * 环绕通知（Around advice） ：包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。可以在方法的调用前后完成自定义的行为，也可以选择不执行。
     *
     * @param joinPoint
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取handler的参数（不包括 request 和 response）
        List<Object> logArgs = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        // 获取所有参数上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        Map<String, Object> params = new LinkedHashMap<>();

        Map<String, Object> requestBody = new LinkedHashMap<>();

        int index = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestParam) {
                    params.put(((RequestParam) annotation).value(), joinPoint.getArgs()[index++]);
                }
            }
        }
        log.info("params : " + params);
        log.info("ARGS : " + logArgs);
        log.info("handler执行时长 : " + time + " ms =========================================");
        log.info("返回结果:[{}]", JsonUtils.toString(result));
        return result;
    }


}
