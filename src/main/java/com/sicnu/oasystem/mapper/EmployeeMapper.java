package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Department;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName EmployeeMapper
 * @Description 操作employee表的mapper
 * @Author JohnTang
 * @LastChangeDate 2020/11/4 23:06
 * @Version v1.0
 */


@Mapper
public interface EmployeeMapper {

    /**
     * @MethodName findEmployeeByUsername
     * @Param String username
     * @Description 找出员工by员工用户名
     * @Return Employee
     * @LastChangeDate 2020/11/5
     * @Version v1.0
     */
    Employee findEmployeeByUsername(String username);

    /***
     * @MethodName findDepartmentByDepartmentId
     * @param departmentId
     * @Description 根据部门id找部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.Department
     * @LastChangeDate 2020/11/8
     */
    Department findDepartmentByDepartmentId(int departmentId);

    /**
     * @MethodName findEmployeeByEmployeeId
     * @Param int employeeId
     * @Description 找出员工by员工id
     * @Author JohnTang
     * @Return
     * @LastChangeDate 2020/11/6
     */
    Employee findEmployeeByEmployeeId(int employeeId);

    /**
     * @MethodName findRolesByEmployeeId
     * @Param int employeeId
     * @Description 找出所有权限列表by员工id
     * @Author JohnTang
     * @Return List<Role>
     * @LastChangeDate 2020/11/5
     */
    List<Role> findRolesByEmployeeId(int employeeId);

    /**
     * @MethodName updatePartlyEmployeeByEmployeeId
     * @Param int employeeId, String phone, String email, String homeAddress
     * @Description 更新员工部分信息by员工id
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updatePartlyEmployeeByEmployeeId(int employeeId, String phone, String email, String homeAddress);

    /**
     * @MethodName getAllEmployee
     * @param
     * @Description 获取所有员工的通讯录
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee> getAllEmployee();

    /**
     * @MethodName getEmployeeBypage
     * @param currentPageNum
     * @param pageSize
     * @Description 按页获取员工数据
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee> getEmployeeBypage(Integer currentPageNum,Integer pageSize);

    /**
     * @MethodName getemployeenumbers
     * @param
     * @Description 获取所有员工的数量
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/9
     */

    Integer getemployeenumbers();

    /**
     * @MethodName getEmployeeBycondition
     * @param name
     * @param sex
     * @Description 通过名字和性别获取员工
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee>getEmployeeByCondition(String name,String sex);
}
