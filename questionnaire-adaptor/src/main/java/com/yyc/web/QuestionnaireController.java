package com.yyc.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.yyc.access.Access;
import com.yyc.access.Role;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 问卷调查接口
 *
 * @author yuchengyao
 */
@Slf4j
@Api(value = "问卷相关接口", tags = {"问卷相关接口"})
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
    @ApiOperation(value = "问卷调查新增（问题新增、问题细项新增）接口", notes = "问卷调查新增（问题新增、问题细项新增）接口")
    @Access(roles = {
            Role.ADMIN
    })
    @PostMapping
    public Response insertQuestionnaire(@RequestBody @Valid QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireServiceI.insert(questionnaireInsertCmd);
        return Response.buildSuccess();
    }

    /**
     * 问卷调查列表（搜索）接口
     *
     * @return
     */
    @ApiOperation(value = "问卷调查列表（搜索）接口", notes = "问卷调查列表（搜索）接口")
    @Access(roles = {
            Role.GENERAL_USER,
            Role.ADMIN
    })
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
    @ApiOperation(value = "问卷详情（问题、问题细项）接口", notes = "问卷详情（问题、问题细项）接口")
    @Access(roles = {
            Role.GENERAL_USER,
            Role.ADMIN
    })
    @GetMapping("{questionnaireCode}")
    public SingleResponse<QuestionnaireDTO> getQuestionnaire(@PathVariable String questionnaireCode) {
        return SingleResponse.of(questionnaireServiceI.getQuestionnaire(questionnaireCode));
    }

    /**
     * 问卷停用接口
     *
     * @param questionnaireCode 问卷唯一标识
     * @return
     */
    @ApiOperation(value = "问卷停用接口", notes = "问卷停用接口")
    @Access(roles = {
            Role.ADMIN
    })
    @PutMapping("deactivate")
    public Response deactivateQuestionnaire(@PathVariable String questionnaireCode) {
        questionnaireServiceI.deactivateQuestionnaire(questionnaireCode);
        return Response.buildSuccess();
    }

    /**
     * 问卷填报接口
     *
     * @param questionnaireReportCmd
     * @return
     */
    @ApiOperation(value = "问卷填报接口", notes = "问卷填报接口")
    @Access(roles = {
            Role.GENERAL_USER,
            Role.ADMIN
    })
    @PostMapping("report")
    public Response reportQuestionnaire(@RequestBody @Valid QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireServiceI.reportQuestionnaire(questionnaireReportCmd);
        return Response.buildSuccess();
    }


    /**
     * 分享问卷
     *
     * @param scene
     * @return
     */
    @GetMapping("share")
    public SingleResponse<byte[]> shareQuestionnaire(String scene, String page) throws Exception {
        return SingleResponse.of(questionnaireServiceI.shareQuestionnaire(scene, page));
    }


}
