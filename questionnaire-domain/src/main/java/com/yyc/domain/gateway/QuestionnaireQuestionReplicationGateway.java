package com.yyc.domain.gateway;

import com.yyc.dto.QuestionnaireQuestionReplicationQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireQuestionReplicationDTO;

import java.util.List;

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

    /**
     * 获取问卷回答结果
     *
     * @param questionnaireQuestionReplicationQry
     * @return
     */
    List<QuestionnaireQuestionReplicationDTO> getQuestionnaireQuestionReplication(QuestionnaireQuestionReplicationQry questionnaireQuestionReplicationQry);
}
