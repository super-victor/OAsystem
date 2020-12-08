package com.sicnu.oasystem.util;

/**
 * @ClassName OverMaxFileSizeLimitException
 * @Description 超出最大文件限制异常
 * @Author JohnTang
 * @LastChangeDate 2020/12/2 18:40
 * @Version v1.0
 */

public class OverMaxFileSizeLimitException extends Exception{

    public OverMaxFileSizeLimitException(String message) {
        super(message);
    }
}
