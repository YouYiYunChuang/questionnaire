package com.yyc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuchengyao
 */
@Data
public class QuestionnaireQuestionInsertCmd {

    /**
     * 问卷code
     */
    private String questionnaireCode;

    /**
     * 问题code
     */
    private String questionnaireQuestionCode;

    /**
     * 问题序号
     */
    @NotNull(message = "问题序号不可以为空")
    private Integer questionnaireQuestionSort;

    /**
     * 问题名称
     */
    @NotNull(message = "问题名称不可以为空")
    private String questionnaireQuestionTitle;

    /**
     * 问题内容，填空题，问题项框架
     */
    @NotNull(message = "问题内容不可以为空")
    private String questionnaireQuestionContent;

    /**
     * 问题细项列表
     */
    @NotNull(message = "问题细项不可为空")
    private List<QuestionnaireQuestionItemInsertCmd> questionnaireQuestionItemInsertCmdList;
}
