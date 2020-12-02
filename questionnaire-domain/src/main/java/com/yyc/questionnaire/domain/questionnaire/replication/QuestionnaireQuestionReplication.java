package com.yyc.questionnaire.domain.questionnaire.replication;

import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题回答表
 */
@Data

@Builder
public class QuestionnaireQuestionReplication {

    /**
     * 回答内容
     */
    private String questionnaireQuestionReplicationContent;
}
