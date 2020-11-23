package com.sicnu.oasystem.controller.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.AddrListService;
import com.sicnu.oasystem.service.admin.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AdminController
 * @Description 管理员控制器
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:32
 * @Version v1.0
 */

@RestController
public class AdminController {
    @Resource
    AddrListService addrListService;

    @Resource
    AdminService adminService;

    @GetMapping("/allEmployeeDetails")
    BackFrontMessage getAllEmployeeDetails(Integer currentPageNum,Integer pageSize) {
        return addrListService.getEmployeeBypage(currentPageNum,pageSize);
    }

    @GetMapping("/allRolesWithEmployeeList")
    BackFrontMessage getAllRoleWithEmployeeList(){
        return adminService.getAllRoles();
    }




}
