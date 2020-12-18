package com.yyc.handler;

import com.alibaba.cola.dto.Response;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yucheng.yao
 */
@Slf4j
@RestControllerAdvice
@Configuration
public class QuestionnaireExceptionHandler {

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response parameterExceptionHandler(MethodArgumentNotValidException e) {
        return Response.buildFailure(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION.getErrCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(QuestionnaireException.class)
    @ResponseBody
    public Response busExceptionHandler(QuestionnaireException e) {
        log.error("业务异常", e);
        if (e.getErrCode() == null) {
            return Response.buildFailure(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_BIZ_EXCEPTION.getErrCode(), e.getMessage());
        }
        return Response.buildFailure(e.getErrCode().getErrCode(), e.getErrCode().getErrDesc());
    }

    /**
     * 系统异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response systemExceptionHandler(Exception e) {
        log.error("系统异常", e);
        return Response.buildFailure(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION.getErrCode(), QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION.getErrDesc());
    }

}
