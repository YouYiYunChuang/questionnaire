package com.yyc.question;

import com.yyc.api.QuestionnaireQuestionItemServiceI;
import com.yyc.domain.gateway.QuestionnaireQuestionItemGateway;
import com.yyc.dto.QuestionnaireQuestionInsertCmd;
import com.yyc.dto.QuestionnaireQuestionItemInsertCmd;
import com.yyc.question.executor.QuestionnaireQuestionItemTypeListExe;
import lombok.NonNull;
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

    @Resource
    private QuestionnaireQuestionItemGateway questionnaireQuestionItemGateway;

    @Override
    public List<Map<String, String>> listQuestionnaireQuestionItemType() {
        return questionnaireQuestionItemTypeListExe.listQuestionnaireQuestionItemType();
    }

    @Override
    public void batchInsert(QuestionnaireQuestionInsertCmd questionnaireQuestionInsertCmd) {

        if (questionnaireQuestionInsertCmd == null) {
            return;
        }

        List<QuestionnaireQuestionItemInsertCmd> questionnaireQuestionItemInsertCmdList = questionnaireQuestionInsertCmd.getQuestionnaireQuestionItemInsertCmdList();

        questionnaireQuestionItemInsertCmdList.forEach(value -> {

                    //  问题细项的问题code
                    value.setQuestionnaireQuestionCode(questionnaireQuestionInsertCmd.getQuestionnaireQuestionCode());
                    insert(value);
                }
        );

    }

    private void insert(@NonNull QuestionnaireQuestionItemInsertCmd questionnaireQuestionItemInsertCmd) {
        questionnaireQuestionItemGateway.insert(questionnaireQuestionItemInsertCmd);
    }
}
