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

    public void insertInfo(String info){
        MDC.put("type","insert");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        logger.info(info);
    }

    public void updateInfo(String info){
        MDC.put("type","update");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        logger.info(info);
    }

    public void deleteInfo(String info){
        MDC.put("type","delete");
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        logger.info(info);
    }

    public void login(String info){
        info="用户ID为"+UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()+info;
        MDC.put("type","login");
        logger.info(info);
    }

    public void customException(String info){
        MDC.put("type","customException");
        logger.info(info);
    }
}

