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

    BackFrontMessage getAllMenus();

    BackFrontMessage getAllRoles();

    BackFrontMessage getAllEmployees();

    BackFrontMessage addMenuInRole(Integer menuId, Integer roleId);

    BackFrontMessage deleteMenuInRole(Integer menuId, Integer roleId);

    BackFrontMessage addRoleInEmployee(Integer employeeId, Integer roleId);

    BackFrontMessage deleteRoleInEmployee(Integer employeeId, Integer roleId);
}
