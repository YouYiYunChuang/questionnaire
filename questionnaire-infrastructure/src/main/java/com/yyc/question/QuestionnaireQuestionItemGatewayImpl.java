package com.yyc.question;

import com.yyc.domain.gateway.QuestionnaireQuestionItemGateway;
import com.yyc.dto.QuestionnaireQuestionItemInsertCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuchengyao
 */
@Service
public class QuestionnaireQuestionItemGatewayImpl implements QuestionnaireQuestionItemGateway {

    @Resource
    private QuestionnaireQuestionItemMapper questionnaireQuestionItemMapper;

    @Override
    public void insert(QuestionnaireQuestionItemInsertCmd questionnaireQuestionItemInsertCmd) {

        QuestionnaireQuestionItemDO questionnaireQuestionDO = new QuestionnaireQuestionItemDO();

        BeanUtils.copyProperties(questionnaireQuestionItemInsertCmd, questionnaireQuestionDO);

        questionnaireQuestionItemMapper.insert(questionnaireQuestionDO);
    }
}
