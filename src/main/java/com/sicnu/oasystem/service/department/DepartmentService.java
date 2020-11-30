package com.sicnu.oasystem.service.department;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName DepartmentService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/28 9:30
 * @Version v1.0
 */

public interface DepartmentService {

    BackFrontMessage getAllDepartment();

    BackFrontMessage addDepartment(String departmentName, String phone);

    BackFrontMessage deleteDepartment(Integer departmentId);

    BackFrontMessage updateDepartment(Integer departmentId,String departmentName, String phone);
}

