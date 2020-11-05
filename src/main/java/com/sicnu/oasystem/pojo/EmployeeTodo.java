package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:38
 * @Description:职工待办事项对应表
 */
@Data
public class EmployeeTodo {
    private Integer employeeTodoId;
    private Integer employeeId;
    private Integer todoId;
    private Date createTime;
    private Date updateTime;
}
