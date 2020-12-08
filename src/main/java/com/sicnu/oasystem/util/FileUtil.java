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

    public static String saveInNewDirectory(MultipartFile multipartFile, String localDafaulPath, String relativePath, int maxSizeLimit) throws IOException, OverMaxFileSizeLimitException {
        if (multipartFile.getSize() > maxSizeLimit) {
            throw new OverMaxFileSizeLimitException("超出最大文件限制:此文件大小："+multipartFile.getSize()+"，限制大小"+maxSizeLimit);
        }
        File directory = new File(localDafaulPath+relativePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File newFile = new File(localDafaulPath+relativePath, multipartFile.getOriginalFilename());
        multipartFile.transferTo(newFile);
        return relativePath+"/"+multipartFile.getOriginalFilename();
    }

    public static String saveWithRandomName(MultipartFile multipartFile, String localDafaulPath, int maxSizeLimit) throws IOException, OverMaxFileSizeLimitException {
        if (multipartFile.getSize() > maxSizeLimit) {
            throw  new OverMaxFileSizeLimitException("超出最大文件限制:此文件大小："+multipartFile.getSize()+"，限制大小"+maxSizeLimit);
        }
        // 找出文件类型
        String[] items = multipartFile.getOriginalFilename().split("\\.");
        String fileType = items[items.length - 1];

        //生成新的文件名字
        String newFileName = UUID.randomUUID().toString() + "." + fileType;
        File newFile = new File(localDafaulPath, newFileName);
        multipartFile.transferTo(newFile);
        return newFileName;
    }

    public static boolean delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        //如果是一个文件
        if (file.isFile()) {
            return file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return file.delete();
            }else {
                for (File file1 : files) {
                    delete(file1.getAbsolutePath());
                }
                return file.delete();
            }
        }




    }
}
