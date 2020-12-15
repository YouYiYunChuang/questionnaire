package com.yyc.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author yuchengyao
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionnaireDTO {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 场景码
     */
    private String questionnaireScene;

    /**
     * 问卷code
     */
    private String questionnaire;


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
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date questionnaireEndTime;

    /**
     * 问卷问题列表
     */
    private List<QuestionnaireQuestionDTO> questionnaireQuestionDTOS;

    /**
     * 问卷状态
     */
    private Integer status;
}
