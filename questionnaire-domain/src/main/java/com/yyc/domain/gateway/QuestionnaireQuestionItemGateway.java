package com.yyc.domain.gateway;

import com.yyc.dto.QuestionnaireQuestionItemInsertCmd;

/**
 * @author yuchengyao
 */
public interface QuestionnaireQuestionItemGateway {

    /**
     * 新增问题细项
     *
     * @param questionnaireQuestionItemInsertCmd
     */
    void insert(QuestionnaireQuestionItemInsertCmd questionnaireQuestionItemInsertCmd);
}
