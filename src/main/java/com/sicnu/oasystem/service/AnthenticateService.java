package com.sicnu.oasystem.service;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AnthenticationService
 * @Description 认证有关服务
 * @Author JohnTang
 * @LastChangeDate 2020/11/5 18:45
 * @Version v1.0
 */

@Service
public class AnthenticateService implements UserDetailsService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * @MethodName loadUserByUsername
     * @Param String s
     * @Description 根据用户名加载用户拿给springsecurity框架
     * @Author JohnTang
     * @Return UserDetails
     * @LastChangeDate 2020/11/5
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = employeeMapper.findEmployeeByUsername(s);
        if (employee == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roles = employeeMapper.findRolesByEmployeeId(employee.getEmployeeId());
        employee.setAuthorities(roles);
        return employee;
    }
}
