package com.yyc.questionnaire;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.questionnaire.executor.*;
import com.yyc.questionnaire.executor.query.QuestionnaireExportExe;
import com.yyc.questionnaire.executor.query.QuestionnaireGetExe;
import com.yyc.questionnaire.executor.query.QuestionnaireResultExe;
import com.yyc.questionnaire.executor.query.QuestionnairesListExe;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    @Resource
    private QuestionnaireShareExe questionnaireShareExe;

    @Resource
    private QuestionnaireResultExe questionnaireResultExe;

    @Resource
    private QuestionnaireExportExe questionnaireExportExe;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireInsertExe.insert(questionnaireInsertCmd);
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@NonNull QuestionnaireQry questionnaireQry) {
        return questionnairesListExe.listQuestionnaires(questionnaireQry);
    }

    @Override
    public QuestionnaireDTO getQuestionnaire(@NonNull QuestionnaireQry questionnaireQry) {
        return questionnaireGetExe.getQuestionnaire(questionnaireQry.getQuestionnaireCode(), questionnaireQry.getQuestionnaireScene());
    }

    @Override
    public void deactivateQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireDeactivateExe.deactivateQuestionnaire(questionnaireCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportQuestionnaire(@NonNull QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireReportExe.reportQuestionnaire(questionnaireReportCmd);
    }

    @Override
    public void checkReportQuestionnaire(@NonNull String questionnaireCode, @NonNull Map<String, Object> questionnaireReplicationContent) {
        questionnaireReportCheckExe.checkQuestionnaire(questionnaireCode, questionnaireReplicationContent);
    }

    @Override
    public byte[] shareQuestionnaire(@NonNull String scene, @NonNull String page) throws Exception {
        return questionnaireShareExe.shareQuestionnaire(scene, page);
    }

    @Override
    public void exportResultByQuestionnaireCode(String questionnaireCode, HttpServletResponse response) throws Exception {

        Map<String, List<List<Map<String, String>>>> questionnaireResult = questionnaireResultExe.getQuestionnaireResult(questionnaireCode);
        questionnaireExportExe.exportQuestionnaireResult(questionnaireResult, response);
    }
}
