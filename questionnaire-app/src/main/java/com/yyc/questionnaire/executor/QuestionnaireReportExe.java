package com.yyc.questionnaire.executor;

import com.yyc.access.AppContext;
import com.yyc.api.QuestionnaireServiceI;
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

    @Resource
    private QuestionnaireServiceI questionnaireServiceI;

    public void reportQuestionnaire(QuestionnaireReportCmd questionnaireReportCmd) {

        String openId = AppContext.getTokenDTO().getOpenId();

        questionnaireReportCmd.setOpenId(openId);

        questionnaireServiceI.checkReportQuestionnaire(openId, questionnaireReportCmd.getQuestionnaireCode());

        questionnaireQuestionReplicationGateway.insert(questionnaireReportCmd);
    }

}
