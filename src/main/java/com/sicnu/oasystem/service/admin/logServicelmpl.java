package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.LogMapper;
import com.sicnu.oasystem.pojo.Log;
import com.sicnu.oasystem.service.admin.logService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName logServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:57
 * @Version v1.0
 */
@Service
public class logServicelmpl implements logService {

    @Resource
    LogMapper logMapper;

    @Override
    public BackFrontMessage getAllLogs() {
        List<Log>logs=logMapper.getAllLogs();
        if(logs==null){
            return new BackFrontMessage(500,"获取日志失败",null);
        }else if(logs.size()==0){
            return new BackFrontMessage(500,"当前没有任何日志",null);
        }else {
            return new BackFrontMessage(200,"获取所有日志成功",logs);
        }
    }

    @Override
    public BackFrontMessage getAllSysLogs() {
        List<Log>logs=logMapper.getAllSysLogs();
        if(logs==null){
            return new BackFrontMessage(500,"获取系统日志失败",null);
        }else if(logs.size()==0){
            return new BackFrontMessage(500,"当前没有任何系统日志",null);
        }else {
            return new BackFrontMessage(200,"获取所有系统日志成功",logs);
        }
    }

    @Override
    public BackFrontMessage getLogTypeByLevel(String level) {
        List<String>logType=logMapper.getLogTypeByLevel(level);
        if(logType==null){
            return new BackFrontMessage(500,"获取日志类型失败",null);
        }else if(logType.size()==0){
            return new BackFrontMessage(500,"日志类型为空",null);
        }else {
            return new BackFrontMessage(200,"获取日志类型成功",logType);
        }
    }

    @Override
    public BackFrontMessage getAllLogLevels() {
        List<String>logLevels=logMapper.getAllLevels();
        if(logLevels==null){
            return new BackFrontMessage(500,"获取日志级别失败",null);
        }else if(logLevels.size()==0){
            return new BackFrontMessage(500,"获取日志级别为空",null);
        }else {
            return new BackFrontMessage(200,"获取日志级别成功",logLevels);
        }
    }

    @Override
    public BackFrontMessage getAllOperateLogs(String operation) {
        List<Log>logs=logMapper.getAllOperateLogs(operation);
        if(logs==null){
            return new BackFrontMessage(500,"获取操作日志失败",null);
        }else if(logs.size()==0){
            return new BackFrontMessage(500,"当前没有任何操作日志",null);
        }else {
            return new BackFrontMessage(200,"获取所有操作日志成功",logs);
        }
    }
}

