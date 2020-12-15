package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName MessageMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 21:42
 * @Version v1.0
 */
@Mapper
public interface MessageMapper {

    /**
     * @MethodName insertMessage
     * @param message
     * @Description 添加消息
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int insertMessage(Message message);

    /**
     * @MethodName findMessageByEmployeeId
     * @param employeeId
     * @Description 找出该员工的所有消息
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Message>
     * @LastChangeDate 2020/12/14
     */
    List<Message> findMessageByEmployeeId(int employeeId);

    /**
     * @MethodName findMessageByEmployeeIdAndIsRead
     * @param employeeId
     * @param isRead
     * @Description 找出该员工未读或者已读消息
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Message>
     * @LastChangeDate 2020/12/14
     */
    List<Message> findMessageByEmployeeIdAndIsRead(int employeeId,int isRead);

    /**
     * @MethodName updateIsReadInMessage
     * @param messageId
     * @param employeeId
     * @param isRead
     * @Description 更新消息的已读状态
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/14
     */
    int updateIsReadInMessage(int messageId, int employeeId,int isRead);
}
