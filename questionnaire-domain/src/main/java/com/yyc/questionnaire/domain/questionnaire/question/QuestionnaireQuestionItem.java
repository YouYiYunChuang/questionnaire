package com.yyc.questionnaire.domain.questionnaire.question;

import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题细项
 */
@Data
@Builder
public class QuestionnaireQuestionItem {

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
    private String questionnaireQuestionTtemContent;

}
