package com.sicnu.oasystem.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ValidUtil
 * @Description 验证数据格式
 * @Author Waynejwei
 * @LastChangeDate 2020/11/25 16:35
 * @Version v1.0
 */

public class ValidUtil {


    /**
     * @MethodName isValidMobileNo
     * @param mobileNumber 手机号码
     * @Description 验证手机号，长度为11位
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/25
     */
    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.length() == 11;
    }

    /**
     * @MethodName isValidEmail
     * @param email 邮箱
     * @Description 验证邮箱验证码（形如：xxxx@xx.xx）
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/25
     */
    public static boolean isValidEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }

    public static <T> List<String> validate(T t) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations == null) {
            return null;
        }
        List<String> messageList = new ArrayList(constraintViolations.size());

        constraintViolations.forEach(constraintViolation -> messageList.add(constraintViolation.getMessage()));
        return messageList;
    }
}
