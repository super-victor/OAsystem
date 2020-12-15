package com.sicnu.oasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;


// 协同办公系统
@SpringBootApplication
@EnableTransactionManagement
public class OasystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasystemApplication.class, args);
    }

}