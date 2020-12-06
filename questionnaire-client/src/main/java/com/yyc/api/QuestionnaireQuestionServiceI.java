package com.yyc.api;

import com.yyc.dto.QuestionnaireInsertCmd;

import java.util.List;
import java.util.Map;

/**
 * @author yuchengyao
 */
public interface QuestionnaireQuestionServiceI {

    /**
     * 问卷问题类型
     *
     * @return
     */
    List<Map<String, String>> listQuestionnaireQuestionType();

    /**
     * 根据问卷信息新增问题信息
     *
     * @param questionnaireInsertCmd 问卷信息
     */
    void batchInsert(QuestionnaireInsertCmd questionnaireInsertCmd);

}
