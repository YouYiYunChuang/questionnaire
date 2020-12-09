package com.yyc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

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
    private Map<String, Object> questionnaireReplicationContent;

    /**
     * 问卷code
     */
    @NotNull(message = "问卷code")
    private String questionnaireCode;

}
