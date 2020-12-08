package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.SendFile;
import com.sicnu.oasystem.pojo.limit.EmployeeLimitA;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.aop.target.SingletonTargetSource;

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

    SendFile findEmptySendFileByEmployeeId(int employeeId);

    List<Integer> findEmployeeIdsInDocumentAcess(int sendfileId);

    List<EmployeeLimitA> gethaveCheckAbilityEmployList();

    int ishaveCheckAbility(int employeeId);

    int insertDocumentAcess(int sendfileId,int employeeId);

    int deleteDocumentAcess(int sendfileId);

    int deleteDocumentAcessBySendfileIdAndEmployeeId(int sendfileId,int employeeId);

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

    List<SendFile> findSendFileNeedCheck(int censorId);

    List<String> findAnnexUrlsBySendfileId(Integer sendfileId);

    int deleteDocumentAnnexByannexUrl(String annexUrl);

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

    List<SendFile> findUnReviewSendFile();

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

    SendFile findSendFileBySendfileId(int sendfileId);

    List<SendFile> findSendFileByEmployeeIdAndStatus(int employeeId, int status);

    List<EmployeeLimitA> findAccessEmployeesBySendfileId(int sendfileId);

    int deleteSendFile(int sendfileId);

    List<SendFile> findAllPublishSendFile();

    List<SendFile> findSelfPublishSendFile(int senderId);
}
