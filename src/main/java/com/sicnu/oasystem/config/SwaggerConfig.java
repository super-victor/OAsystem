package com.sicnu.oasystem.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;


/**
 * @ClassName SwaggerConfig
 * @Description Swagger配置类
 * @Author JohnTang
 * @LastChangeDate 2020/11/6 15:09
 * @Version v1.0
 */


@Configuration
public class SwaggerConfig implements ApiListingScannerPlugin {

    @Value("${myswagger.title}")
    private String title;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description("协同办公系统的后台api接口")
                .contact(new Contact("panyong", "www.baidu.com", "1358844623@qq.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new ApiKey("token", "Authorization", "header"));
        return securitySchemes;
    }

//    private List<ApiKey> securitySchemes() {
//        List<ApiKey> apiKeys = new ArrayList<>();
//        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
//        return apiKeys;
//    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add(MediaType.APPLICATION_JSON_VALUE);
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("登录");

        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("登录")
                .notes("登录")
                .consumes(hashSet1) // 接收参数格式
                .produces(hashSet2) // 返回参数格式
                .tags(hashSet3)
                .requestParameters(Arrays.asList(
                        new RequestParameterBuilder()
                                .description("用户名")
                                .name("username")
                                .required(true)
                                .parameterIndex(0)
                                .build(),
                        new RequestParameterBuilder()
                                .description("密码")
                                .name("password")
                                .required(true)
                                .parameterIndex(1)
                                .build()
                ))
                .build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口","登录", Arrays.asList(usernamePasswordOperation), false);

        return Arrays.asList(loginApiDescription);
    }

    //是否使用此插件
    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
