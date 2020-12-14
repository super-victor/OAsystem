package com.sicnu.oasystem.util;

import java.util.Date;

/**
 * @ClassName DataUtil
 * @Description 全局常量工具类
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 21:35
 * @Version v1.0
 */

public class DataUtil {
    // 失败
    public static final int MESSAGE_TYPE_FAIL = 0;
    // 成功
    public static final int MESSAGE_TYPE_SUCCESS = 1;
    // 提示
    public static final int MESSAGE_TYPE_INFO = 2;
    // 警告
    public static final int MESSAGE_TYPE_WARN = 3;

    // 发文审查title
    public static final String MESSAGE_TITLE_POSTREVIEW = "发文审查";

    // 收到公司日程title
    public static final String MESSAGE_TITLE_SCHEDULE = "公司日程";

    // 系统总的访问量
    public static int Data_Total_Views = 0;

    // 系统当天的访问量
    public static int Data_Daily_Views = 0;
    public static Date Data_Date = new Date();

}
