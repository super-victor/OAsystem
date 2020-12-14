package com.sicnu.oasystem.service.document;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    /**
     * @MethodName getSelfDraftBoxDocument
     * @param
     * @Description 获取自己草稿箱的文档
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getSelfDraftBoxDocument();

    /**
     * @MethodName getSelfUnPassDocument
     * @param
     * @Description 获取自己未通过的文档
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getSelfUnPassDocument();

    /**
     * @MethodName deleteDocument
     * @param sendfileId
     * @Description 删除文档
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage deleteDocument(Integer sendfileId);

    /**
     * @MethodName deleteDocumentAnnex
     * @param sendfileId
     * @param annexUrl
     * @Description 删除文档的附件
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage deleteDocumentAnnex(int sendfileId,String annexUrl);

    /**
     * @MethodName uploadDocumentAnnex
     * @param sendfileId
     * @param file
     * @Description 上传文档附件
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage uploadDocumentAnnex(Integer sendfileId, MultipartFile file);

    /**
     * @MethodName getDocumentDetail
     * @param sendfileId
     * @Description 获取文档详情
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
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

    /**
     * @MethodName commitDocument
     * @param sendfileId
     * @Description 提交文档审核
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage commitDocument(Integer sendfileId);

    /**
     * @MethodName checkDocument
     * @param sendfileId
     * @param ispassed
     * @param comment
     * @Description 审核公文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage checkDocument(Integer sendfileId,int ispassed,String comment);

    /**
     * @MethodName cancelCheck
     * @param sendfileId
     * @Description 取消自己提交的文档审核
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage cancelCheck(Integer sendfileId);

    /**
     * @MethodName needCheckDocument
     * @param
     * @Description 审查人获取自己需要审查的文档列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage needCheckDocument();

    /**
     * @MethodName getAllPublishDocument
     * @param
     * @Description 获取所有已发布的文档
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getAllPublishDocument();

    /**
     * @MethodName getSelfPublishDocument
     * @param
     * @Description 获取自己已发布的文档
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getSelfPublishDocument();

    /**
     * @MethodName putDocumentNotPassIntoDraftbox
     * @param sendfileId
     * @Description 将未通过的文档放进草稿箱
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage putDocumentNotPassIntoDraftbox(int sendfileId);

    Map<String,List> documentSeeAbleData();
}
