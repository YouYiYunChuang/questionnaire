package com.yyc.web.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 问卷权限aop
 */
@Slf4j
@Aspect
@Component
public class QuestionnaireAccessAspect {

    /**
     * 定义切入点，切入点为 com.yyc.web 中的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.yyc.web.*.*(..)))")
    public void smsContentAspect() {
        //  do nothing
    }

    /**
     * @description 在连接点执行之前执行的通知
     */
    @Before(value = "smsContentAspect()")
    public void doBefore() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //  短信接口中 Header 中的参数接收
        String smsAccessKey = request.getHeader("questionnaire-access-token");

        //  参数校验
        checkParameter(smsAccessKey);
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("smsContentAspect()")
    public void doAfter() {
        //  清除请求权限上下文
    }

    /**
     * 参数校验
     *
     * @param strings 参数
     */
    private void checkParameter(String... strings) {
        //  TODO: token check
    }
}
