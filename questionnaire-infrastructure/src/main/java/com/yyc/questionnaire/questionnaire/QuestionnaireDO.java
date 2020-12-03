package com.yyc.questionnaire.questionnaire;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.questionnaire.BizDO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 问卷模型
 *
 * @author yuchengyao
 */
@Data
@Builder
@TableName(value = "questionnaire")
@Table(name = "questionnaire")
public class QuestionnaireDO extends BizDO {

    /**
     * 问卷code
     */
    @Column(name = "questionnaire_code", type = MySqlTypeConstant.VARCHAR, comment = "问卷code")
    @TableId(value = "questionnaire_code", type = IdType.ASSIGN_UUID)
    private String questionnaireCode;

    /**
     * 问卷标题
     */
    @Column(name = "questionnaire_title", type = MySqlTypeConstant.VARCHAR, comment = "问卷标题")
    @TableId(value = "questionnaire_title", type = IdType.ASSIGN_UUID)
    private String questionnaireTitle;

    /**
     * 副标题
     */
    @Column(name = "questionnaire_subtitle", type = MySqlTypeConstant.VARCHAR, length = 1024, comment = "副标题")
    @TableId(value = "questionnaire_subtitle", type = IdType.ASSIGN_UUID)
    private String questionnaireSubtitle;

    /**
     * 问卷署名:问卷结尾显示的落款
     */
    @Column(name = "questionnaire_signature", type = MySqlTypeConstant.VARCHAR, length = 1024, comment = "问卷署名:问卷结尾显示的落款")
    @TableId(value = "questionnaire_signature", type = IdType.ASSIGN_UUID)
    private String questionnaireSignature;

    /**
     * 问卷开始时间
     */
    @Column(name = "questionnaire_start_time", type = MySqlTypeConstant.VARCHAR, comment = "问卷开始时间")
    @TableId(value = "questionnaire_start_time", type = IdType.ASSIGN_UUID)
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    @Column(name = "questionnaire_end_time", type = MySqlTypeConstant.VARCHAR, comment = "问卷结束时间")
    @TableId(value = "questionnaire_end_time", type = IdType.ASSIGN_UUID)
    private Date questionnaireEndTime;
}
