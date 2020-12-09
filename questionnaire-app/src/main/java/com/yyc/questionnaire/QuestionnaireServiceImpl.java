package com.yyc.questionnaire;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.questionnaire.executor.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Slf4j
@Service
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

    @Resource
    private QuestionnaireReportCheckExe questionnaireReportCheckExe;

    @Transactional
    @Override
    public void insert(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireInsertExe.insert(questionnaireInsertCmd);
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@NonNull QuestionnaireQry questionnaireInsertQry) {
        return questionnairesListExe.listQuestionnaires(questionnaireInsertQry);
    }

    @Override
    public QuestionnaireDTO getQuestionnaire(@NonNull String questionnaireCode) {
        return questionnaireGetExe.getQuestionnaire(questionnaireCode);
    }

    @Override
    public void deactivateQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireDeactivateExe.deactivateQuestionnaire(questionnaireCode);
    }

    @Override
    @Transactional
    public void reportQuestionnaire(@NonNull QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireReportExe.reportQuestionnaire(questionnaireReportCmd);
    }

    @Override
    public void checkReportQuestionnaire( @NonNull String questionnaireCode) {
        questionnaireReportCheckExe.checkQuestionnaire(questionnaireCode);
    }

}
