package com.sicnu.oasystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName GlobalSecurityUrl
 * @Description 从配置文件中读取全局安全配置
 * @Author JohnTang
 * @LastChangeDate 2020/11/26 9:47
 * @Version v1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "global-security-config")
public class GlobalSecurityConfig {
    List<String> allowUrlList;
}
