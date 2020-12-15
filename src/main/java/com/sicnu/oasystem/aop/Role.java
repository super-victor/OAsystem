package com.sicnu.oasystem.aop;

import java.lang.annotation.*;

/**
 * @ClassName Role
 * @Description 角色权限控制注解，用于管理员和超级管理员
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 18:27
 * @Version v1.0
 **/

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    int id();
}
