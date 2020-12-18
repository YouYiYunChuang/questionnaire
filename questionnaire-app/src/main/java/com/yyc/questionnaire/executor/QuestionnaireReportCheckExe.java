package com.yyc.questionnaire.executor;

import cn.hutool.core.date.DateUtil;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.domain.status.QuestionnaireQuestionType;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.dto.data.QuestionnaireQuestionDTO;
import com.yyc.dto.data.QuestionnaireQuestionItemDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

            //  单选题 or 多选题
            if (QuestionnaireQuestionType.SINGLE_CHOICE.name().equals(questionnaireQuestionDTO.getQuestionnaireQuestionType())
                    || QuestionnaireQuestionType.MULTIPLE_CHOICE.name().equals(questionnaireQuestionDTO.getQuestionnaireQuestionType())) {

                Set<String> questionnaireReplicationContentCodeSet = questionnaireQuestionDTO.getQuestionnaireQuestionItemDTOS().stream().collect(Collectors.toMap(QuestionnaireQuestionItemDTO::getQuestionnaireQuestionItemCode, value -> value, (a, b) -> a)).keySet();

                Set<String> allQuestionnaireReplicationContentCodeSet = questionnaireReplicationContent.keySet();

                boolean retainAll = questionnaireReplicationContentCodeSet.retainAll(allQuestionnaireReplicationContentCodeSet);

                throwQuestionnaireException(retainAll, questionnaireQuestionDTO);

                return;
            }

            //  填空题
            if (QuestionnaireQuestionType.FILL_IN_THE_BLANK.name().equals(questionnaireQuestionDTO.getQuestionnaireQuestionType())) {

                questionnaireQuestionDTO.getQuestionnaireQuestionItemDTOS().forEach(questionnaireQuestionItemDTO -> {
                    //  细项 code
                    String questionnaireQuestionItemCode = questionnaireQuestionItemDTO.getQuestionnaireQuestionItemCode();

                    if (!questionnaireReplicationContent.containsKey(questionnaireQuestionItemCode)) {

                        throwQuestionnaireException(false, questionnaireQuestionDTO);
                    }
                });

                return;
            }


        });
    }

    /**
     * @param retainAll
     * @param questionnaireQuestionDTO
     */
    private void throwQuestionnaireException(boolean retainAll, QuestionnaireQuestionDTO questionnaireQuestionDTO) {

        if (!retainAll) {
            String exceptionMessage = String.format("题为：《%s》 的问卷有答案未填！！！", questionnaireQuestionDTO.getQuestionnaireQuestionTitle());
            throw new QuestionnaireException(exceptionMessage);
        }

    }
}
