package com.sicnu.oasystem.controller.department;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.department.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName DepartmentController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/27 17:21
 * @Version v1.0
 */
@Api(tags = "部门管理")
@RestController
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/allDepartment")
    BackFrontMessage getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/department")
    BackFrontMessage addDepartment(@RequestParam String name,@RequestParam String phone){
        return departmentService.addDepartment(name, phone);
    }

    @ApiOperation(value = "修改部门")
    @PutMapping("/department")
    BackFrontMessage updateDepartment(@RequestParam Integer departmentId, @RequestParam String name,@RequestParam String phone){
        return departmentService.updateDepartment(departmentId,name,phone);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/department")
    BackFrontMessage deleteDepartment(@RequestParam Integer departmentId){
        return departmentService.deleteDepartment(departmentId);
    }

}
