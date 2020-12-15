package com.sicnu.oasystem.service.department;

import com.sicnu.oasystem.json.BackFrontMessage;

import java.util.Map;

/**
 * @InterfaceName DepartmentService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/28 9:30
 * @Version v1.0
 */

public interface DepartmentService {

    /**
     * @MethodName getAllDepartment
     * @param
     * @Description 获取所有部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage getAllDepartment();

    /**
     * @MethodName addDepartment
     * @param departmentName
     * @param phone
     * @Description 添加部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage addDepartment(String departmentName, String phone);

    /**
     * @MethodName deleteDepartment
     * @param departmentId
     * @Description 删除部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage deleteDepartment(Integer departmentId);

    /**
     * @MethodName updateDepartment
     * @param departmentId
     * @param departmentName
     * @param phone
     * @Description 更新部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/15
     */
    BackFrontMessage updateDepartment(Integer departmentId,String departmentName, String phone);

    /**
     * @MethodName getDepartmentSeeAbleData
     * @param
     * @Description 获取部门有关可视化数据
     * @Author JohnTang
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     * @LastChangeDate 2020/12/15
     */
    Map<String, Object> getDepartmentSeeAbleData();
}

