package com.yyc.domain.status;

import com.yyc.domain.Status;

/**
 * @author yuchengyao
 */

public enum DataStatus implements Status {

    NEW("20", "新增"),

    DELETE("21", "删除"),

    DEACTIVATE("22", "停用"),
    ;


    private String code;

    private String message;


    DataStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
