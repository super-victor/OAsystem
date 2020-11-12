package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName DocumentService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 11:02
 * @Version v1.0
 */

public interface DocumentService {
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
    BackFrontMessage createDocument(String type, String title, String content, String sendPersonNum, String remark, int censorId, int isUrgent, MultipartFile file);

}
