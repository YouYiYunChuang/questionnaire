package com.yyc.question;

import com.yyc.api.QuestionnaireQuestionServiceI;
import com.yyc.question.executor.QuestionnaireQuestionTypeListExe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Slf4j
@Service
public class QuestionnaireQuestionServiceImpl implements QuestionnaireQuestionServiceI {

    @Resource
    private QuestionnaireQuestionTypeListExe questionnaireQuestionTypeListExe;

    @Override
    public List<Map<String, String>> listQuestionnaireQuestionType() {
        return questionnaireQuestionTypeListExe.listQuestionnaireQuestionType();
    }
}
