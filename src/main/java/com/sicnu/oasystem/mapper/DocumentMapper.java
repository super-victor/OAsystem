package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.DataSeeAbleA;
import com.sicnu.oasystem.pojo.SendFile;
import com.sicnu.oasystem.pojo.limit.EmployeeLimitA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName DocumentMapper
 * @Description 收发文有关mapper
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 19:10
 * @Version v1.0
 */

@Mapper
public interface DocumentMapper {

    /**
     * @MethodName insertSendFile
     * @param sendFile
     * @Description 添加发文
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/18
     */
    int insertSendFile(SendFile sendFile);

    /**
     * @MethodName findEmptySendFileByEmployeeId
     * @param employeeId
     * @Description 通过员工id找空发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.SendFile
     * @LastChangeDate 2020/12/11
     */
    SendFile findEmptySendFileByEmployeeId(int employeeId);

    /**
     * @MethodName findEmployeeIdsInDocumentAcess
     * @param sendfileId
     * @Description 通过发文id找发布范围内的所有员工id
     * @Author JohnTang
     * @Return java.util.List<java.lang.Integer>
     * @LastChangeDate 2020/12/11
     */
    List<Integer> findEmployeeIdsInDocumentAcess(int sendfileId);

    /**
     * @MethodName gethaveCheckAbilityEmployList
     * @param
     * @Description 获取有审查能力的员工列表
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.limit.EmployeeLimitA>
     * @LastChangeDate 2020/12/11
     */
    List<EmployeeLimitA> gethaveCheckAbilityEmployList();

    /**
     * @MethodName ishaveCheckAbility
     * @param employeeId
     * @Description 判断是否有审查能力
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int ishaveCheckAbility(int employeeId);

    /**
     * @MethodName insertDocumentAcess
     * @param sendfileId
     * @param employeeId
     * @Description 添加文档权限
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int insertDocumentAcess(int sendfileId,int employeeId);

    /**
     * @MethodName deleteDocumentAcess
     * @param sendfileId
     * @Description 删除文档权限通过sendfileId
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int deleteDocumentAcess(int sendfileId);

    /**
     * @MethodName deleteDocumentAcess
     * @param sendfileId
     * @Description 删除文档权限通过SendfileIdAndEmployeeId
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int deleteDocumentAcessBySendfileIdAndEmployeeId(int sendfileId,int employeeId);

    /**
     * @MethodName updateStatusAndCommentInSendFile
     * @param sendfileId
     * @param status
     * @param comment
     * @Description 根据sendfileId更新发文的status和comment
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int updateStatusAndCommentInSendFile(int sendfileId,int status, String comment);

    /**
     * @MethodName updateSendFileBySendFile
     * @param sendFile
     * @Description 用户更新自己的发文
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/18
     */
    int updateSendFileBySendFile(SendFile sendFile);

    /**
     * @MethodName findSendFileNeedCheck
     * @param censorId
     * @Description 找出需要审查的发文
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.SendFile>
     * @LastChangeDate 2020/12/14
     */
    List<SendFile> findSendFileNeedCheck(int censorId);

    /**
     * @MethodName findAnnexUrlsBySendfileId
     * @param sendfileId
     * @Description 根据发文id找出所有的附件url
     * @Author JohnTang
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/12/14
     */
    List<String> findAnnexUrlsBySendfileId(Integer sendfileId);

    /**
     * @MethodName deleteDocumentAnnexByannexUrl
     * @param annexUrl
     * @Description 删除附件
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int deleteDocumentAnnexByannexUrl(String annexUrl);

    /**
     * @MethodName insertDocumentAnnex
     * @param sendfileId
     * @param annexUrl
     * @Description 增加附件
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int insertDocumentAnnex(int sendfileId, String annexUrl);

    /**
     * @MethodName findSendFileByEmployeeIdAndSendfileId
     * @param employeeId
     * @param sendfileId
     * @Description 用户找自己的发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.SendFile
     * @LastChangeDate 2020/11/18
     */
    SendFile findSendFileByEmployeeIdAndSendfileId(Integer employeeId, Integer sendfileId);

    /**
     * @MethodName findUnReviewSendFile
     * @param
     * @Description 找出未审核的发文
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.SendFile>
     * @LastChangeDate 2020/12/14
     */
    List<SendFile> findUnReviewSendFile();

    /**
     * @MethodName updateStatusInSendFile
     * @param sendfileId
     * @param status
     * @Description 更新发文的状态
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int updateStatusInSendFile(int sendfileId,int status);

    /**
     * @MethodName checkedReceiveFile
     * @param receivefileId
     * @Description 确认收到
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/28
     */
    int checkedReceiveFile(int receivefileId);

    /**
     * @MethodName findSendFileBySendfileId
     * @param sendfileId
     * @Description 根据发文id找发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.SendFile
     * @LastChangeDate 2020/12/14
     */
    SendFile findSendFileBySendfileId(int sendfileId);

    /**
     * @MethodName findSendFileByEmployeeIdAndStatus
     * @param employeeId
     * @param status
     * @Description 找出该员工特定状态的发文
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.SendFile>
     * @LastChangeDate 2020/12/14
     */
    List<SendFile> findSendFileByEmployeeIdAndStatus(int employeeId, int status);

    /**
     * @MethodName findAccessEmployeesBySendfileId
     * @param sendfileId
     * @Description 找出发文的所有的权限用户
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.limit.EmployeeLimitA>
     * @LastChangeDate 2020/12/14
     */
    List<EmployeeLimitA> findAccessEmployeesBySendfileId(int sendfileId);

    /**
     * @MethodName deleteSendFile
     * @param sendfileId
     * @Description 删除发文
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int deleteSendFile(int sendfileId);

    /**
     * @MethodName findAllPublishSendFile
     * @param
     * @Description 找出所有已发布的发文
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.SendFile>
     * @LastChangeDate 2020/12/14
     */
    List<SendFile> findAllPublishSendFile();

    /**
     * @MethodName findSelfPublishSendFile
     * @param senderId
     * @Description 找出自己发布的所有发文
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.SendFile>
     * @LastChangeDate 2020/12/14
     */
    List<SendFile> findSelfPublishSendFile(int senderId);

    List<DataSeeAbleA> findDateAndCountAboutPublishDocuments();

    List<DataSeeAbleA> findDateAndCountAboutPublishDocumentsByDepartmentName(String departmentName);

    List<DataSeeAbleA> findDateAndCountAboutDraftBoxDocuments();

    List<DataSeeAbleA> findDateAndCountAboutDraftBoxDocumentsByDepartmentName(String departmentName);

    List<DataSeeAbleA> findDateAndCountAboutSelfPublishDocuments(int employeeId);

    List<DataSeeAbleA> findDateAndCountAboutSelfDraftBoxDocuments(int employeeId);
}
