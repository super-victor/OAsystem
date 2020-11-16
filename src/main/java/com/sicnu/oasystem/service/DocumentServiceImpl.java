package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.DocumentMapper;
import com.sicnu.oasystem.pojo.ReceiveFile;
import com.sicnu.oasystem.pojo.SendFile;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
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

    @Resource
    DocumentMapper documentMapper;

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
    public BackFrontMessage createDocument(Integer type, String title, String content, Integer sendPersonNum, String remark, Integer censorId, Integer isUrgent, MultipartFile file, List<Integer> receiverIdList) {
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

        SendFile sendFile = new SendFile();
        sendFile.setType(type);
        sendFile.setTitle(title);
        sendFile.setAnnexUrl(filename);
        sendFile.setContext(content);
        sendFile.setSendPsrsonNum(sendPersonNum);
        sendFile.setIsUrgent(isUrgent);
        sendFile.setRemark(remark);
        sendFile.setSenderId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        sendFile.setCensorId(censorId);
        sendFile.setStatus(0);

        if (documentMapper.insertSendFile(sendFile) != 0) {
            ReceiveFile receiveFile = new ReceiveFile();
            receiveFile.setSendFileId(sendFile.getSendfileId());
            receiveFile.setIsRecceived(0);
            for (int i = 0;i < sendPersonNum; i++) {
                receiveFile.setReceiverId(receiverIdList.get(i));
                documentMapper.insertReceiveFile(receiveFile);
            }
            return new BackFrontMessage(200,"创建成功",null);
        } else {
            new File(path + filename).delete();
            return new BackFrontMessage(500, "创建失败", null);
        }
    }
}
