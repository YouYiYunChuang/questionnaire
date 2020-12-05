package com.yyc.question.executor;

import com.yyc.domain.status.QuestionnaireQuestionItemType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireQuestionItemTypeListExe {

    public List<Map<String, String>> listQuestionnaireQuestionItemType() {
        return QuestionnaireQuestionItemType.getQuestionnaireQuestionTypeList();
    }
}
