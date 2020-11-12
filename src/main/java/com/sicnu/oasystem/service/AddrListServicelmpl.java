package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.json.BackFrontPage;
import com.sicnu.oasystem.mapper.DepartmentMapper;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AddrListService
 * @Description 通讯录服务
 * @Author pan
 * @LastChangeDate 2020/11/6 18:20
 * @Version v1.0
 */
@Service
public class AddrListServicelmpl implements AddrListService {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    DepartmentMapper departmentMapper;

    /**
     * @MethodName getAllEmplyee
     * @param
     * @Description 获取通讯录所需的所有员工的信息
     * @Author panA
     * @Return com.sicnu.oasystem.json.BackFrontMessagAe
     * @LastChangeDate 2020/11/8
     */
    public BackFrontMessage getAllEmplyeAddr(){
         return new BackFrontMessage(200,"获取成功",employeeMapper.getAllEmployee());
    }
    /**
     * @MethodName getDeparmentName
     * @param
     * @Description 获取部门信息
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    public BackFrontMessage getDeparmentName(){
        return new BackFrontMessage(200,"success",departmentMapper.getAlldeaprtmentname());
    }

    /**
     * @MethodName getEmployeeBypage
     * @param currentPageNum
     * @param pageSize
     * @Description 使用分页获取通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/8
     */
    public BackFrontMessage getEmployeeBypage(Integer currentPageNum,Integer pageSize){
        return new BackFrontMessage(200,"success",new BackFrontPage(pageSize,employeeMapper.getemployeenumbers(),currentPageNum,employeeMapper.getEmployeeBypage(currentPageNum,pageSize)));
    }

    /**
     * @MethodName getEmployeeByCondition
     * @param name
     * @param sex
     * @Description 按条件获取员工通讯录
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/9
     */
    @Override
    public BackFrontMessage getEmployeeByCondition(String name, String sex) {
        return new BackFrontMessage(200,"success",employeeMapper.getEmployeeBycondition(name,sex));
    }
}

