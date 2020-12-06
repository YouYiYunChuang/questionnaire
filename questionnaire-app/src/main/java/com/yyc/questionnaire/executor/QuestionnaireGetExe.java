package com.yyc.questionnaire.executor;

import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireGetExe {


    @Resource
    private QuestionnaireGateway questionnaireGateway;

    public QuestionnaireDTO getQuestionnaire(String questionnaireCode) {

        QuestionnaireQry questionnaireQry = new QuestionnaireQry();

        questionnaireQry.setQuestionnaireCode(questionnaireCode);

        return questionnaireGateway.getQuestionnaire(questionnaireQry);
    }
}
