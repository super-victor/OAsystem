package com.sicnu.oasystem.aop;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName RoleAop
 * @Description 角色权限控制注解，用于管理员和超级管理员
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 15:24
 * @Version v1.0
 */

@Aspect
@Component
public class RoleAop {

    @Around("@annotation(role)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, Role role){

        Integer roleId = role.id();
        Iterator<com.sicnu.oasystem.pojo.Role> iterator = (Iterator<com.sicnu.oasystem.pojo.Role>) UserAuthenticationUtils.getCurrentUserFromSecurityContext().getAuthorities().iterator();

        boolean flag = false;

        while (iterator.hasNext()) {
            if (iterator.next().getRoleId() == roleId) {
                flag = true;
            }
        }


        if (flag) {
            Object object = null;
            try {
                object = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return object;
        } else {
            return new BackFrontMessage(301, "权限不足", null);
        }

    }
}
