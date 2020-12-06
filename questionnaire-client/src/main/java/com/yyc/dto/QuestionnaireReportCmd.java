package com.yyc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuchengyao
 */
@Data
public class QuestionnaireReportCmd {

    /**
     * 上报人 openId
     */
    private String openId;

    /**
     * 回答内容
     */
    @NotNull(message = "内容不可为空")
    private String questionnaireReplicationContent;

    /**
     * 问卷code
     */
    @NotNull(message = "问卷code")
    private String questionnaireCode;

}
