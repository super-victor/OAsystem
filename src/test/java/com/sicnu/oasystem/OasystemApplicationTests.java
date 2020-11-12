package com.sicnu.oasystem;

import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class OasystemApplicationTests {

//    @Resource
//    AddrListMapper addrListMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
//        List<Employee>employees=employeeMapper.getAllEmployee();
//        List<Employee>employees=employeeMapper.getEmployeeBypage(1,1);
        List<Employee>employees=employeeMapper.getEmployeeByCondition(null,"f");
        for (Employee e:employees){
            System.out.println(e);
        }
    }

}
