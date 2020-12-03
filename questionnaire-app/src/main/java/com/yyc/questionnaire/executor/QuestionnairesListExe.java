package com.yyc.questionnaire.executor;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class QuestionnairesListExe {

    public MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireInsertQry) {
        //  TODO:
        return MultiResponse.buildSuccess();
    }
}
