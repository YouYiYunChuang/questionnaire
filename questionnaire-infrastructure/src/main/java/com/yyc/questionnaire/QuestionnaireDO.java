package com.yyc.questionnaire;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.BizDO;
import lombok.Data;

import java.util.Date;

/**
 * 问卷模型
 *
 * @author yuchengyao
 */
@Data
@TableName(value = "questionnaire")
@Table(name = "questionnaire")
public class QuestionnaireDO extends BizDO {

    /**
     * 问卷code
     */
    @Unique
    @Column(name = "questionnaire_code", type = MySqlTypeConstant.VARCHAR, comment = "问卷code")
    @TableField(value = "questionnaire_code")
    private String questionnaireCode;

    /**
     * 问卷标题
     */
    @Column(name = "questionnaire_title", type = MySqlTypeConstant.VARCHAR, comment = "问卷标题")
    @TableField(value = "questionnaire_title")
    private String questionnaireTitle;

    /**
     * 副标题
     */
    @Column(name = "questionnaire_subtitle", type = MySqlTypeConstant.VARCHAR, length = 1024, comment = "副标题")
    @TableField(value = "questionnaire_subtitle")
    private String questionnaireSubtitle;

    /**
     * 问卷署名:问卷结尾显示的落款
     */
    @Column(name = "questionnaire_signature", type = MySqlTypeConstant.VARCHAR, length = 1024, comment = "问卷署名:问卷结尾显示的落款")
    @TableField(value = "questionnaire_signature")
    private String questionnaireSignature;

    /**
     * 问卷开始时间
     */
    @Column(name = "questionnaire_start_time", type = MySqlTypeConstant.VARCHAR, comment = "问卷开始时间")
    @TableField(value = "questionnaire_start_time")
    private Date questionnaireStartTime;

    /**
     * 问卷结束时间
     */
    @Column(name = "questionnaire_end_time", type = MySqlTypeConstant.VARCHAR, comment = "问卷结束时间")
    @TableField(value = "questionnaire_end_time")
    private Date questionnaireEndTime;
}
