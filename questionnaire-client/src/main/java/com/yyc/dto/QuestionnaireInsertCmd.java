package com.yyc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author yuchengyao
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionnaireInsertCmd {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 场景
     */
    private String questionnaireScene;

    /**
     * 问卷标题
     */

    @NotNull(message = "问卷标题不可以为空")
    private String questionnaireTitle;

    /**
     * 副标题
     */
    @NotNull(message = "副标题不可以为空")
    private String questionnaireSubtitle;

    /**
     * 问卷署名:问卷结尾显示的落款
     */
    @NotNull(message = "问卷署名不可以为空")
    private String questionnaireSignature;

    /**
     * 问卷开始时间
     */
    @NotNull(message = "问卷开始时间不可以为空")
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    @NotNull(message = "问卷结束时间不可以为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date questionnaireEndTime;

    /**
     * 问卷问题列表
     */
    @NotNull(message = "问卷问题不可以为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<QuestionnaireQuestionInsertCmd> questionnaireQuestionInsertCmdList;
}
