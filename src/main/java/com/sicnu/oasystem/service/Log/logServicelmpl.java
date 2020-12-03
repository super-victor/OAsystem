package com.sicnu.oasystem.service.Log;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.LogMapper;
import com.sicnu.oasystem.pojo.Log;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName logServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:57
 * @Version v1.0
 */

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
}

