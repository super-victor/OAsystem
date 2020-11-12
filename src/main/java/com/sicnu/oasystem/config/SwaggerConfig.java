package com.sicnu.oasystem.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 10:07
 * @Version v1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig implements ApiListingScannerPlugin {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sicnu.oasystem.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                         new ApiInfoBuilder()
                        .title("协同办公系统后端api")
                        .description("协同办公系统后端api")
                        .version("1.0")
                        .contact(new Contact("潘勇","www.baidu.com","aaa@gmail.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build()
                )
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList<>();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("登录")
                .notes("用户名密码登录")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
                .tags(Sets.newHashSet("登录"))
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .description("用户名")
                                .type(new TypeResolver().resolve(String.class))
                                .name("username")
                                .defaultValue("2238192070@qq.com")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .description("密码")
                                .type(new TypeResolver().resolve(String.class))
                                .name("password")
                                .defaultValue("tangliyi123")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build()
                ))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("登录成功")
                                .responseModel(null
                                ).build()))
                .build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口",
                Arrays.asList(usernamePasswordOperation), false);

        return Arrays.asList(loginApiDescription);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
