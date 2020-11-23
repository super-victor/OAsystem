package com.sicnu.oasystem.pojo.limit;


import lombok.Data;


/**
 * @ClassName EmployeeLimitA
 * @Description 限制访问的职工实体类,限定：除自己以外的普通职工
 * @Author JohnTang
 * @LastChangeDate 2020/11/23 12:37
 * @Version v1.0
 */

@Data
public class EmployeeLimitA {
    private Integer employeeId;
    private String name;
    private String phone;
    private String email;
    private String sex;
    private String departmentName;
    private String position;
    private String homeAddress;
}
