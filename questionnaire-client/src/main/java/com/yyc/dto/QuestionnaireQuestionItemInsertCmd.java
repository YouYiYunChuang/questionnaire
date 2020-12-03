package com.yyc.dto;

/**
 * @author yuchengyao
 */
public class QuestionnaireQuestionItemInsertCmd{

    /**
     * 问题code
     */
    private String questionnaireQuestionCode;

    /**
     * 细项排序
     */
    private Integer questionnaireQuestionItemSort;

    /**
     * 细项类型code：单选、多选、填空等
     */
    private String questionnaireQuestionTypeCode;

    /**
     * 细项内容
     */
    private String questionnaireQuestionItemContent;
}
