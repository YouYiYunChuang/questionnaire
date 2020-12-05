package com.yyc.domain.question;

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
}
