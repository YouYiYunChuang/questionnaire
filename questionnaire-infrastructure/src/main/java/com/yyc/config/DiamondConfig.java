package com.yyc.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuchengyao
 */
@Configuration
@ComponentScan(value = {

        "com.yyc.web",//    controller

        "com.yyc.user",
        "com.yyc.question",
        "com.yyc.questionnaire",
        "com.yyc.replication",  //  db实体

        "com.yyc.aop",  //  system-aop
        "com.yyc.handler",  //  system-handler
        "com.gitee.sunchenbin.mybatis.actable.manager.*",   //  system-actable

})
@MapperScan(value = {
        "com.gitee.sunchenbin.mybatis.actable.dao.*",   //  system-actable
        "com.yyc.user",
        "com.yyc.question",
        "com.yyc.questionnaire",
        "com.yyc.replication",  //  db实体
})
public class DiamondConfig {
    public final static String DummyConfig = "DummyConfig";

    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        return paginationInnerInterceptor;
    }
}