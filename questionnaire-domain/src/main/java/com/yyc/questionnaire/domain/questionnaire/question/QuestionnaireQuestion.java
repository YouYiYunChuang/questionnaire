package com.yyc.questionnaire.domain.questionnaire.question;

import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题模型
 */
@Data
@Builder
public class QuestionnaireQuestion {

    /**
     * 问卷code
     */
    private String questionnaire_code;

    /**
     * 问题code
     */
    private String questionnaire_question_code;

    /**
     * 问题序号
     */
    private Integer questionnaire_question_sort;

    /**
     * 问题名称
     */
    private String questionnaire_question_title;

    /**
     * 问题内容，填空题，问题项框架
     */
    private String questionnaire_question_content;
}
