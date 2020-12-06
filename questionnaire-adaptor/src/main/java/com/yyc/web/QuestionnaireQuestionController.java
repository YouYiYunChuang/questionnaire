package com.yyc.web;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.access.Access;
import com.yyc.access.Role;
import com.yyc.api.QuestionnaireQuestionServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Api(value = "问卷问题相关接口", tags = {"问卷问题相关接口"})
@RestController
@RequestMapping("questionnaire-question")
public class QuestionnaireQuestionController {

    @Resource
    private QuestionnaireQuestionServiceI questionnaireQuestionServiceI;

    /**
     * 问卷问题类型列表接口
     *
     * @return
     */
    @ApiOperation(value = "问卷问题类型列表接口", notes = "问卷问题类型列表接口")
    @Access(roles = {
            Role.GENERAL_USER,
            Role.ADMIN
    })
    @GetMapping("type")
    public MultiResponse<Map<String, String>> listQuestionnaireQuestionType() {
        return MultiResponse.ofWithoutTotal(questionnaireQuestionServiceI.listQuestionnaireQuestionType());
    }

}
