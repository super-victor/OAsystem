package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.DocumentMapper;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.ReceiveFile;
import com.sicnu.oasystem.pojo.Role;
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

    @Resource
    EmployeeMapper employeeMapper;

    @Value("${document.path}")
    private String path;

    /**
     * @MethodName getAllCensors
     * @Description 获取所有的审查人
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    @Override
    public BackFrontMessage getAllCensors() {
        return new BackFrontMessage(200, "获取成功", employeeMapper.findEmployeesByRoleId(2));
    }

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
        // 上传文件
        if (file == null || file.isEmpty()) {
            return new BackFrontMessage(500,"上传失败1",null);
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
            return new BackFrontMessage(500,"上传失败2",null);
        }

        // 创建发文
        SendFile sendFile = new SendFile();
        sendFile.setType(type);
        sendFile.setTitle(title);
        sendFile.setAnnexUrl(filename);
        sendFile.setContext(content);
        sendFile.setSendPsrsonNum(sendPersonNum);
        sendFile.setIsUrgent(isUrgent);
        sendFile.setRemark(remark);
        sendFile.setSenderId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        // 检查审查人id是否有Role_Centor角色
        List<Role> roles = employeeMapper.findRolesByEmployeeId(censorId);
        boolean flag = false;
        for (Role role : roles) {
            if (role.getRoleId() == 2) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return new BackFrontMessage(500, "创建失败1", null);
        }
        sendFile.setCensorId(censorId);
        sendFile.setStatus(0);

        // 添加发文
        if (documentMapper.insertSendFile(sendFile) != 0) {
            // 添加收文的人
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
            return new BackFrontMessage(500, "创建失败2", null);
        }
    }

    /**
     * @param sendfileId
     * @param title
     * @param content
     * @param annexUrl
     * @param remark
     * @param multipartFile
     * @MethodName updateDocument
     * @Description 修改发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    @Override
    public BackFrontMessage updateDocument(Integer sendfileId, String title, String content, String annexUrl, String remark, MultipartFile multipartFile) {
        return null;
    }
}
