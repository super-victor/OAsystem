package com.sicnu.oasystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName FileUtil
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/18 17:24
 * @Version v1.0
 */

public class FileUtil {
    /**
     * @MethodName saveFileAtLocalDefaultPath
     * @param multipartFile
     * @param path
     * @Description 将文件以一个随机的名字保存在提供的本地目录
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/11/18
     */
    public static String saveFileAtLocalDefaultPath(MultipartFile multipartFile, String path) throws IOException {
        // 找出文件类型
        String[] items = multipartFile.getOriginalFilename().split(".");
        String fileType = items[items.length - 1];

        //生成新的文件名字
        String newFileName = UUID.randomUUID().toString() + "." + fileType;
        File newFile = new File(path, newFileName);
        multipartFile.transferTo(newFile);
        return newFileName;
    }
}
