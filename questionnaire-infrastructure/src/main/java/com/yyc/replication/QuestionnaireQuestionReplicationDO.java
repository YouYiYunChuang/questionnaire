package com.yyc.replication;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.BizDO;
import lombok.Builder;
import lombok.Data;

/**
 * 问卷问题回答表
 *
 * @author yuchengyao
 */
@Data
@Builder
@TableName(value = "questionnaire_question_replication")
@Table(name = "questionnaire_question_replication")
public class QuestionnaireQuestionReplicationDO extends BizDO {

    /**
     * 问卷code
     */
    @Column(name = "questionnaire_code", type = MySqlTypeConstant.VARCHAR, comment = "问卷code")
    @TableField(value = "questionnaire_code")
    private String questionnaireCode;

    /**
     * 回答内容
     */
    @Column(name = "questionnaire_question_replication_content", type = MySqlTypeConstant.VARCHAR, comment = "回答内容")
    @TableField(value = "questionnaire_question_replication_content")
    private String questionnaireReplicationContent;

    /**
     * 问卷回答人id
     */
    @Column(name = "report_id", type = MySqlTypeConstant.VARCHAR, comment = "问卷回答人id")
    @TableField(value = "report_id")
    private String reportId;
}
