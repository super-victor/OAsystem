package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.EmployeeCardHolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
     * @param cardHolderClassfyId 名片夹分类id
     * @Description 分享名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/14
     */
    @ApiOperation(value = "通过名片夹id添加职工名片夹(分享名片夹)")
    @PostMapping("/EmployeeCardHolder")
    public BackFrontMessage insertEmployeeCardHolder(@RequestParam int cardHolderId, @RequestParam int cardHolderClassfyId){
        return employeeCardHolderService.insertEmployeeCardHolder(cardHolderId, cardHolderClassfyId);
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
    public BackFrontMessage deleteEmployeeCardHolder(@RequestParam int employeeCardHolderId){
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
    @GetMapping("/findEmployeeCardHolderByEmployeeId")
    public BackFrontMessage findEmployeeCardHolderByEmployeeId(){
        return employeeCardHolderService.findEmployeeCardHolderByEmployeeId();
    }

    /**
     * @MethodName findEmployeeCardHolderByCardHolderClassfyId
     * @param  cardHolderClassfyId 名片夹分类id
     * @Description 查找某一分类下的名片夹
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    @ApiOperation(value = "查找某一分类下的名片夹")
    @GetMapping("/findEmployeeCardHolderByCardHolderClassfyId")
    public BackFrontMessage findEmployeeCardHolderByCardHolderClassfyId(@RequestParam int cardHolderClassfyId){
        return employeeCardHolderService.findEmployeeCardHolderByCardHolderClassfyId(cardHolderClassfyId);
    }

    /**
     * @MethodName updateEmployeeCardHolderClassfy
     * @param employeeCardHolderId 职工名片夹id
     * @param newCardHolderClassfyId 新的名片夹分类id
     * @Description 修改名片夹分类
     * @Author Waynejwei
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    @ApiOperation(value = "修改名片夹的分类")
    @PutMapping("/EmployeeCardHolder")
    public BackFrontMessage updateEmployeeCardHolderClassfy(@RequestParam int employeeCardHolderId, @RequestParam int newCardHolderClassfyId){
        return employeeCardHolderService.updateCardHolderClassfyIdBy(employeeCardHolderId, newCardHolderClassfyId);
    }
}
