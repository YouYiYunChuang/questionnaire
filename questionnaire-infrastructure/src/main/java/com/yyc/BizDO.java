package com.yyc;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 业务基础模型
 *
 * @author yuchengyao
 */
@Data
public class BizDO extends BaseDO {

    /**
     * 数据状态
     */
    @Column(name = "status", type = MySqlTypeConstant.INT, length = 3, comment = "数据状态")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private String status;

    /**
     * 创建人
     */
    @Column(name = "create_by", type = MySqlTypeConstant.VARCHAR, comment = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by", type = MySqlTypeConstant.VARCHAR, comment = "更新人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time", type = MySqlTypeConstant.TIMESTAMP, comment = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", type = MySqlTypeConstant.TIMESTAMP, comment = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
