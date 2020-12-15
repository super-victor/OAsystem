package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;

import java.util.List;

/**
 * @InterfaceName SuperAdminService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/26 16:43
 * @Version v1.0
 */

public interface SuperAdminService {

    /**
     * @MethodName getAllAdmin
     * @param
     * @Description 获取所有管理员
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage getAllAdmin();

    /**
     * @MethodName updateAdminList
     * @param employeeIdList
     * @Description 更新管理员列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage updateAdminList(List<Integer> employeeIdList);

    /**
     * @MethodName lockAdmin
     * @param employeeId
     * @param isAccountLocked
     * @Description 锁定管理员
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    BackFrontMessage lockAdmin(int employeeId, Integer isAccountLocked);
}
