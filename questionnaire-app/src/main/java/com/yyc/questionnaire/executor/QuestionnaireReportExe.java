package com.yyc.questionnaire.executor;

import com.yyc.access.AppContext;
import com.yyc.domain.gateway.QuestionnaireQuestionReplicationGateway;
import com.yyc.dto.QuestionnaireReportCmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireReportExe {

    @Resource
    private QuestionnaireQuestionReplicationGateway questionnaireQuestionReplicationGateway;

    public void reportQuestionnaire(QuestionnaireReportCmd questionnaireReportCmd) {

        String openId = AppContext.getTokenDTO().getOpenId();

        questionnaireReportCmd.setOpenId(openId);

        questionnaireQuestionReplicationGateway.insert(questionnaireReportCmd);
        //  TODO:
    }
}
