package com.sicnu.oasystem.service.message;

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
     * @param type 使用DataUtil的常量
     * @param title 使用DataUtil的常量
     * @param content
     * @Description 系统发送消息
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int send(int employeeId, int type, String title, String content);
}
