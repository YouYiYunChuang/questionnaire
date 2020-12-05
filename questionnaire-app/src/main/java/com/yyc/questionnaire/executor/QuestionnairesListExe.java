package com.yyc.questionnaire.executor;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 获取问卷列表
 *
 * @author yuchengyao
 */
@Component
public class QuestionnairesListExe {

    @Resource
    private QuestionnaireGateway questionnaireGateway;

    public MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireInsertQry) {
        //  TODO:

        questionnaireGateway.getQuestionnaire(questionnaireInsertQry);
        return MultiResponse.buildSuccess();
    }
}
