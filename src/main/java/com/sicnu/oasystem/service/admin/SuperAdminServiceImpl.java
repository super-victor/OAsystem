package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Role;
import com.sicnu.oasystem.util.ListUtil;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName SuperAdminServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/26 16:46
 * @Version v1.0
 */


@Component

public class SuperAdminServiceImpl implements SuperAdminService {
    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public BackFrontMessage getAllAdmin() {

        return new BackFrontMessage(200,"获取成功",employeeMapper.findAllAdmin());
    }

    @Override
    public BackFrontMessage updateAdminList(List<Integer> employeeIdList) {
        List<Employee> primaryEmployeeList = employeeMapper.findAllAdmin();
        List<Integer> primaryEmployeeIdList = new ArrayList<>(primaryEmployeeList.size());

        for (Employee employee: primaryEmployeeList) {
            primaryEmployeeIdList.add(employee.getEmployeeId());
        }

        ListUtil.compare(primaryEmployeeIdList, employeeIdList);

        for (Integer employeeId : primaryEmployeeIdList) {
            employeeMapper.deletefromEmployeeRole(employeeId, 2);
        }

        for (Integer employeeId : employeeIdList) {
            employeeMapper.insertIntoEmployeeRole(employeeId,2);
        }

        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public BackFrontMessage lockAdmin(int employeeId,Integer isAccountLocked) {

        // 不能锁自己
        if (employeeId == UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()) {
            return new BackFrontMessage(500,"修改失败",null);
        }
        if (employeeMapper.updateIsAccountLockedInEmployee(employeeId, isAccountLocked) != 0){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }
}
