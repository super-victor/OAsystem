package com.sicnu.oasystem.service.employee.impl;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.service.employee.EmployeeService;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName EmployeeServiceImpl
 * @Description 职工个人事务实现类
 * @Author JohnTang
 * @LastChangeDate 2020/11/8 21:39
 * @Version v1.0
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public BackFrontMessage getSelfProfile() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        return new BackFrontMessage(200, "获取成功", currentEmployee);
    }

    @Override
    public BackFrontMessage changeSelfPorfile(String phone, String email, String homeAddress) {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        if ( employeeMapper.updatePartlyEmployeeByEmployeeId(currentEmployee.getEmployeeId(), phone, email, homeAddress) != 0) {
            return new BackFrontMessage(200, "更新成功", null);
        }else {
            return new BackFrontMessage(500, "更新失败", null);
        }
    }
}
