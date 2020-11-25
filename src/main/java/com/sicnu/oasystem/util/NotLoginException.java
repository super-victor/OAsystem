package com.sicnu.oasystem.util;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName NotLoginException
 * @Description 未登录异常
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 16:34
 * @Version v1.0
 */

public class NotLoginException extends AuthenticationException {
    public NotLoginException(String msg) {
        super(msg);
    }
}
