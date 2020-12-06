package com.yyc.domain.exception;

import com.alibaba.cola.exception.ErrorCodeI;

/**
 * @author yuchengyao
 */
public enum QuestionnaireExceptionCode implements ErrorCodeI {

    QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION("QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION", "系统异常"),

    QUESTIONNAIRE_EXCEPTION_BIZ_EXCEPTION("QUESTIONNAIRE_EXCEPTION_BIZ_EXCEPTION", "业务异常"),

    QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION("QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION", "参数异常"),

    QUESTIONNAIRE_EXCEPTION_DATA_EXCEPTION("QUESTIONNAIRE_EXCEPTION_DATA_EXCEPTION", "数据异常"),

    QUESTIONNAIRE_EXCEPTION_WECHAT_OPEN_ID_EXCEPTION("QUESTIONNAIRE_EXCEPTION_WECHAT_OPEN_ID_EXCEPTION", "微信openid异常"),

    QUESTIONNAIRE_EXCEPTION_HTTP_REQUEST_EXCEPTION("QUESTIONNAIRE_EXCEPTION_HTTP_REQUEST_EXCEPTION", "http请求异常"),

    QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NULL_EXCEPTION("QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NULL_EXCEPTION", "用户请求有权限接口token为null"),

    QUESTIONNAIRE_EXCEPTION_USER_TOKEN_EXPIRED_EXCEPTION("QUESTIONNAIRE_EXCEPTION_USER_TOKEN_EXPIRED_EXCEPTION", "用户token过期"),

    QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NO_ACCESS_EXCEPTION("QUESTIONNAIRE_EXCEPTION_USER_TOKEN_NO_ACCESS_EXCEPTION", "token 无权限"),
    ;
    private final String errCode;
    private final String errDesc;

    QuestionnaireExceptionCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrDesc() {
        return errDesc;
    }
}
