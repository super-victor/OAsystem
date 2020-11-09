package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.AnthenticateService;
import com.sicnu.oasystem.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName EmployeeController
 * @Description 用户认证相关控制器
 * @Author JohnTang
 * @LastChangeDate 2020/11/5 18:39
 * @Version v1.0
 */

@Api(tags = "个人相关信息")
@RestController
public class EmployeeController {

    @Resource
    EmployeeService employeeService;


    /**
     * @MethodName getSelfProfile
     * @Param
     * @Description 获取个人资料
     * @Author JohnTang
     * @Return BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    @ApiOperation(value = "获取个人资料")
    @GetMapping("/profile")
    public BackFrontMessage getSelfProfile(){
        return employeeService.getSelfProfile();
    }

    /**
     * @MethodName changeUserProfile
     * @Param String phone,String email,String homeAddress
     * @Description 修改自己的个人资料
     * @Author JohnTang
     * @Return BackFrontMessage
     * @LastChangeDate 2020/11/6
     */
    @ApiOperation(value = "修改个人资料")
    @PutMapping("/profile")
    public BackFrontMessage changeSelfProfile(@RequestParam String phone,@RequestParam String email,@RequestParam String homeAddress){
        return employeeService.changeSelfPorfile(phone, email, homeAddress);
    }

}
