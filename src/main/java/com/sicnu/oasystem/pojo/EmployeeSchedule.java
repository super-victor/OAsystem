package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer employeeScheduleId;
    private Integer scheduleId;
    private Integer employeeId;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    public EmployeeSchedule(){}
}
