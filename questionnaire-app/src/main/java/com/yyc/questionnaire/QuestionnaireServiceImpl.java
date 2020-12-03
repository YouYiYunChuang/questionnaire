package com.yyc.questionnaire;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireInsertQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.questionnaire.executor.*;
import lombok.NonNull;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
public class QuestionnaireServiceImpl implements QuestionnaireServiceI {

    @Resource
    private QuestionnaireInsertExe questionnaireInsertExe;

    @Resource
    private QuestionnairesListExe questionnairesListExe;

    @Resource
    private QuestionnaireGetExe questionnaireGetExe;

    @Resource
    private QuestionnaireDeactivateExe questionnaireDeactivateExe;

    @Resource
    private QuestionnaireReportExe questionnaireReportExe;

    @Override
    public void insertQuestionnaire(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireInsertExe.insertQuestionnaire();
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@NonNull QuestionnaireInsertQry questionnaireInsertQry) {
        return questionnairesListExe.listQuestionnaires(questionnaireInsertQry);
    }

    @Override
    public QuestionnaireDTO getQuestionnaire(@NonNull String id) {
        return questionnaireGetExe.getQuestionnaire(id);
    }

    @Override
    public void deactivateQuestionnaire(@NonNull String id) {
        questionnaireDeactivateExe.deactivateQuestionnaire(id);
    }

    @Override
    public void reportQuestionnaire(@NonNull QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireReportExe.reportQuestionnaire(questionnaireReportCmd);
    }
}
