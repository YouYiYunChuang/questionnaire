package com.yyc.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 问卷调查接口
 *
 * @author yuchengyao
 */
@RestController
@RequestMapping("questionnaire")
public class QuestionnaireController {

    @Resource
    private QuestionnaireServiceI questionnaireServiceI;

    /**
     * 问卷调查新增（问题新增、问题细项新增）接口
     *
     * @return
     */
    @PostMapping
    public Response insertQuestionnaire(@RequestBody @Valid QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireServiceI.insertQuestionnaire(questionnaireInsertCmd);
        return Response.buildSuccess();
    }

    /**
     * 问卷调查列表（搜索）接口
     *
     * @return
     */
    @GetMapping
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@ModelAttribute QuestionnaireQry questionnaireInsertQry) {
        return questionnaireServiceI.listQuestionnaires(questionnaireInsertQry);
    }

    /**
     * 问卷详情（问题、问题细项）接口
     *
     * @param questionnaireCode 问卷code
     * @return
     */
    @GetMapping("{questionnaireCode}")
    public SingleResponse<QuestionnaireDTO> getQuestionnaire(@PathVariable String questionnaireCode) {
        return SingleResponse.of(questionnaireServiceI.getQuestionnaire(questionnaireCode));
    }

    /**
     * 问卷停用接口
     *
     * @param id 问卷唯一标识
     * @return
     */
    @PutMapping("deactivate")
    public Response deactivateQuestionnaire(@PathVariable String id) {
        questionnaireServiceI.deactivateQuestionnaire(id);
        return Response.buildSuccess();
    }

    /**
     * 问卷填报接口
     *
     * @param questionnaireReportCmd
     * @return
     */
    @PostMapping("report")
    public Response reportQuestionnaire(@RequestBody QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireServiceI.reportQuestionnaire(questionnaireReportCmd);
        return Response.buildSuccess();
    }


}
