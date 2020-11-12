package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * @ClassName DocumentServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 15:06
 * @Version v1.0
 */

@Service
public class DocumentServiceImpl implements DocumentService{

    @Value("${document.path}")
    private String path;

    /***
     * @MethodName createDocument
     * @param type
     * @param title
     * @param content
     * @param sendPersonNum
     * @param remark
     * @param censorId
     * @param isUrgent
     * @param file
     * @Description 创建收发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/12
     */
    @Override
    public BackFrontMessage createDocument(String type, String title, String content, String sendPersonNum, String remark, int censorId, int isUrgent, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new BackFrontMessage(500,"上传失败",null);
        }
        String filename = UUID.randomUUID().toString();
        String[] items = file.getOriginalFilename().split(".");
        String fileType = items[items.length - 1];
        filename = filename + "." + fileType;
        File newFile = new File(path, filename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new BackFrontMessage(500,"上传失败",null);
        }

        return null;

    }
}
