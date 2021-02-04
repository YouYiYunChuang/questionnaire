package com.yyc.replication;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.gateway.QuestionnaireQuestionReplicationGateway;
import com.yyc.domain.status.DataStatus;
import com.yyc.domain.utils.CollectionCopyUtil;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.dto.QuestionnaireQuestionReplicationQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireQuestionReplicationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireQuestionReplicationGatewayImpl implements QuestionnaireQuestionReplicationGateway {

    @Resource
    private QuestionnaireQuestionReplicationMapper questionnaireQuestionReplicationMapper;

    @Override
    public void insert(QuestionnaireReportCmd questionnaireReportCmd) {

        checkQuestionnaireQuestionReplication(questionnaireReportCmd);

        QuestionnaireQuestionReplicationDO questionnaireQuestionReplicationDO = new QuestionnaireQuestionReplicationDO();

        BeanUtils.copyProperties(questionnaireReportCmd, questionnaireQuestionReplicationDO);

        questionnaireQuestionReplicationDO.setQuestionnaireReplicationContent(JsonUtils.toString(questionnaireReportCmd.getQuestionnaireReplicationContent()));

        questionnaireQuestionReplicationDO.setStatus(DataStatus.NEW.getCode());

        questionnaireQuestionReplicationMapper.insert(questionnaireQuestionReplicationDO);
    }

    @Override
    public List<QuestionnaireQuestionReplicationDTO> getQuestionnaireQuestionReplication(QuestionnaireQuestionReplicationQry questionnaireQuestionReplicationQry) {

        Wrapper wrapper = new QueryWrapper()
                .eq(questionnaireQuestionReplicationQry.getOpenId() != null, "open_id", questionnaireQuestionReplicationQry.getOpenId())
                .eq(questionnaireQuestionReplicationQry.getQuestionnaireCode() != null, "questionnaire_code", questionnaireQuestionReplicationQry.getQuestionnaireCode());

        return CollectionCopyUtil.copyList(questionnaireQuestionReplicationMapper.selectList(wrapper), QuestionnaireQuestionReplicationDTO.class);
    }

    private void checkQuestionnaireQuestionReplication(QuestionnaireReportCmd questionnaireReportCmd) {

        Wrapper wrapper = new QueryWrapper()
                .eq(questionnaireReportCmd.getOpenId() != null, "open_id", questionnaireReportCmd.getOpenId())
                .eq(questionnaireReportCmd.getQuestionnaireCode() != null, "questionnaire_code", questionnaireReportCmd.getQuestionnaireCode());

        List list = questionnaireQuestionReplicationMapper.selectList(wrapper);

        if (list == null || list.isEmpty()) {
            return;
        }

        throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_QUESTIONNAIRE_REPEAT_REPORT_EXCEPTION);
    }
}
