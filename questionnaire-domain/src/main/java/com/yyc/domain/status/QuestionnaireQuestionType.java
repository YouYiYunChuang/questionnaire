package com.yyc.domain.status;

import com.yyc.domain.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷问题类型枚举
 *
 * @author yuchengyao
 */
public enum QuestionnaireQuestionType implements Status {


    SINGLE_CHOICE("MULTIPLE_CHOICE", "单选题"),

    MULTIPLE_CHOICE("MULTIPLE_CHOICE", "多选题"),

    FILL_IN_THE_BLANK("FILL_IN_THE_BLANK", "填空题");

    private String code;

    private String message;


    QuestionnaireQuestionType(String code, String message) {
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

        for (QuestionnaireQuestionType value : values()) {
            Map<String, String> enumMap = new HashMap<>();
            enumMap.put("code", value.getCode());
            enumMap.put("message", value.getMessage());

            resultList.add(enumMap);
        }

        return resultList;
    }


}
