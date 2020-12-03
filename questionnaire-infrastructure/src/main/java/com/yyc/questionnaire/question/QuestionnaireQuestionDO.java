package com.yyc.questionnaire.question;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.questionnaire.BaseDO;
import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题模型
 *
 * @author yuchengyao
 */
@Data
@Builder
@TableName(value = "questionnaire_question")
@Table(name = "questionnaire_question")
public class QuestionnaireQuestionDO extends BaseDO {

    /**
     * 问卷code
     */
    @Column(name = "questionnaire_code", type = MySqlTypeConstant.VARCHAR, comment = "问卷code")
    @TableField(value = "questionnaire_code")
    private String questionnaireCode;

    /**
     * 问题code
     */
    @Column(name = "questionnaire_question_code", type = MySqlTypeConstant.VARCHAR, comment = "问题code")
    @TableField(value = "questionnaire_question_code")
    private String questionnaireQuestionCode;

    /**
     * 问题序号
     */
    @Column(name = "questionnaire_question_sort", type = MySqlTypeConstant.INT, length = 4, comment = "问题序号")
    @TableField(value = "questionnaire_question_sort")
    private Integer questionnaireQuestionSort;

    /**
     * 问题名称
     */
    @Column(name = "questionnaire_question_title", type = MySqlTypeConstant.VARCHAR, length = 1024, comment = "问题名称")
    @TableField(value = "questionnaire_question_title")
    private String questionnaireQuestionTitle;

    /**
     * 问题内容，填空题，问题项框架
     */
    @Column(name = "questionnaire_question_content", type = MySqlTypeConstant.VARCHAR, length = 4096, comment = "问题内容，填空题，问题项框架")
    @TableField(value = "questionnaire_question_content")
    private String questionnaireQuestionContent;
}
