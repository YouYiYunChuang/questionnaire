package com.yyc.questionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.yyc.questionnaire", "com.alibaba.cola" })
@MapperScan("com.yyc.questionnaire.com.yyc.questionnaire.gatewayimpl.database")
public class QuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }

}
