package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

/**
 * @ClassName Department
 * @Description 部门实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 15:47
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class Department {
    private Integer departmentId;
    private String name;
    private String phone;
    private Date createTime;
    private Date updateTime;
}