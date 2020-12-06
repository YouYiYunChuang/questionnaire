package com.yyc.domain.gateway;

import com.yyc.dto.QuestionnaireReportCmd;

/**
 * @author yuchengyao
 */
public interface QuestionnaireQuestionReplicationGateway {

    /**
     * 新增上报
     *
     * @param questionnaireReportCmd 上报信息
     */
    void insert(QuestionnaireReportCmd questionnaireReportCmd);
}
