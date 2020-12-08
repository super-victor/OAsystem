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
     * @MethodName getSelfUnCheckDocument
     * @param
     * @Description 审查人获取自己需要审查的发文列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    BackFrontMessage getSelfInCheckDocument();

    BackFrontMessage getSelfDraftBoxDocument();

    BackFrontMessage getSelfUnPassDocument();

    BackFrontMessage deleteDocument(Integer sendfileId);

    BackFrontMessage deleteDocumentAnnex(int sendfileId,String annexUrl);

    BackFrontMessage uploadDocumentAnnex(Integer sendfileId, MultipartFile file);

    BackFrontMessage getDocumentDetail(Integer sendfileId);

    /**
     * @MethodName createDocument
     * @param
     * @Description 创建空白发文返回id，有空白发文不需要创建直接返回id
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/2
     */
    BackFrontMessage createDocument();

    /**
     * @MethodName updateUncheckDocument
     * @param sendfileId
     * @param type
     * @param title
     * @param content
     * @param remark
     * @param censorId
     * @param isPublic
     * @param accessEmployeeIdList
     * @Description 第一次修改和草稿箱的修改复用，更新未检查的发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/3
     */
    BackFrontMessage updateUncheckDocument(Integer sendfileId,String type,String subject, String title, String content, String remark, Integer censorId, String urgent, Integer isPublic, List<Integer> accessEmployeeIdList);

    BackFrontMessage commitDocument(Integer sendfileId);

    BackFrontMessage checkDocument(Integer sendfileId,int ispassed,String comment);

    BackFrontMessage cancelCheck(Integer sendfileId);

    BackFrontMessage needCheckDocument();

    BackFrontMessage getAllPublishDocument();

    BackFrontMessage getSelfPublishDocument();

    BackFrontMessage putDocumentNotPassIntoDraftbox(int sendfileId);
}
