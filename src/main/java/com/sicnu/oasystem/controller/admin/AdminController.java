package com.sicnu.oasystem.controller.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AdminController
 * @Description 管理员控制器
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:32
 * @Version v1.0
 */

@Api(tags = "后台管理")
@RestController
public class AdminController {

    @Resource
    AdminService adminService;

    /**
     * @MethodName getAllEmployeeDetails
     * @param
     * @Description 获取所有职工信息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "获取所有职工信息")
    @GetMapping("/allEmployee")
    BackFrontMessage getAllEmployeeDetails() {
        return adminService.getAllEmployee();
    }

    /**
     * @MethodName getAllRoleWithEmployeeList
     * @param
     * @Description 获取所有角色，以及角色下的职工列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "获取所有角色和角色对应职工列表")
    @GetMapping("/allRolesWithEmployeeList")
    BackFrontMessage getAllRoleWithEmployeeList(){
        return adminService.getAllRolesWithEmployeeList();
    }

    /**
     * @MethodName updateRoleEmployeelist
     * @param roleId
     * @param employeeIdList
     * @Description 更新角色下的所有用户
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "更新指定角色下所有用户")
    @PutMapping("/roleEmployeelist")
    BackFrontMessage updateRoleEmployeelist(@RequestParam Integer roleId, @RequestParam List<Integer> employeeIdList){
        return adminService.updateRoleEmployeelist(roleId, employeeIdList);
    }

    /**
     * @MethodName getMenuListByRoleId
     * @param roleId
     * @Description 获取该角色下所有功能codeList
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "获取指定角色下所有功能的codeList")
    @GetMapping("/menuCodeList")
    BackFrontMessage getMenuCodeList(@RequestParam Integer roleId){
        return adminService.getMenuCodeList(roleId);
    }

    /**
     * @MethodName updateRoleMenulist
     * @param roleId
     * @param codeList
     * @Description 更新该角色下拥有的所有功能
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "通过codelist更新指定角色下所有功能")
    @PutMapping("roleMenulist")
    BackFrontMessage updateRoleMenulist(@RequestParam Integer roleId, @RequestParam List<String> codeList){
        return adminService.updateRoleMenulist(roleId,codeList);
    }

    /**
     * @MethodName lockOrUnlockEmployee
     * @param employeeId
     * @param isAccountLocked
     * @Description 锁定或者解锁职工账户
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "锁定或者解锁职工账户")
    @PutMapping("/lockOrUnlockEmployee")
    BackFrontMessage lockOrUnlockEmployee(@RequestParam Integer employeeId, @RequestParam Integer isAccountLocked){
        return adminService.lockOrUnlockEmployee(employeeId, isAccountLocked);
    }

    /**
     * @MethodName getEmployeeRolelist
     * @param employeeId
     * @Description 获取指定职工的角色列表，返回拥有的角色和所有角色集合
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "获取指定职工的角色列表")
    @GetMapping("/employeeRolelist")
    BackFrontMessage getEmployeeRolelist(@RequestParam Integer employeeId){
        return adminService.getEmployeeRole(employeeId);
    }

    /**
     * @MethodName updateEmployeeRolelist
     * @param employeeId
     * @param roleIdList
     * @Description 更新用户拥有的角色列表，多了增加，已存在不变，少了删除
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/24
     */
    @ApiOperation(value = "更新用户拥有的角色列表")
    @PutMapping("/employeeRolelist")
    BackFrontMessage updateEmployeeRolelist(@RequestParam Integer employeeId, @RequestParam List<Integer> roleIdList){
        return adminService.updateEmployeeRolelist(employeeId, roleIdList);
    }

    @ApiOperation(value = "添加职工")
    @PostMapping("/employee")
    BackFrontMessage addEmployee( @RequestBody @ModelAttribute Employee employee){
        return adminService.addEmployee(employee);
    }

    @ApiOperation(value = "删除职工")
    @DeleteMapping("/employee")
    BackFrontMessage deleteEmployee(@RequestParam int employeeId){
        return adminService.deleteEmployee(employeeId);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    BackFrontMessage addRole(@RequestParam String roleName){
        return adminService.addRole(roleName);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role")
    BackFrontMessage deleteRole(@RequestParam Integer roleId){
        return adminService.deleteRole(roleId);
    }

}
