package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import java.util.List;

/**
 * @InterfaceName AdminService
 * @Description 管理员服务
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:40
 * @Version v1.0
 */

public interface AdminService {

    /**
     * @MethodName getAllEmployee
     * @param
     * @Description 获取所有的员工
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage getAllEmployee();

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
     * @MethodName getMenuCodeList
     * @param roleId
     * @Description 获取所有功能的code列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage getMenuCodeList(Integer roleId);

    /**
     * @MethodName updateRoleMenulist
     * @param roleId
     * @param codeList
     * @Description 更新角色对应的功能列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage updateRoleMenulist(Integer roleId, List<String> codeList);

    /**
     * @MethodName getAllRoles
     * @param
     * @Description 获取所有的角色
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    BackFrontMessage getAllRolesWithEmployeeList();

    /**
     * @MethodName updateRoleEmployeelist
     * @param roleId
     * @param employeeIdList
     * @Description 添加描述
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage updateRoleEmployeelist(Integer roleId, List<Integer> employeeIdList);

    /**
     * @MethodName addMenuInRole
     * @param menuId
     * @param roleId
     * @Description 将角色和功能关联起来
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    int addMenuInRole(Integer menuId, Integer roleId);

    /**
     * @MethodName deleteMenuInRole
     * @param menuId
     * @param roleId
     * @Description 取消角色和功能的关联
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    int deleteMenuInRole(Integer menuId, Integer roleId);

    /**
     * @MethodName addRoleInEmployee
     * @param employeeId
     * @param roleId
     * @Description 添加职工和角色的对应
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    int addRoleInEmployee(Integer employeeId, Integer roleId);

    /**
     * @MethodName deleteRoleInEmployee
     * @param employeeId
     * @param roleId
     * @Description 取消职工和角色的对应
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/23
     */
    int deleteRoleInEmployee(Integer employeeId, Integer roleId);

    /**
     * @MethodName lockOrUnlockEmployee
     * @param employeeId
     * @param isAccountLocked
     * @Description 修改账户的锁定状态
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage lockOrUnlockEmployee(Integer employeeId, Integer isAccountLocked);

    /**
     * @MethodName getEmployeeRole
     * @param employeeId
     * @Description 获取对应职工的角色列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage getEmployeeRole(Integer employeeId);

    /**
     * @MethodName updateEmployeeRolelist
     * @param employeeId
     * @param roleIdList
     * @Description 更新用户的角色列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/25
     */
    BackFrontMessage updateEmployeeRolelist(Integer employeeId, List<Integer> roleIdList);
}
