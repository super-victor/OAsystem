package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Role;
import com.sicnu.oasystem.util.ListUtil;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public BackFrontMessage getAllEmployee() {
        return new BackFrontMessage(200,"获取成功",employeeMapper.findAllEmployeeExceptA());
    }

    @Override
    public BackFrontMessage addEmployee(Employee employee) {
        int flag;
        try {
            flag = employeeMapper.insertEmployee(employee);
        }catch (DuplicateKeyException e) {
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            flag = 0;
        }catch (Exception e){
            flag = 0;
        }

        if (flag != 0) {
            return new BackFrontMessage(200, "添加成功", null);
        } else {
            return new BackFrontMessage(500, "添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteEmployee(int employeeId) {
        //确保不是拥有管理员和超级管理员用户
        // unfinish
        //先将用户有关的所有表都清空完
        if (employeeMapper.deleteEmployee(employeeId) != 0) {
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage addRole(String name) {
        int flag;
        try {
            flag = employeeMapper.insertRole(name);
        }catch (DuplicateKeyException e) {
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            flag = 0;
        }catch (Exception e){
            flag = 0;
        }

        if (flag != 0) {
            return new BackFrontMessage(200, "添加成功", null);
        } else {
            return new BackFrontMessage(500, "添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteRole(int roleId) {
        int flag;
        try {
            flag = employeeMapper.deleteRole(roleId);
        }catch (Exception e){
            flag = 0;
        }

        if (flag != 0) {
            return new BackFrontMessage(200, "删除成功", null);
        } else {
            return new BackFrontMessage(500, "删除失败", null);
        }
    }

    @Override
    public BackFrontMessage getAllMenus() {
        return new BackFrontMessage(200, "获取成功", employeeMapper.findAllMenus());
    }

    @Override
    public BackFrontMessage getMenuCodeList(Integer roleId) {
        return new BackFrontMessage(200,"获取成功",employeeMapper.findCodesByRoleId(roleId));
    }

    @Override
    public BackFrontMessage updateRoleMenulist(Integer roleId, List<String> codeList) {
        // 判断roleId是否为超级管理员和管理员
        if (roleId == 1 || roleId == 2) {
            return new BackFrontMessage(500,"修改失败",null);
        }

        List<String> primaryCodeList = employeeMapper.findCodesByRoleId(roleId);

        ListUtil.compare(primaryCodeList, codeList);

        for (String code : primaryCodeList) {
            employeeMapper.deletefromRoleMenuByCode(roleId, code);
        }

        for (String code : codeList) {
            employeeMapper.insertIntoRoleMenuByCode(roleId, code);
        }

        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public BackFrontMessage getAllRolesWithEmployeeList() {
        return new BackFrontMessage(200,"获取成功", employeeMapper.findAllRolesWithEmpolyeeList());
    }

    @Override
    public BackFrontMessage updateRoleEmployeelist(Integer roleId, List<Integer> employeeIdList) {
        if (roleId == 1 || roleId == 2) {
            return new BackFrontMessage(500,"修改失败",null);
        }
        List<Employee> primaryEmployeeList = employeeMapper.findEmployeesByRoleId(roleId);
        List<Integer> primaryEmployeeIdList = new ArrayList<>(primaryEmployeeList.size());

        for (Employee employee: primaryEmployeeList) {
            primaryEmployeeIdList.add(employee.getEmployeeId());
        }

        ListUtil.compare(primaryEmployeeIdList,employeeIdList);

        for (Integer employeeId : primaryEmployeeIdList) {
            employeeMapper.deletefromEmployeeRole(employeeId,roleId);
        }

        for (Integer employeeId : employeeIdList) {
            employeeMapper.insertIntoEmployeeRole(employeeId,roleId);
        }

        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public int addMenuInRole(Integer menuId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertIntoRoleMenu(menuId,roleId);
        }catch (DuplicateKeyException e) {
            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }
        return flag;
    }

    @Override
    public int deleteMenuInRole(Integer menuId, Integer roleId) {
        return employeeMapper.deletefromRoleMenu(menuId, roleId);
    }

    @Override
    public int addRoleInEmployee(Integer employeeId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertIntoEmployeeRole(1,1);
        }catch (DuplicateKeyException e) {
            log.warn("用户id为"+employeeId+"插入了一个重复键 roleId为"+roleId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+employeeId+"插入了一个违反约束完整性的键 roleId为"+roleId);
            flag = 0;
        }
        return flag;
    }

    @Override
    public int deleteRoleInEmployee(Integer employeeId, Integer roleId) {
        return employeeMapper.deletefromEmployeeRole(employeeId, roleId);
    }

    @Override
    public BackFrontMessage lockOrUnlockEmployee(Integer employeeId, Integer isAccountLocked) {
        //判断是否为超级管理员，管理员
        if (employeeMapper.updateIsAccountLockedInEmployee(employeeId, isAccountLocked) == 1) {
            return new BackFrontMessage(200, "修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    @Override
    public BackFrontMessage getEmployeeRole(Integer employeeId) {
        Map<String, List<Role>> map = new HashMap<>(2);
        map.put("haveRoleList", employeeMapper.findRolesByEmployeeId(employeeId));
        map.put("nothaveRoleList", employeeMapper.findAllRoles());
        return new BackFrontMessage(200, "获取成功",map);
    }

    @Override
    public BackFrontMessage updateEmployeeRolelist(Integer employeeId, List<Integer> roleIdList) {
        // 判断是否为超级管理员，管理员 判断角色列表中有没有1,2
        List<Role> primaryRoleList = employeeMapper.findRolesByEmployeeId(employeeId);
        List<Integer> primaryRoleIdList = new ArrayList<>(primaryRoleList.size());

        for (Role role : primaryRoleList) {
            primaryRoleIdList.add(role.getRoleId());
        }

        ListUtil.compare(primaryRoleIdList,roleIdList);

        for (Integer roleId : primaryRoleIdList) {
            employeeMapper.deletefromEmployeeRole(employeeId,roleId);
        }
        for (Integer roleId :roleIdList) {
            employeeMapper.insertIntoEmployeeRole(employeeId,roleId);
        }

        return new BackFrontMessage(200,"修改成功",null);
    }



}
