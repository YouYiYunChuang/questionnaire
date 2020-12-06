package com.yyc.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.yyc.BaseDO;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
@TableName(value = "user")
@Table(name = "user")
public class UserDO extends BaseDO {

    /**
     * 公开id
     */
    @Unique
    @Column(name = "open_id", type = MySqlTypeConstant.VARCHAR, comment = "微信openId")
    @TableField(value = "open_id")
    private String openId;
}
