package com.yyc.web;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.access.Access;
import com.yyc.access.Role;
import com.yyc.api.QuestionnaireQuestionItemServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Api(value = "问卷问题细项相关接口", tags = {"问卷问题细项相关接口"})
@RequestMapping("questionnaire-question-item")
public class QuestionnaireQuestionItemController {

    @Resource
    private QuestionnaireQuestionItemServiceI questionnaireQuestionItemServiceI;

    /**
     * 问卷问题细项类型列表接口
     *
     * @return
     */
    @ApiOperation(value = "问卷问题细项类型列表接口", notes = "问卷问题细项类型列表接口")
    @Access(roles = {
            Role.GENERAL_USER,
            Role.ADMIN
    })
    @GetMapping("type")
    public MultiResponse<Map<String, String>> listQuestionnaireQuestionItemType() {
        return MultiResponse.ofWithoutTotal(questionnaireQuestionItemServiceI.listQuestionnaireQuestionItemType());
    }
}
