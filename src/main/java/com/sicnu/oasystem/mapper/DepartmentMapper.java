package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Department;
import com.sicnu.oasystem.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @InterfaceName AddrListMapper
 * @Description 部门的Mapper
 * @Author pan
 * @LastChangeDate 2020/11/6 18:24
 * @Version v1.0
 */
@Mapper
public interface DepartmentMapper {

    /**
     * @MethodName getAllDepartmentName
     * @param
     * @Description 获取所有的部门名称
     * @Author JohnTang
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/11/25
     */
    List<String> getAllDepartmentName();

    //获取所有的部门
    List<Department> findAllDepartment();
    //添加部门
    int addDepartment(Department department);
    //更新部门信息
    int updateDepartment(Department department);
    /**
     * @MethodName deleteDepartment
     * @param departmentId 部门Id
     * @Description 删除部门
     * @Author pan
     * @Return int
     * @LastChangeDate 2020/12/10
     */
    int deleteDepartment(int departmentId);

    /***
     * @MethodName findDepartmentByDepartmentId
     * @param departmentId
     * @Description 根据部门id找部门
     * @Author JohnTang
     * @Return com.sicnu.oasystem.pojo.Department
     * @LastChangeDate 2020/11/8
     */
    Department findDepartmentByDepartmentId(int departmentId);
}
