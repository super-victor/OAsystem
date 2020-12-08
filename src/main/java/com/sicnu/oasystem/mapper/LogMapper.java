package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName LogMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:53
 * @Version v1.0
 */
@Mapper
public interface LogMapper {

    /**
     * @MethodName getAllLogs
     * @param
     * @Description 所有的日志信息
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Log>
     * @LastChangeDate 2020/12/4
     */
    List<Log>getAllLogs();

    /**
     * @MethodName getAllOperateLogs
     * @param operation 操作类型 如增，删，改
     * @Description 获取所有的操作日志
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Log>
     * @LastChangeDate 2020/12/4
     */
    List<Log>getAllOperateLogs(String operation);

    /**
     * @MethodName getAllSysLogs
     * @param
     * @Description 获取所有的系统日志
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Log>
     * @LastChangeDate 2020/12/4
     */
    List<Log>getAllSysLogs();

    /**
     * @MethodName getAllLevels
     * @param
     * @Description 获取所有的日志级别
     * @Author pan
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/12/4
     */
    List<String>getAllLevels();

    /**
     * @MethodName getLogTypeByLevel
     * @param level 日志级别
     * @Description 按日志级别获取日志类型
     * @Author pan
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/12/4
     */
    List<String>getLogTypeByLevel(String level);
}
