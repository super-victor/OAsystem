package com.sicnu.oasystem.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @ClassName SwaggerConfig
 * @Description Swagger配置类
 * @Author JohnTang
 * @LastChangeDate 2020/11/6 15:09
 * @Version v1.0
 */


@Configuration
public class SwaggerConfig {

    @Value("${myswagger.title}")
    private String title;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description("协同办公系统的后台api接口")
                .contact(new Contact("panyong", "www.baidu.com", "1358844623@qq.com"))
                .version("1.0")
                .build();
    }



}
