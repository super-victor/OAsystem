package com.sicnu.oasystem.util;

import com.sicnu.oasystem.json.BackFrontMessage;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/2 18:51
 * @Version v1.0
 */
@RestControllerAdvice
public class GlobalExceptionHand {

    Logger logger= Logger.getLogger("error");

    //捕获全局未catch的异常并使用log4j插入到数据库
    @ExceptionHandler
    public BackFrontMessage geterror(Exception e){
        MDC.put("type","SystemException");
        e.printStackTrace();
        logger.error("出现异常："+e.getMessage());
        return new BackFrontMessage(500,"服务器异常",e.getMessage());
    }
}

