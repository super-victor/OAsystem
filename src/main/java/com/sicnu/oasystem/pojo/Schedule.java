package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Schedule
 * @Description 日程实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:14
 * @Version v1.0
 */


@Data
@AllArgsConstructor
public class Schedule {
    private Integer scheduleId;
    private Date startTime;
    private String location;
    private String content;
    private String remark;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
