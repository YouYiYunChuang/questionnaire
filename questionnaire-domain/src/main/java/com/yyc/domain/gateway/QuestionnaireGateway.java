package com.yyc.domain.gateway;

import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;

/**
 * @author yuchengyao
 */
public interface QuestionnaireGateway {

    /**
     * 获取唯一的问卷
     *
     * @param questionnaireQry
     * @return
     */
    QuestionnaireDTO getQuestionnaire(QuestionnaireQry questionnaireQry);
}
