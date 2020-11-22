package com.sicnu.oasystem.service.admin.impl;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.service.admin.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AdminServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 11:09
 * @Version v1.0
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public BackFrontMessage getAllMenus() {
        return new BackFrontMessage(200, "获取成功", employeeMapper.findAllMenus());
    }

    @Override
    public BackFrontMessage getAllRoles() {
        return new BackFrontMessage(200,"获取成功", employeeMapper.findAllRoles());
    }

    @Override
    public BackFrontMessage getAllEmployees() {
        return null;
    }

    @Override
    public BackFrontMessage addMenuInRole(Integer menuId, Integer roleId) {
        return null;
    }

    @Override
    public BackFrontMessage deleteMenuInRole(Integer menuId, Integer roleId) {
        return null;
    }

    @Override
    public BackFrontMessage addRoleInEmployee(Integer employeeId, Integer roleId) {
        return null;
    }

    @Override
    public BackFrontMessage deleteRoleInEmployee(Integer employeeId, Integer roleId) {
        return null;
    }
}
