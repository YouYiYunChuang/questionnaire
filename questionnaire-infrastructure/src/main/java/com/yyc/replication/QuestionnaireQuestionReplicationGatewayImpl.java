package com.yyc.replication;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.QuestionnaireQuestionReplicationGateway;
import com.yyc.dto.QuestionnaireReportCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireQuestionReplicationGatewayImpl implements QuestionnaireQuestionReplicationGateway {

    private QuestionnaireQuestionReplicationMapper questionnaireQuestionReplicationMapper;

    @Override
    public void insert(QuestionnaireReportCmd questionnaireReportCmd) {

        checkQuestionnaireQuestionReplication(questionnaireReportCmd);

        QuestionnaireQuestionReplicationDO questionnaireQuestionReplicationDO = new QuestionnaireQuestionReplicationDO();

        BeanUtils.copyProperties(questionnaireQuestionReplicationDO, questionnaireQuestionReplicationDO);

        questionnaireQuestionReplicationMapper.insert(questionnaireQuestionReplicationDO);
    }

    private void checkQuestionnaireQuestionReplication(QuestionnaireReportCmd questionnaireReportCmd) {

        Wrapper wrapper = new QueryWrapper()
                .eq(questionnaireReportCmd.getOpenId() != null, "open_id", questionnaireReportCmd.getOpenId())
                .eq(questionnaireReportCmd.getOpenId() != null, "questionnaire_code", questionnaireReportCmd.getQuestionnaireCode())
                .ne(true, "questionnaire.status", 21);

        List list = questionnaireQuestionReplicationMapper.selectList(wrapper);

        if (list != null || list.isEmpty()) {
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_QUESTIONNAIRE_REPEAT_REPORT_EXCEPTION);
        }
    }
}
