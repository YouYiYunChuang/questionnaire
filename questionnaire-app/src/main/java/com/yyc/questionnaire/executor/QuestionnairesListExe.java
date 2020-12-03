package com.yyc.questionnaire.executor;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.dto.QuestionnaireInsertQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class QuestionnairesListExe {

    public MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireInsertQry questionnaireInsertQry) {
        return MultiResponse.buildSuccess();
    }
}
