package com.sicnu.oasystem.controller.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.admin.SuperAdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @ClassName SuperAdminController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/27 16:21
 * @Version v1.0
 */

@RestController
public class SuperAdminController {
    @Resource
    SuperAdminService superAdminService;

    @ApiOperation(value = "获取所有管理员信息")
    @GetMapping("/alladmin")
    BackFrontMessage getAllAdmin(){
        return superAdminService.getAllAdmin();
    }

    @ApiOperation(value = "添加管理员")
    @PostMapping("/admin")
    BackFrontMessage addAdmin(@RequestParam List<Integer> employeeIdList){
        return superAdminService.updateAdminList(employeeIdList);
    }

    @ApiOperation(value = "锁定管理员")
    @PutMapping("/lockOrUnlockAdmin")
    BackFrontMessage lockadmin(@RequestParam Integer employeeId, @RequestParam Integer isAccountLocked){
        return superAdminService.lockAdmin(employeeId, isAccountLocked);
    }



}
