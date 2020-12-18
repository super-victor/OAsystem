package com.sicnu.oasystem.config;

import com.sicnu.oasystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import java.lang.reflect.Field;

/**
 * @ClassName WebMvcConfig
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/2 18:26
 * @Version v1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    FileUtil fileUtil;

    // 设置下载url映射的本地路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/download/**")
                .addResourceLocations("file:"+fileUtil.getLocalPath());
    }

    // 设定controller接收mutipartfile的最大值
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        return factory.createMultipartConfig();
    }
}
