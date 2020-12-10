package com.yyc.api;

import com.alibaba.cola.dto.MultiResponse;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;

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
     * @param questionnaireInsertQry
     * @return
     */
    MultiResponse<QuestionnaireDTO> listQuestionnaires(QuestionnaireQry questionnaireInsertQry);

    /**
     * 问卷详情
     *
     * @param id
     * @return
     */
    QuestionnaireDTO getQuestionnaire(String id);

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
     * @param questionnaireCode
     */
    void checkReportQuestionnaire(String questionnaireCode);


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
