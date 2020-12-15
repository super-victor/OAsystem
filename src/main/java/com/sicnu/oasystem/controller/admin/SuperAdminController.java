package com.sicnu.oasystem.controller.admin;

import com.sicnu.oasystem.aop.Role;
import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.admin.SuperAdminService;
import com.sicnu.oasystem.service.admin.logService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName SuperAdminController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/27 16:21
 * @Version v1.0
 */

@Api(tags = "超级管理员")
@RestController
public class SuperAdminController {
    @Resource
    SuperAdminService superAdminService;

    @Resource
    logService logService;

    /**
     * @MethodName getAllAdmin
     * @param
     * @Description 获取所有管理员信息
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "获取所有管理员信息")
    @Role(id = 1)
    @GetMapping("/alladmin")
    BackFrontMessage getAllAdmin(){
        return superAdminService.getAllAdmin();
    }

    /**
     * @MethodName addAdmin
     * @param employeeIdList
     * @Description 添加管理员
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "添加管理员")
    @Role(id = 1)
    @PostMapping("/admin")
    BackFrontMessage addAdmin(@RequestParam List<Integer> employeeIdList){
        if (employeeIdList == null) {
            employeeIdList = new ArrayList<>();
        }
        return superAdminService.updateAdminList(employeeIdList);
    }

    /**
     * @MethodName lockadmin
     * @param employeeId
     * @param isAccountLocked
     * @Description 锁定管理员
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/11
     */
    @ApiOperation(value = "锁定管理员")
    @Role(id = 1)
    @PutMapping("/lockOrUnlockAdmin")
    BackFrontMessage lockadmin(@RequestParam @NotNull Integer employeeId, @RequestParam @NotNull @Pattern(regexp = "0|1") Integer isAccountLocked){
        return superAdminService.lockAdmin(employeeId, isAccountLocked);
    }


    @ApiOperation("获取所有的日志信息")
    @Role(id = 1)
    @GetMapping("/getAllLogs")
    BackFrontMessage getAllLogs(){
        return logService.getAllLogs();
    }

    @ApiOperation("获取所有的操作日志")
    @Role(id = 1)
    @GetMapping("/getAllOperateLogs")
    BackFrontMessage getAllOperateLogs(String operation){
        return logService.getAllOperateLogs(operation);
    }

    @ApiOperation("获取所有的系统日志")
    @Role(id = 1)
    @GetMapping("/getAllSysLogs")
    BackFrontMessage getAllSysLogs(){
        return logService.getAllSysLogs();
    }

    @ApiOperation("获取所有的日志级别")
    @Role(id = 1)
    @GetMapping("/getAllLogLevels")
    BackFrontMessage getAllLogLevels(){
        return logService.getAllLogLevels();
    }

    @ApiOperation("按日志级别获取日志类型")
    @Role(id = 1)
    @GetMapping("/getLogTypeByLevel")
    BackFrontMessage getLogTypeByLevel(String level){
        return logService.getLogTypeByLevel(level);
    }
}
