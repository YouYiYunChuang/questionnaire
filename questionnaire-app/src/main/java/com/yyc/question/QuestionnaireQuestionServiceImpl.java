package com.yyc.question;

import com.yyc.api.QuestionnaireQuestionItemServiceI;
import com.yyc.api.QuestionnaireQuestionServiceI;
import com.yyc.domain.gateway.QuestionnaireQuestionGateway;
import com.yyc.domain.utils.CodeBuildUtils;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQuestionInsertCmd;
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

    @Resource
    private QuestionnaireQuestionItemServiceI questionnaireQuestionItemServiceI;

    @Resource
    private QuestionnaireQuestionGateway questionnaireQuestionGateway;

    @Override
    public List<Map<String, String>> listQuestionnaireQuestionType() {
        return questionnaireQuestionTypeListExe.listQuestionnaireQuestionType();
    }

    @Override
    public void batchInsert(QuestionnaireInsertCmd questionnaireInsertCmd) {

        if (questionnaireInsertCmd == null) {
            return;
        }

        List<QuestionnaireQuestionInsertCmd> questionnaireQuestionInsertCmdList = questionnaireInsertCmd.getQuestionnaireQuestionInsertCmdList();

        questionnaireQuestionInsertCmdList.forEach(value -> {

                    //  问题的问卷code
                    value.setQuestionnaireCode(questionnaireInsertCmd.getQuestionnaireCode());

                    //  生产问卷问题code
                    value.setQuestionnaireQuestionCode(CodeBuildUtils.getCode());

                    insert(value);

                    questionnaireQuestionItemServiceI.batchInsert(value);
                }
        );

    }

    private void insert(QuestionnaireQuestionInsertCmd questionnaireQuestionInsertCmd) {
        questionnaireQuestionGateway.insert(questionnaireQuestionInsertCmd);
    }
}
