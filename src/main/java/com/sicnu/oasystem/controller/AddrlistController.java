package com.sicnu.oasystem.controller;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.AddrListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

/**
 * @ClassName AdrrlistController
 * @Description 通讯录相关控制器
 * @Author pan
 * @LastChangeDate 2020/11/6 18:17
 * @Version v1.0
 */

@RestController
@Api(tags = "通讯录")
public class AddrlistController {

//    @Resource
//    AddrListServicelmpl addrListService;
      @Resource
      AddrListService addrListService;

    /**
     * @MethodName getaddrlist
     * @param
     * @Description 获取所有员工通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取所有员工的通讯录信息")
    @GetMapping("/addr")
    public BackFrontMessage getAddrList(){
        return addrListService.getAllEmplyeAddr();
    }

    /**
     * @MethodName getdepartmentname
     * @param
     * @Description 获取部门信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取所有的部门名称")
    @GetMapping("/deaprtment")
    public BackFrontMessage getDepartmentName(){return addrListService.getDeparmentName();}

    /**
     * @MethodName getemplBypage
     * @param currentPageNum
     * @param pageSize
     * @Description 分页获取员工通讯录信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "按页获取员工的通讯录信息")
    @GetMapping("/getBypage")
    public  BackFrontMessage getEmplByPage(Integer currentPageNum,Integer pageSize){
        return addrListService.getEmployeeBypage(currentPageNum,pageSize);
    }

    /**
     * @MethodName getemployeeByconditon
     * @param name 姓名
     * @param sex 性别
     * @Description 按条件获取员工通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/9
     */
    @ApiOperation(value = "按条件获取员工的通讯录")
    @GetMapping("/getBypage/condition")
    public BackFrontMessage getEmployeeByConditon(String name,String sex){
        if(name==null){
            return addrListService.getEmployeeByCondition(name,sex);
        }else {
            return addrListService.getEmployeeByCondition("%"+name+"%",sex);
        }
    }
}

