package com.yyc.question;

import com.yyc.domain.gateway.QuestionnaireQuestionGateway;
import com.yyc.dto.QuestionnaireQuestionInsertCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Service
public class QuestionnaireQuestionGatewayImpl implements QuestionnaireQuestionGateway {

    @Resource
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;

    @Override
    public void insert(QuestionnaireQuestionInsertCmd questionnaireQuestionInsertCmd) {

        QuestionnaireQuestionDO questionnaireQuestionDO = new QuestionnaireQuestionDO();

        BeanUtils.copyProperties(questionnaireQuestionInsertCmd, questionnaireQuestionDO);

        questionnaireQuestionMapper.insert(questionnaireQuestionDO);
    }
}
