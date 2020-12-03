package com.yyc.web;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.api.QuestionnaireQuestionServiceI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yuchengyao
 */
@RestController
@RequestMapping("questionnaire-question")
public class QuestionnaireQuestionController {

    @Resource
    private QuestionnaireQuestionServiceI questionnaireQuestionServiceI;

    /**
     * 问卷问题类型列表
     *
     * @return
     */
    @GetMapping("type")
    public MultiResponse<Map<String, String>> listQuestionnaireQuestionType() {
        return MultiResponse.ofWithoutTotal(questionnaireQuestionServiceI.listQuestionnaireQuestionType());
    }

}
