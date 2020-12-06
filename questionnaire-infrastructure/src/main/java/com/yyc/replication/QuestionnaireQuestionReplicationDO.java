package com.yyc.replication;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.BizDO;
import lombok.Data;

/**
 * 问卷问题回答表
 *
 * @author yuchengyao
 */
@Data
@TableName(value = "questionnaire_question_replication")
@Table(name = "questionnaire_question_replication")
public class QuestionnaireQuestionReplicationDO extends BizDO {

    /**
     * 问卷code
     */
    @Unique(columns = {
            "questionnaire_code",
            "open_id"
    })
    @Column(name = "questionnaire_code", type = MySqlTypeConstant.VARCHAR, comment = "问卷code")
    @TableField(value = "questionnaire_code")
    private String questionnaireCode;

    /**
     * 回答内容
     */
    @Column(name = "questionnaire_question_replication_content", type = MySqlTypeConstant.VARCHAR, length = 10000, comment = "回答内容")
    @TableField(value = "questionnaire_question_replication_content")
    private String questionnaireReplicationContent;

    /**
     * 问卷回答人openId
     */
    @Column(name = "open_id", type = MySqlTypeConstant.VARCHAR, comment = "问卷回答人openId")
    @TableField(value = "open_id")
    private String reportId;

}
