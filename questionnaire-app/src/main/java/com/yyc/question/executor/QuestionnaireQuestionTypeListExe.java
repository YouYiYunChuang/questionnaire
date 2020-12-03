package com.yyc.question.executor;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.domain.questionnaire.status.QuestionnaireQuestionType;

import java.util.Map;

/**
 * @author yuchengyao
 */
public class QuestionnaireQuestionTypeListExe {

    public MultiResponse<Map<String, String>> listQuestionnaireQuestionType() {
        return MultiResponse.ofWithoutTotal(QuestionnaireQuestionType.getQuestionnaireQuestionTypeList());
    }
}
