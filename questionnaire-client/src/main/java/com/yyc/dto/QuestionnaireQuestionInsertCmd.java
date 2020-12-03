package com.yyc.dto;

import java.util.List;

/**
 * @author yuchengyao
 */
public class QuestionnaireQuestionInsertCmd {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 问题code
     */
    private String questionnaireQuestionCode;

    /**
     * 问题序号
     */
    private Integer questionnaireQuestionSort;

    /**
     * 问题名称
     */
    private String questionnaireQuestionTitle;

    /**
     * 问题内容，填空题，问题项框架
     */
    private String questionnaireQuestionContent;

    /**
     * 问题细项列表
     */
    private List<QuestionnaireQuestionItemInsertCmd> questionnaireQuestionItemInsertCmdList;
}
