package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Employee
 * @Description 职工实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:10
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class Employee {
    private Integer employeeId;
    private String name;
    private String password;
    private String phone;
    private String email;
    private Date birthday;
    private String idCard;
    private String sex;
    private Date entryTime;
    private Integer departmentId;
    private String position;
    private String homeAddress;
    private Date createTime;
    private Date updateTime;
}
