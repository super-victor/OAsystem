package com.sicnu.oasystem.config;

import java.io.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName CheckAfterAppRun
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/17 17:09
 * @Version v1.0
 */

@Slf4j
@Component
public class CheckAfterAppRun implements ApplicationRunner {

    @Value("${document.path}")
    private String localPath;

    @Override
    public void run(ApplicationArguments args) {
        File file = new File(localPath);
        if (!file.exists()) {
            File currentDir = new File(System.getProperty("user.dir")+"/oasystem");
            if (!currentDir.exists()) {
                if (currentDir.mkdir()) {
                    log.info("未检测到自定义文件路径，默认设置为"+currentDir.getAbsolutePath());
                } else {
                    log.warn("本地默认文件目录创建失败!!文件系统不可用!");
                }
            }else{
                log.info("未检测到自定义文件路径，默认设置为"+currentDir.getAbsolutePath());
            }
        } else {
            log.info("已检测到自定义文件路径: "+localPath);
        }
    }
}
