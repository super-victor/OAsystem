package com.sicnu.oasystem.service.department;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.DepartmentMapper;
import com.sicnu.oasystem.pojo.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName DepartmentServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/28 9:34
 * @Version v1.0
 */

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public BackFrontMessage getAllDepartment() {
        return new BackFrontMessage(200,"获取成功",departmentMapper.findAllDepartment());
    }

    @Override
    public BackFrontMessage addDepartment(String departmentName, String phone) {
        Department department = new Department();
        department.setName(departmentName);
        department.setPhone(phone);
        if (departmentMapper.addDepartment(department) != 0) {
            return new BackFrontMessage(200,"添加成功",null);
        } else {
            return new BackFrontMessage(500,"添加失败",null);
        }
    }

    @Override
    public BackFrontMessage deleteDepartment(Integer departmentId) {
        if (departmentMapper.deleteDepartment(departmentId) != 0) {
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage updateDepartment(Integer departmentId,String departmentName, String phone) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setName(departmentName);
        department.setPhone(phone);

        if (departmentMapper.updateDepartment(department) != 0) {
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }
}
