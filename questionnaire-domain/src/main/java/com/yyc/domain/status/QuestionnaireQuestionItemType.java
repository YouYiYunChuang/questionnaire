package com.yyc.domain.status;

import com.yyc.domain.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷问题细项类型
 *
 * @author yuchengyao
 */
public enum QuestionnaireQuestionItemType implements Status {

    STRING("STRING", "字符"),

    INTEGER("INTEGER", "数字"),

    ;

    private String code;

    private String message;


    QuestionnaireQuestionItemType(String code, String message) {
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

    public static List<Map<String, String>> getQuestionnaireQuestionTypeList() {

        List<Map<String, String>> resultList = new ArrayList<>();

        for (QuestionnaireQuestionItemType value : values()) {
            Map<String, String> enumMap = new HashMap<>();
            enumMap.put("code", value.getCode());
            enumMap.put("message", value.getMessage());

            resultList.add(enumMap);
        }

        return resultList;
    }
}
