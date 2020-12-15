package com.yyc.questionnaire.executor;

import com.yyc.api.QuestionnaireQuestionServiceI;
import com.yyc.domain.gateway.QuestionnaireGateway;
import com.yyc.domain.utils.CodeBuildUtils;
import com.yyc.dto.QuestionnaireInsertCmd;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireInsertExe {

    @Resource
    private QuestionnaireGateway questionnaireGateway;

    @Resource
    private QuestionnaireQuestionServiceI questionnaireQuestionServiceI;

    public void insert(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {

        questionnaireInsertCmd.setQuestionnaireCode(CodeBuildUtils.getCode());

        questionnaireInsertCmd.setQuestionnaireScene(CodeBuildUtils.getScene());

        questionnaireGateway.insert(questionnaireInsertCmd);

        questionnaireQuestionServiceI.batchInsert(questionnaireInsertCmd);
    }

}
