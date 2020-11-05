package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:15
 * @Description: 职工角色对应实体类
 */
@Data
public class EmployeeRole {
    private Integer employeeRoleId;  //职工角色ID
    private Integer employeeId;     //职工ID
    private Integer RoleId;         //角色ID
    private Date createTime;       //创建时间
    private Date updateTime;       //修改时间
}
