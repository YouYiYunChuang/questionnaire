package com.yyc.api;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;

import java.util.Map;

/**
 * @author yuchengyao
 */
public interface QuestionnaireServiceI {

    /**
     * 问卷调查新增（问题新增、问题细项新增）接口
     *
     * @param questionnaireInsertCmd
     */
    void insert(QuestionnaireInsertCmd questionnaireInsertCmd);

    /**
     * 问卷列表
     *
     * @param questionnaireQry
     * @return
     */
    MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireQry);

    /**
     * 问卷详情
     *
     * @param questionnaireQry
     * @return
     */
    QuestionnaireDTO getQuestionnaire(QuestionnaireQry questionnaireQry);

    /**
     * 问卷停用
     *
     * @param id
     */
    void deactivateQuestionnaire(String id);

    /**
     * 问卷填报
     *
     * @param questionnaireReportCmd
     */
    void reportQuestionnaire(QuestionnaireReportCmd questionnaireReportCmd);

    /**
     * 问卷上报校验
     *
     * @param questionnaireCode               问卷code
     * @param questionnaireReplicationContent 问题上报内容
     */
    void checkReportQuestionnaire(String questionnaireCode, Map<String, Object> questionnaireReplicationContent);


    /**
     * 分享问卷
     *
     * @param scene
     * @param page
     * @return
     * @throws Exception
     */
    byte[] shareQuestionnaire(String scene, String page) throws Exception;
}
