package com.yyc.domain.gateway;

import com.yyc.dto.QuestionnaireQuestionInsertCmd;

/**
 * @author yuchengyao
 */
public interface QuestionnaireQuestionGateway {

    /**
     * 新增问题项
     *
     * @param questionnaireQuestionInsertCmd
     */
    void insert(QuestionnaireQuestionInsertCmd questionnaireQuestionInsertCmd);
}
