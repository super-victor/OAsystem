package com.sicnu.oasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


// 协同办公系统
@SpringBootApplication
public class OasystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasystemApplication.class, args);
    }

}

// 待办清单
// todo
//  增加一个超级管理员角色，将权限管理抽离出来，
//  使用方法注解的形式进行权限控制,提供两个接口，一个是增加管理员和删除管理员
//  普通的用户角色映射将不能修改管理员角色
