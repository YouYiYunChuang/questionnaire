package com.yyc.questionnaire.executor;

import cn.hutool.core.date.DateUtil;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.domain.exception.QuestionnaireException;
import com.yyc.domain.exception.QuestionnaireExceptionCode;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yuchengyao
 */
@Component
public class QuestionnaireReportCheckExe {

    @Resource
    private QuestionnaireServiceI questionnaireServiceI;

    public void checkQuestionnaire(String openId, String questionnaireCode) {

        QuestionnaireDTO questionnaire = questionnaireServiceI.getQuestionnaire(questionnaireCode);

        checkQuestionnaire(questionnaire);
    }

    private void checkQuestionnaire(QuestionnaireDTO questionnaire) {

        if (!DateUtil.isIn(new Date(), questionnaire.getQuestionnaireStartTime(), questionnaire.getQuestionnaireEndTime())) {
            //  上报时间不在问卷周期内
            throw new QuestionnaireException(QuestionnaireExceptionCode.QUESTIONNAIRE_EXCEPTION_QUESTIONNAIRE_REPEAT_REPORT_CYCLE_EXCEPTION);
        }

    }
}
