package com.yyc.question;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.api.QuestionnaireQuestionServiceI;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.question.executor.QuestionnaireQuestionTypeListExe;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yuchengyao
 */
public class QuestionnaireQuestionServiceImpl implements QuestionnaireQuestionServiceI {

    @Resource
    private QuestionnaireQuestionTypeListExe questionnaireQuestionTypeListExe;

    @Override
    public MultiResponse<Map<String, String>> listQuestionnaireQuestionType() {
        return questionnaireQuestionTypeListExe.listQuestionnaireQuestionType();
    }
}
