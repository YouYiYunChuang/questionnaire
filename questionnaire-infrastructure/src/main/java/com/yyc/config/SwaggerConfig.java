package com.yyc.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 *
 * @author 10916
 */
@Configuration //声明该类为配置类
@EnableSwagger2 //声明启动Swagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        Predicate<RequestHandler> article = RequestHandlerSelectors.basePackage("com.yyc.web");

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()

                //  为当前包路径
                .apis(Predicates.or(article))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return springfox.documentation.service.ApiInfo
     * @Author ZuYong
     * @Description 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @Date 10:12 2020/5/30
     * @Param []
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("三务公开服务的swagger服务集成")
                // 创建人信息
                .contact(new Contact("yucheng.yao", "18447073720@qq.com", "18447073720@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }
}
