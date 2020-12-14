package com.sicnu.oasystem.controller.admin;

import com.sicnu.oasystem.aop.Role;
import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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
    @Role(id = 2)
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
    @Role(id = 2)
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
    @Role(id = 2)
    @PutMapping("/roleEmployeelist")
    BackFrontMessage updateRoleEmployeelist(@RequestParam @NotNull Integer roleId, @RequestParam List<Integer> employeeIdList){
        if (employeeIdList == null) {
            employeeIdList = new ArrayList<>();
        }
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
    @Role(id = 2)
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
    @Role(id = 2)
    @PutMapping("roleMenulist")
    BackFrontMessage updateRoleMenulist(@RequestParam @NotNull Integer roleId, @RequestParam List<String> codeList){
        if (codeList == null) {
            codeList = new ArrayList<>();
        }
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
    @Role(id = 2)
    @PutMapping("/lockOrUnlockEmployee")
    BackFrontMessage lockOrUnlockEmployee(@RequestParam @NotNull Integer employeeId, @RequestParam @NotNull @Pattern(regexp = "0|1") Integer isAccountLocked){
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
    @Role(id = 2)
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
    @Role(id = 2)
    @PutMapping("/employeeRolelist")
    BackFrontMessage updateEmployeeRolelist(@RequestParam @NotNull Integer employeeId, @RequestParam List<Integer> roleIdList){
        if (roleIdList == null) {
            roleIdList = new ArrayList<>();
        }
        return adminService.updateEmployeeRolelist(employeeId, roleIdList);
    }

    /**
     * @MethodName addEmployee
     * @param employee
     * @Description 添加职工
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "添加职工")
    @Role(id = 2)
    @PostMapping("/employee")
    BackFrontMessage addEmployee(@Validated Employee employee){
        return adminService.addEmployee(employee);
    }

    /**
     * @MethodName batchEmployeeByExcel
     * @param multipartFile
     * @Description 批量导入职工
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "通过excel批量导入职工")
    @Role(id = 2)
    @PostMapping("/batchEmployeeByExcel")
    BackFrontMessage batchEmployeeByExcel(@RequestParam MultipartFile multipartFile) {
        return adminService.batchEmployeeByExcel(multipartFile);
    }

    /**
     * @MethodName deleteEmployee
     * @param employeeId
     * @Description 删除职工
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "删除职工")
    @Role(id = 2)
    @DeleteMapping("/employee")
    BackFrontMessage deleteEmployee(@RequestParam int employeeId){
        return adminService.deleteEmployee(employeeId);
    }

    /**
     * @MethodName addRole
     * @param roleName
     * @Description 添加角色
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "添加角色")
    @Role(id = 2)
    @PostMapping("/role")
    BackFrontMessage addRole(@RequestParam @Size(min = 1,max = 10) @NotNull String roleName){
        return adminService.addRole(roleName);
    }

    /**
     * @MethodName deleteRole
     * @param roleId
     * @Description 删除角色
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "删除角色")
    @Role(id = 2)
    @DeleteMapping("/role")
    BackFrontMessage deleteRole(@RequestParam Integer roleId){
        return adminService.deleteRole(roleId);
    }

    /**
     * @MethodName copyrole
     * @param copyroleId
     * @param newName
     * @Description 复制角色
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "复制角色")
    @Role(id = 2)
    @PostMapping("/copyrole")
    BackFrontMessage copyrole(@RequestParam Integer copyroleId, @RequestParam  @Size(min = 1,max = 10) @NotNull String newName){
        return adminService.copyRole(copyroleId,newName);
    }

}
