package com.yyc.questionnaire.executor;

import cn.hutool.core.date.DateUtil;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireReportCheckExe {

    @Resource
    private QuestionnaireServiceI questionnaireServiceI;

    public void checkQuestionnaire(@NonNull String questionnaireCode, @NonNull Map<String, Object> questionnaireReplicationContent) {

        QuestionnaireQry questionnaireQry = new QuestionnaireQry();
        questionnaireQry.setQuestionnaireCode(questionnaireCode);
        QuestionnaireDTO questionnaire = questionnaireServiceI.getQuestionnaire(questionnaireQry);

        checkQuestionnaire(questionnaire, questionnaireReplicationContent);

    }

    private void checkQuestionnaire(@NonNull QuestionnaireDTO questionnaire, @NonNull Map<String, Object> questionnaireReplicationContent) {

        //  周期校验
        if (!DateUtil.isIn(new Date(), questionnaire.getQuestionnaireStartTime(), questionnaire.getQuestionnaireEndTime())) {
            //  上报时间不在问卷周期内
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_QUESTIONNAIRE_REPEAT_REPORT_CYCLE_EXCEPTION);
        }

        //  答案校验
        checkLegitimateQuestionnaireReplicationContent(questionnaire, questionnaireReplicationContent);
    }

    private void checkLegitimateQuestionnaireReplicationContent(@NonNull QuestionnaireDTO questionnaire, @NonNull Map<String, Object> questionnaireReplicationContent) {

        questionnaire.getQuestionnaireQuestionDTOS().forEach(questionnaireQuestionDTO -> {
            questionnaireQuestionDTO.getQuestionnaireQuestionItemDTOS().forEach(questionnaireQuestionItemDTO -> {

                //  细项 code
                String questionnaireQuestionItemCode = questionnaireQuestionItemDTO.getQuestionnaireQuestionItemCode();

                if (!questionnaireReplicationContent.containsKey(questionnaireQuestionItemCode)) {
                    String exceptionMessage = String.format("题为：《%s》 的问卷有答案未填！！！", questionnaireQuestionDTO.getQuestionnaireQuestionTitle());
                    throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_QUESTIONNAIRE_REPLICATION_CONTENT_CHECK_EXCEPTION, exceptionMessage);
                }
            });
        });
    }
}
