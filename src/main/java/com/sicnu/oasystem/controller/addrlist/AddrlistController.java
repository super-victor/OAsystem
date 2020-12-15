package com.sicnu.oasystem.controller.addrlist;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.addrlist.AddrListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName AdrrlistController
 * @Description 通讯录相关控制器
 * @Author pan
 * @LastChangeDate 2020/11/6 18:17
 * @Version v1.0
 */
@Api(tags = "通讯录")
@RestController
@Validated
public class AddrlistController {

    @Resource
    AddrListService addrListService;

    /**
     * @MethodName getaddrlist
     * @param
     * @Descrip tion 获取所有员工通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "获取所有员工的通讯录")
    @GetMapping("/addressbook")
    public BackFrontMessage getaddrlist(){
        return addrListService.getAllEmplyeAddr();
    }


    @ApiOperation(value = "获取所有员工的通讯录")
    @GetMapping("/AddressbookInfo")
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
    @ApiOperation(value = "获取所有部门的部门名称")
    @GetMapping("/getAllDepartmentName")
    public BackFrontMessage getDepartmentName(){
        return addrListService.getDeparmentName();
    }

    /**
     * @MethodName getemplBypage
     * @param currentPageNum
     * @param pageSize
     * @Description 分页获取员工通讯录信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    @ApiOperation(value = "分页获取员工通讯录")
    @GetMapping("/addressbookbypage")
    public  BackFrontMessage getemplBypage(@Validated @NotNull(message = "当前页数不能为空") @Min(value = 1,message = "当前页数最小为1") Integer currentPageNum, @Validated @NotNull(message = "页面大小不能为空") Integer pageSize){
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
    @ApiOperation(value = "按条件查询员工通讯录")
    @GetMapping("/getBypage/condition")
    public BackFrontMessage getemployeeByconditon(String name,String sex){
        if(name==null){
            return addrListService.getEmployeeByCondition(name,sex);
        }else {
            return addrListService.getEmployeeByCondition("%"+name+"%",sex);
        }
    }
}

