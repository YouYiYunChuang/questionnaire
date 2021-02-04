package com.yyc.questionnaire.executor.query;

import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.domain.status.DataStatus;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireGetExe {


    @Resource
    private QuestionnaireGateway questionnaireGateway;

    public QuestionnaireDTO getQuestionnaire(String questionnaireCode, String scene) {

        if (StringUtils.isEmpty(questionnaireCode) && StringUtils.isEmpty(scene)) {
            //  参数唯一,参数异常
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION);
        }

        if (!StringUtils.isEmpty(questionnaireCode) && !StringUtils.isEmpty(scene)) {
            //  参数唯一,参数异常
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_PARAMETER_EXCEPTION);
        }

        QuestionnaireQry questionnaireQry = new QuestionnaireQry();

        questionnaireQry.setQuestionnaireCode(questionnaireCode);
        questionnaireQry.setQuestionnaireScene(scene);

        QuestionnaireDTO questionnaire = questionnaireGateway.getQuestionnaire(questionnaireQry);

        if (DataStatus.DEACTIVATE.getCode().equals(questionnaire.getStatus())) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_STATUS_DEACTIVATE_EXCEPTION);
        }

        return questionnaire;
    }
}
