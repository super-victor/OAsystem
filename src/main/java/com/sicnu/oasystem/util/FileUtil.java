package com.sicnu.oasystem.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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

@Component
public class FileUtil{



    // 最终的本地路径
    private String localPath;

    // 用户自定义的本地路径
    @Value("${document.path}")
    private String customPath;

    @PostConstruct
    private void init() {
        File file = new File(customPath);
        if (file.exists()) {
            localPath = customPath;
        } else {
            localPath = System.getProperty("user.dir")+"/oasystem";
        }
    }

    public String getLocalPath() {
        return localPath;
    }

    /**
     * @MethodName saveFileInNewDirectory
     * @param multipartFile
     * @param relativePath
     * @param maxSizeLimit
     * @Description 将文件保存在系统文件目录下的新路径relativePath
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/17
     */
    public String saveFileInNewDirectory(MultipartFile multipartFile, String relativePath, int maxSizeLimit) throws IOException, OverMaxFileSizeLimitException {
        if (multipartFile.getSize() > maxSizeLimit) {
            throw new OverMaxFileSizeLimitException("超出最大文件限制:此文件大小："+multipartFile.getSize()+"，限制大小"+maxSizeLimit);
        }
        File directory = new File(localPath+relativePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File newFile = new File(localPath+relativePath, multipartFile.getOriginalFilename());
        multipartFile.transferTo(newFile);
        return relativePath+"/"+multipartFile.getOriginalFilename();
    }

    /**
     * @MethodName saveWithRandomName
     * @param multipartFile
     * @param maxSizeLimit
     * @Description 以随机的UUID为文件名保存文件
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/17
     */
    public String saveWithRandomName(MultipartFile multipartFile, int maxSizeLimit) throws IOException, OverMaxFileSizeLimitException {
        if (multipartFile.getSize() > maxSizeLimit) {
            throw  new OverMaxFileSizeLimitException("超出最大文件限制:此文件大小："+multipartFile.getSize()+"，限制大小"+maxSizeLimit);
        }
        // 找出文件类型
        String[] items = multipartFile.getOriginalFilename().split("\\.");
        String fileType = items[items.length - 1];

        //生成新的文件名字
        String newFileName = UUID.randomUUID().toString() + "." + fileType;
        File newFile = new File(localPath, newFileName);
        multipartFile.transferTo(newFile);
        return newFileName;
    }

    public boolean deleteLocalFile(String relativePath){
        return delete(localPath+relativePath);
    }

    /**
     * @MethodName delete
     * @param path
     * @Description 删除文件或者目录及目录下的所有文件
     * @Author JohnTang
     * @Return boolean
     * @LastChangeDate 2020/12/11
     */
    private boolean delete(String path) {
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
