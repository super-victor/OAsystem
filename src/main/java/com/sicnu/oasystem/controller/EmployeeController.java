package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.AnthenticateService;
import com.sicnu.oasystem.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @GetMapping("/selfprofile")
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
    @PutMapping("/selfprofile")
    public BackFrontMessage changeSelfProfile(@RequestBody Map<String,String> resultMap){
        return employeeService.changeSelfPorfile(resultMap.get("phone"), resultMap.get("email"), resultMap.get("homeAddress"));
    }

}
