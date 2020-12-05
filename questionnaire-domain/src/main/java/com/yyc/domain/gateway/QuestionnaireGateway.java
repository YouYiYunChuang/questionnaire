package com.yyc.domain.gateway;

import com.alibaba.cola.dto.MultiResponse;
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

    /**
     * 获取问卷列表
     *
     * @param questionnaireQry
     * @return
     */
    MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireQry);
}
