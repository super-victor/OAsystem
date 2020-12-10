package com.sicnu.oasystem.util;

import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @ClassName LogUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/2 20:13
 * @Version v1.0
 */
@Component
public class LogUtil {

    Logger logger=Logger.getLogger("info");

    //对数据库进行插入操作（操作日志）
    public void insertInfo(String info){
        MDC.put("type","insert");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;//获取操作者的Id
        logger.info(info);
    }
    //对数据库进行修改操作（操作日志）
    public void updateInfo(String info){
        MDC.put("type","update");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        logger.info(info);
    }
    //对数据库进行删除操作（操作日志）
    public void deleteInfo(String info){
        MDC.put("type","delete");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        logger.info(info);
    }
    //记录登陆信息（操作日志）
    public void login(String info){
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        MDC.put("type","login");
        logger.info(info);
    }
    //添加自己捕获的异常
    public void customException(String info){
        MDC.put("type","customException");
        logger.info(info);
    }
}

