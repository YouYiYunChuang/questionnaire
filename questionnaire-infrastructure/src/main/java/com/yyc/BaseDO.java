package com.yyc;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * BaseDO
 * 数据基础类,提供默认字段：
 * <br/>id  主键
 * <br/>createBy    创建人
 * <br/>updateBy    更新人
 * <br/>createTime  创建时间
 * <br/>updateTime  更新时间
 *
 * @author 10916
 */
@Data
public class BaseDO {

    /**
     * id
     */
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, length = 50, isKey = true, comment = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

}
