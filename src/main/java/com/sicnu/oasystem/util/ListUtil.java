package com.sicnu.oasystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListUtil
 * @Description 列表工具类
 * @Author JohnTang
 * @LastChangeDate 2020/11/24 11:09
 * @Version v1.0
 */

public class ListUtil {
    /**
     * @MethodName compare
     * @param primary 原数组 (方法执行完后装的就是相对于原数组减少的部分)
     * @param current 增加删除后的数组 (方法执行完后装的就是相对于原数组增加的部分)
     * @Description 必须保证list是arraylist的实现，用于得出primary和current相比增加删除的部分
     * @Author JohnTang
     * @Return void
     * @LastChangeDate 2020/11/28
     */
    public static<T> void compare(List<T> primary , List<T> current){
        List<T> intersection = new ArrayList<>(primary.size());
        intersection.addAll(primary);
        // 取到了交集
        intersection.retainAll(current);
        // 去除交集
        primary.removeAll(intersection);
        current.removeAll(intersection);
        // 原始数组装的就是减少的部分
        // 现在数组装的就是增加的部分
    }
}
