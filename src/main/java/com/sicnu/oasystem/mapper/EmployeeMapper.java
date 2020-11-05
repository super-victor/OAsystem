package com.sicnu.oasystem.mapper;

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
@Component
public interface EmployeeMapper {

    /**
     * @MethodName findEmployeeByEmployeeId
     * @Param username:String
     * @Description 根据员工工号找员工
     * @Return Employee
     * @LastChangeDate 2020/11/5
     * @Version v1.0
     */
    Employee findEmployeeByUsername(String username);

    /**
     * @MethodName
     * @Param
     * @Description 添加描述
     * @Return
     * @LastChangeDate 2020/11/5
     * @Version v1.0
     */
    List<Role> findRolesByEmployeeId(int employeeId);


}
