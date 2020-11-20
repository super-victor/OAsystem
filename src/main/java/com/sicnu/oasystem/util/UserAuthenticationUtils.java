package com.sicnu.oasystem.util;

import com.sicnu.oasystem.pojo.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName UserAuthenticationUtils
 * @Description 用户认证工具类
 * @Author JohnTang
 * @LastChangeDate 2020/2/7 0:52
 * @Version v1.0
 **/

public class UserAuthenticationUtils {
    @Value("${document.path}")
    private String path;
    
    /**
     * @MethodName getCurrentUserFromSecurityContext()
     * @Param 
     * @Description 从spring上下文中获取当前用户信息
     * @Return Employee 用于认证的实体类
     * @LastChangeDate 2020/11/5
     * @Version v1.0
     */
    public static Employee getCurrentUserFromSecurityContext() {
        return (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
