package com.yyc.domain.replication;

import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题回答表
 * @author yuchengyao
 */
@Data
@Builder
public class QuestionnaireQuestionReplication {

    /**
     * 问卷code
     */
    private String questionnaireCode;


    /**
     * 回答内容
     * json格式：{
     *      问题key：问题答案1，问题答案2
     * }
     */
    private String questionnaireQuestionReplicationContent;
}
