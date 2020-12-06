package com.yyc.questionnaire.executor;

import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.domain.status.DataStatus;
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

        QuestionnaireDTO questionnaire = questionnaireGateway.getQuestionnaire(questionnaireQry);

        if (DataStatus.DEACTIVATE.getCode().equals(questionnaire.getStatus())) {
            new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_STATUS_DEACTIVATE_EXCEPTION);
        }
        
        return questionnaire;
    }
}
