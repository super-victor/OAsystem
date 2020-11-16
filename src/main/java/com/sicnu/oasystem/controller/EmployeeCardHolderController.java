package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.EmployeeCardHolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName EmployeeCardHolderController
 * @Description 添加描述
 * @Author Waynejwei
 * @LastChangeDate 2020/11/14 16:28
 * @Version v1.0
 */

@Api(tags = "共享名片夹")
@RestController
public class EmployeeCardHolderController {

    @Resource
    EmployeeCardHolderService employeeCardHolderService;

    /**
     * @MethodName insertEmployeeCardHolder
     * @param cardHolderId 名片夹id
     * @Description 分享名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/14
     */
    @ApiOperation(value = "分享名片夹")
    @PostMapping("/EmployeeCardHolder")
    public BackFrontMessage insertEmployeeCardHolder(int cardHolderId){
        return employeeCardHolderService.insertEmployeeCardHolder(cardHolderId);
    }

    /**
     * @MethodName deleteEmployeeCardHolder
     * @param employeeCardHolderId 职工名片夹id
     * @Description 删除职工名片夹by职工名片夹id
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/14
     */
    @ApiOperation(value = "删除名片夹")
    @DeleteMapping("/EmployeeCardHolder")
    public BackFrontMessage deleteEmployeeCardHolder(int employeeCardHolderId){
        return employeeCardHolderService.deleteEmployeeCardHolderByEmployeeCardHolderId(employeeCardHolderId);
    }

    /**
     * @MethodName findEmployeeCardHolder
     * @Description 查找职工所拥有的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    @ApiOperation(value = "查找职工所拥有的名片夹")
    @GetMapping("/EmployeeCardHolder")
    public BackFrontMessage findEmployeeCardHolder(){
        return employeeCardHolderService.findEmployeeCardHolderByEmployeeId();
    }
}
