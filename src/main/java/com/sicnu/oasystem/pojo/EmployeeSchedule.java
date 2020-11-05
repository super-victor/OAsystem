package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName EmployeeSchedule
 * @Description 职工日程对应实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:16
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class EmployeeSchedule {
    private Integer employeeScheduleId;
    private Integer scheduleId;
    private Integer employeeId;
    private Date createTime;
    private Date updateTime;
}
