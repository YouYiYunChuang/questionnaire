package com.yyc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuchengyao
 */
@Configuration
@ComponentScan(value = {
        "com.yyc.base"
})
public class BaseConfig {

}
