package com.yyc.domain.question;

import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题细项
 *
 * @author yuchengyao
 */
@Data
@Builder
public class QuestionnaireQuestionItem {

    /**
     * 问题code
     */
    private String questionnaireQuestionCode;

    /**
     * 问题细项code
     */
    private String questionnaireQuestionItemCode;

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
