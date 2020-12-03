package com.yyc.question;

import com.yyc.api.QuestionnaireQuestionItemServiceI;
import com.yyc.question.executor.QuestionnaireQuestionItemTypeListExe;
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
public class QuestionnaireQuestionItemServiceImpl implements QuestionnaireQuestionItemServiceI {

    @Resource
    private QuestionnaireQuestionItemTypeListExe questionnaireQuestionItemTypeListExe;


    @Override
    public List<Map<String, String>> listQuestionnaireQuestionItemType() {
        return questionnaireQuestionItemTypeListExe.listQuestionnaireQuestionItemType();
    }
}
