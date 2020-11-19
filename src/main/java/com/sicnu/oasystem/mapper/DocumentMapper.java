package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.ReceiveFile;
import com.sicnu.oasystem.pojo.SendFile;
import org.apache.ibatis.annotations.Mapper;

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
     * @MethodName insertReceiveFile
     * @param receiveFile
     * @Description 添加收文
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/18
     */
    int insertReceiveFile(ReceiveFile receiveFile);

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
     * @MethodName findSendFileByEmployeeIdAndSendfileId
     * @param employeeId
     * @param sendfileId
     * @Description 用户找自己的发文
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.SendFile
     * @LastChangeDate 2020/11/18
     */
    SendFile findSendFileByEmployeeIdAndSendfileId(Integer employeeId, Integer sendfileId);
}
