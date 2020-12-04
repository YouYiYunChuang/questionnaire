package com.yyc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuchengyao
 */
@Configuration
@ComponentScan(value = {
        "com.yyc.web",
        "com.yyc.question",
        "com.yyc.questionnaire",
        "com.yyc.replication",
        "com.gitee.sunchenbin.mybatis.actable.manager.*",
        "com.yyc.sms.handler"
})
@MapperScan(value = {
        "com.gitee.sunchenbin.mybatis.actable.dao.*",
        "com.yyc.sms.sms",
        "com.yyc.sms.configuration"
})
public class DiamondConfig {
    public final static String DummyConfig = "DummyConfig";
}