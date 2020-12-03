package com.yyc.questionnaire.executor;

import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireGetExe {

    public QuestionnaireDTO getQuestionnaire(String id) {

        return new QuestionnaireDTO();
    }
}
