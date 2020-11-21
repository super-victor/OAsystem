package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

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
    @JsonIgnore
    private Integer scheduleId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endTime;
    private Integer leader;
    private String location;
    private String content;
    private String remark;
    private Integer type;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private static final Map<Integer,String> typeList = new HashMap<>(){
        {
            put(1,"聚餐");
            put(2,"会议");
            put(3,"合同签署");
            put(4,"项目");
            put(5,"采购");
            put(6,"资金流转");
            put(7,"出差");
            put(8,"其它");
        }
    };
}
