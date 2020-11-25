package com.sicnu.oasystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListUtil
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/24 11:09
 * @Version v1.0
 */

public class ListUtil {
    //保证实现arraylist
    public static<T> void compare(List<T> primary , List<T> current){
//        Iterator<T> iP = primary.iterator();
//        Iterator<T> iC;
//        while (iP.hasNext()) {
//            T t = iP.next();
//            iC = current.iterator();
//            while (iC.hasNext()) {
//                if (t.equals(iC.next())) {
//                    iP.remove();
//                    iC.remove();
//                    break;
//                }
//            }
//        }
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
