package com.yyc.questionnaire.executor;

import com.yyc.domain.gateway.QuestionnaireGateway;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireDeactivateExe {

    @Resource
    private QuestionnaireGateway questionnaireGateway;

    public void deactivateQuestionnaire(String questionnaireCode) {
        questionnaireGateway.deactivateQuestionnaire(questionnaireCode);
    }
}
