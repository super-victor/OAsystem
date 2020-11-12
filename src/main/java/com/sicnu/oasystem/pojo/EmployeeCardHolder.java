package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName EmployeeCardHolder
 * @Description 职工名片夹对应类
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 22:17
 * @Version v1.0
 */

@Data
public class EmployeeCardHolder {
    private Integer employeeCardHolderId;
    private Integer employeeId;
    private Integer cardHolderId;
    private Date createTime;
    private Date updateTime;
}
