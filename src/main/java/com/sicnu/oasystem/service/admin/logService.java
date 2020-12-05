package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.stereotype.Service;

/**
 * @InterfaceName logServeice
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:57
 * @Version v1.0
 */

public interface logService {
    /**
     * @MethodName getAllLogs
     * @param
     * @Description 获取所有的日志信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/4
     */
    BackFrontMessage getAllLogs();

    /**
     * @MethodName getAllOperateLogs
     * @param operation
     * @Description 获取所有的操作日志
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/4
     */
    BackFrontMessage getAllOperateLogs(String operation);

    /**
     * @MethodName getAllSysLogs
     * @param
     * @Description 获取所有的系统日志
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/4
     */
    BackFrontMessage getAllSysLogs();

    /**
     * @MethodName getAllLogLevels
     * @param
     * @Description 获取所有的日志级别
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/4
     */
    BackFrontMessage getAllLogLevels();

    /**
     * @MethodName getLogTypeByLevel
     * @param level
     * @Description 按日志级别获取日志类型
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/4
     */
    BackFrontMessage getLogTypeByLevel(String level);
}
