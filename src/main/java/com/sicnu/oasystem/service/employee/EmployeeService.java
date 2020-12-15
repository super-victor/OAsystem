package com.sicnu.oasystem.service.employee;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName EmployeeService
 * @Description 职工个人事务
 * @Author JohnTang
 * @LastChangeDate 2020/11/8 21:34
 * @Version v1.0
 */

public interface EmployeeService {

    /**
     * @MethodName getSelfProfile
     * @param
     * @Description 获取用户个人资料
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    BackFrontMessage getSelfProfile();

    /**
     * @MethodName changeUserPorfile
     * @Param String phone, String email, String homeAddress
     * @Description 修改用户个人资料
     * @Author JohnTang
     * @Return BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    BackFrontMessage changeSelfPorfile(String phone, String email, String homeAddress);

    /**
     * @MethodName getAllEmployees
     * @param
     * @Description 获取所有职工信息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getAllEmployees();
}
