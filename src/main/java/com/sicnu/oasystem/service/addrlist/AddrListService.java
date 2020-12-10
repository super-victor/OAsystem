package com.sicnu.oasystem.service.addrlist;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @ClassName AddrListServer
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/8 17:24
 * @Version v1.0
 */

public interface AddrListService {

    /**
     * @MethodName getAllEmplyeAddr
     * @param
     * @Description 获取所有员工的通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getAllEmplyeAddr();

    /**
     * @MethodName getDeparmentName
     * @param
     * @Description 获取所有部门的名称
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getDeparmentName();

    /**
     * @MethodName getEmployeeBypage
     * @param currentPageNum 当前页数
     * @param pageSize 每页的大小
     * @Description 按页获取员工信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getEmployeeBypage(Integer currentPageNum,Integer pageSize);

    /**
     * @MethodName getEmployeeByCondition
     * @param name 员工姓名
     * @param sex  员工性别
     * @Description 按条件获取员工的信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/10
     */
    BackFrontMessage getEmployeeByCondition(String name,String sex);
}

