package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName AdminService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:40
 * @Version v1.0
 */

public interface AdminService {
    /**
     * @MethodName getAllMenus
     * @param
     * @Description 获取所有的功能列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage getAllMenus();

    /**
     * @MethodName getAllRoles
     * @param
     * @Description 获取所有的角色
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage getAllRoles();

    /**
     * @MethodName addMenuInRole
     * @param menuId
     * @param roleId
     * @Description 将角色和功能关联起来
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage addMenuInRole(Integer menuId, Integer roleId);

    /**
     * @MethodName deleteMenuInRole
     * @param menuId
     * @param roleId
     * @Description 取消角色和功能的关联
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage deleteMenuInRole(Integer menuId, Integer roleId);

    /**
     * @MethodName addRoleInEmployee
     * @param employeeId
     * @param roleId
     * @Description 添加职工和角色的对应
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage addRoleInEmployee(Integer employeeId, Integer roleId);

    /**
     * @MethodName deleteRoleInEmployee
     * @param employeeId
     * @param roleId
     * @Description 取消职工和角色的对应
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage deleteRoleInEmployee(Integer employeeId, Integer roleId);



}
