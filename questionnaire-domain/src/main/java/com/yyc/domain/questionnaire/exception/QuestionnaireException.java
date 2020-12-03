package com.yyc.domain.questionnaire.exception;

import com.alibaba.cola.exception.BaseException;
import com.alibaba.cola.exception.ErrorCodeI;

/**
 * @author yuchengyao
 */
public class QuestionnaireException extends BaseException {
    
    public QuestionnaireException(String errMessage) {
        super(errMessage);
    }

    public QuestionnaireException(ErrorCodeI errCode) {
        super(errCode.getErrDesc(), null);
        this.setErrCode(errCode);
    }

    public QuestionnaireException(ErrorCodeI errCode, Throwable e) {
        super(errCode.getErrDesc(), e);
        this.setErrCode(errCode);
    }
}
