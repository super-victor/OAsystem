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
        List<Employee> AllEmplyeAddr=employeeMapper.getAllEmployee();
        if(AllEmplyeAddr!=null){
            return new BackFrontMessage(200,"获取成功",AllEmplyeAddr);
        }
        else{
            return new BackFrontMessage(500,"获取失败",null);
        }
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
        List<String> DepartmentName=departmentMapper.getAllDepartmentName();
        if (DepartmentName!=null){
            return new BackFrontMessage(200,"获取成功",departmentMapper.getAllDepartmentName());
        }else {
            return new BackFrontMessage(500,"获取失败",null);
        }
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
        int employeenums=employeeMapper.getemployeenumbers();
        List<Employee> employees=employeeMapper.getEmployeeBypage(currentPageNum,pageSize);
        if(employeenums!=0 && employees!=null){
            int totalPageNum=0;
            if(employeenums%2==0){
                totalPageNum=employeenums/pageSize;
            }else {
                totalPageNum=employeenums/pageSize+1;
            }
            return new BackFrontMessage(200,"获取成功", new BackFrontPage(pageSize,totalPageNum,currentPageNum,employees));
        }else {
            return new BackFrontMessage(500,"获取失败",null);
        }
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
        List<Employee> employees=employeeMapper.getEmployeeByCondition(name,sex);
        if (employees!=null){
            return new BackFrontMessage(200,"success",employeeMapper.getEmployeeByCondition(name,sex));
        }else{
            return new BackFrontMessage(500,"获取失败",null);
        }
    }
}

