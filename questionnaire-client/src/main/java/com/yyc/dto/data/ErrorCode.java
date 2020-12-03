package com.yyc.dto.data;

import com.alibaba.cola.exception.ErrorCodeI;

/**
 * @author yuchengyao
 */
public enum ErrorCode implements ErrorCodeI {

    QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION("QUESTIONNAIRE_EXCEPTION_SYSTEM_EXCEPTION", "系统异常"),

    QUESTIONNAIRE_EXCEPTION_BIZ_EXCEPTION("QUESTIONNAIRE_EXCEPTION_BIZ_EXCEPTION", "业务异常"),

    QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION("QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION", "参数异常"),
    ;
    private final String errCode;
    private final String errDesc;

    ErrorCode(String errCode, String errDesc) {
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
