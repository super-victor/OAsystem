package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * @ClassName EmployeeLimit
 * @Description 受约束的职工
 * @Author JohnTang
 * @LastChangeDate 2020/11/15 18:48
 * @Version v1.0
 */

public class EmployeeLimit {
    private Integer employeeId;
    @JsonIgnore
    private String username;
    private String name;
    @JsonIgnore
    private String password;

    private String phone;
    private String email;
    private Date birthday;
    private String idCard;
    private String sex;
    private Date entryTime;
    private int departmentId;
    private String position;
    private String homeAddress;
    @JsonIgnore
    private Date passwordChangeDate;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private List<Role> authorities;
    private Department department;
}
