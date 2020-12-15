package com.sicnu.oasystem.service.message;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.util.DataUtil;

/**
 * @InterfaceName MessageService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 21:31
 * @Version v1.0
 */

public interface MessageService {

    /**
     * @MethodName send
     * @param employeeId
     * @param type 使用DataUtil的常量 消息的类型
     * @param title 使用DataUtil的常量 消息的标题
     * @param content
     * @Description 系统发送消息
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int send(int employeeId, int type, String title, String content);

    /**
     * @MethodName getSelfAllMessage
     * @param
     * @Description 获取自己所有的消息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getSelfAllMessage();

    /**
     * @MethodName getSelfAllUnReadMessage
     * @param
     * @Description 获取自己所有的未读消息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getSelfAllUnReadMessage();

    /**
     * @MethodName setSelfMessageRead
     * @param messageId
     * @Description 将自己的消息设置成已读
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage setSelfMessageRead(int messageId);
}
