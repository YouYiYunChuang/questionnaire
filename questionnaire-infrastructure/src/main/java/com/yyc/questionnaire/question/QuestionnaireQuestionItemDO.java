package com.yyc.questionnaire.question;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.questionnaire.BaseDO;
import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题细项
 *
 * @author yuchengyao
 */
@Data
@Builder
@TableName(value = "questionnaire_question_item")
@Table(name = "questionnaire_question_item")
public class QuestionnaireQuestionItemDO extends BaseDO {

    /**
     * 问题code
     */
    @Column(name = "questionnaire_question_code", type = MySqlTypeConstant.VARCHAR, comment = "问题code")
    @TableField(value = "id")
    private String questionnaire_question_code;

    /**
     * 细项排序
     */
    @Column(name = "questionnaire_question_item_sort", type = MySqlTypeConstant.INT, length = 4, comment = "细项排序")
    @TableId(value = "questionnaire_question_item_sort")
    private Integer questionnaireQuestionItemSort;

    /**
     * 细项类型code：单选、多选、填空等
     */
    @Column(name = "questionnaire_question_type_code", type = MySqlTypeConstant.VARCHAR, comment = "细项类型code：单选、多选、填空等")
    @TableId(value = "questionnaire_question_type_code")
    private String questionnaireQuestionTypeCode;

    /**
     * 细项内容
     */
    @Column(name = "questionnaire_question_item_content", type = MySqlTypeConstant.VARCHAR, comment = "细项内容")
    @TableId(value = "questionnaire_question_item_content")
    private String questionnaireQuestionItemContent;

}
