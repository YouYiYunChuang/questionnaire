package com.yyc.replication;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
     * 回答内容
     */
    @Column(name = "questionnaire_question_replication_content", type = MySqlTypeConstant.VARCHAR, comment = "回答内容")
    @TableId(value = "questionnaire_question_replication_content", type = IdType.ASSIGN_UUID)
    private String questionnaireQuestionReplicationContent;
}
