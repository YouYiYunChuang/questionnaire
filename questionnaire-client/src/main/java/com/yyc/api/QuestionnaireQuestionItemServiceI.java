package com.yyc.api;

import com.yyc.dto.QuestionnaireQuestionInsertCmd;

import java.util.List;
import java.util.Map;

/**
 * @author yuchengyao
 */
public interface QuestionnaireQuestionItemServiceI {

    /**
     * 问卷问题细项类型列表
     *
     * @return
     */
    List<Map<String, String>> listQuestionnaireQuestionItemType();

    /**
     * 根据问题信息新建问题细项
     *
     * @param questionnaireQuestionInsertCmd 问卷问题信息
     */
    void batchInsert(QuestionnaireQuestionInsertCmd questionnaireQuestionInsertCmd);
}
