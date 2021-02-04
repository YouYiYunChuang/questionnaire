package com.yyc.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yyc.access.AppContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 基础字段处理器
 *
 * @author yuchengyao
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 使用mp实现添加操作,这个方法会执行,metaObject元数据(表中的名字,表中的字段)
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值

        //  数据创建人
        this.setFieldValByName("createBy", new Date(), metaObject);
        //  创建时间
        this.setFieldValByName("createTime", new Date(), metaObject);

        //  更新人
        this.setFieldValByName("updateBy", AppContext.getTokenDTO().getOpenId(), metaObject);
        //  更新时间
        this.setFieldValByName("updateTime", new Date(), metaObject);

        //  数据状态
        this.setFieldValByName("status", new Date(), metaObject);

    }

    /**
     * 使用mp实现修改操作,这个方法会执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        //  更新人
        this.setFieldValByName("updateBy", AppContext.getTokenDTO().getOpenId(), metaObject);
        //  更新时间
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
