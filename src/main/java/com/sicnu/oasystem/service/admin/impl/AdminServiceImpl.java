package com.sicnu.oasystem.service.admin.impl;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.service.admin.AdminService;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AdminServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 11:09
 * @Version v1.0
 */

@Slf4j
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
        return new BackFrontMessage(200,"获取成功", employeeMapper.findAllRolesWithEmpolyeeList());
    }

    @Override
    public BackFrontMessage addMenuInRole(Integer menuId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertMenuAndRoleIntoRoleMenu(menuId,roleId);
        }catch (DuplicateKeyException e) {
            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }
        if (flag == 1) {
            return new BackFrontMessage(200,"添加成功",null);
        } else {
            return new BackFrontMessage(500,"添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteMenuInRole(Integer menuId, Integer roleId) {
        if (employeeMapper.deletefromRoleMenu(menuId, roleId) > 0) {
            return new BackFrontMessage(200, "删除成功", null);
        } else {
            return new BackFrontMessage(500, "删除失败", null);
        }
    }

    @Override
    public BackFrontMessage addRoleInEmployee(Integer employeeId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertRoleIntoEmployeeRole(1,1);
        }catch (DuplicateKeyException e) {
            log.warn("用户id为"+employeeId+"插入了一个重复键 roleId为"+roleId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+employeeId+"插入了一个违反约束完整性的键 roleId为"+roleId);
            flag = 0;
        }
        if (flag == 1) {
            return new BackFrontMessage(200,"添加成功",null);
        } else {
            return new BackFrontMessage(500,"添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteRoleInEmployee(Integer employeeId, Integer roleId) {
        if (employeeMapper.deletefromEmployeeRole(employeeId, roleId) > 0) {
            return new BackFrontMessage(200, "删除成功", null);
        } else {
            return new BackFrontMessage(500, "删除失败", null);
        }
    }
}
