package com.sicnu.oasystem.service.document;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @InterfaceName DocumentService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 11:02
 * @Version v1.0
 */

public interface DocumentService {

    /**
     * @MethodName getAllCensors
     * @param
     * @Description 获取所有的审查人
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    BackFrontMessage getAllCensors();

    /**
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
    BackFrontMessage createDocument(Integer type, String title, String content, Integer sendPersonNum, String remark, Integer censorId, Integer isUrgent, MultipartFile file, List<Integer> receiverIdList);

    /**
     * @MethodName pdateNotPassDocument
     * @param sendfileId
     * @param title
     * @param content
     * @param remark
     * @param multipartFile
     * @Description 修改未通过发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    BackFrontMessage updateNotPassDocument(Integer sendfileId,String title, String content,String remark,MultipartFile multipartFile);

}
