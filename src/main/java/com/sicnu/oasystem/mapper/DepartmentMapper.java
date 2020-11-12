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

    List<Department> getAllDeaprtmentName();
}
