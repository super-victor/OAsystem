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

    int insertMessage(Message message);

    List<Message> findMessageByEmployeeId(int employeeId);

    List<Message> findMessageByEmployeeIdAndIsRead(int employeeId,int isRead);

    int updateIsReadInMessage(int messageId, int employeeId,int isRead);
}
