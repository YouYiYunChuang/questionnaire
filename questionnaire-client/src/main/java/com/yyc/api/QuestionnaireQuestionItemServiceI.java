package com.yyc.api;

import com.alibaba.cola.dto.MultiResponse;

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
}
