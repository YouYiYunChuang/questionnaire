package com.yyc.domain.questionnaire.questionnaire;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 问卷模型
 */
@Data
@Builder
public class Questionnaire {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 问卷标题
     */
    private String questionnaireTitle;

    /**
     * 副标题
     */
    private String questionnaireSubtitle;

    /**
     * 问卷署名:问卷结尾显示的落款
     */
    private String questionnaireSignature;

    /**
     * 问卷开始时间
     */
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    private Date questionnaireEndTime;
}
