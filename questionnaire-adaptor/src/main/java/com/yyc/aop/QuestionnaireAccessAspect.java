package com.yyc.aop;


import com.yyc.access.Access;
import com.yyc.access.Role;
import com.yyc.config.RedisUtils;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.dto.data.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 问卷权限aop
 *
 * @author yuchengyao
 */
@Slf4j
@Aspect
@Component
public class QuestionnaireAccessAspect {

    @Resource
    private RedisUtils redisUtils;

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
    public void doBefore(ProceedingJoinPoint point) throws NoSuchMethodException {

        Access accessAnnotation = getAccessAnnotation(point);

        if (accessAnnotation == null) {
            return;
        }

        String token = getToken();

        if (token == null) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NULL_EXCEPTION);
        }

        //  参数校验
        checkAccess(token, accessAnnotation);
    }

    private String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //  token
        return request.getHeader("questionnaire-access-token");
    }

    private Access getAccessAnnotation(ProceedingJoinPoint point) throws NoSuchMethodException {

        Object target = point.getTarget();

        String methodName = point.getSignature().getName();

        Class[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);

        return method.getAnnotation(Access.class);
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
     * @param token
     * @param access
     */
    private void checkAccess(String token, Access access) {
        String string = redisUtils.getString(token);

        if (string == null) {

            //  token 过期
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_USER_TOKEN_EXPIRED_EXCEPTION);
        }

        TokenDTO parse = JsonUtils.parse(string, TokenDTO.class);

        String[] roleCodes = parse.getRoleCode();

        Role[] roles = access.roles();

        if (roles == null || roles.length == 0 ||
                roleCodes == null || roleCodes.length == 0) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NO_ACCESS_EXCEPTION);
        }

        List<String> roleCodeArrays = new ArrayList<String>(Arrays.asList(roleCodes));

        List<Role> filterRoleCodes = Arrays.stream(roles).sequential().filter(x -> roleCodeArrays.contains(x.name())).collect(Collectors.toList());

        if (filterRoleCodes == null || filterRoleCodes.isEmpty()) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NO_ACCESS_EXCEPTION);
        }


    }


}
