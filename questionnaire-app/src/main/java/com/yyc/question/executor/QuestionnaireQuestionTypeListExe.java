package com.yyc.question.executor;

import com.yyc.domain.status.QuestionnaireQuestionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireQuestionTypeListExe {

    public List<Map<String, String>> listQuestionnaireQuestionType() {
        return QuestionnaireQuestionType.getQuestionnaireQuestionTypeList();
    }
}
