package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/19:59
 * @Description:会议实体类
 */
@Data
public class Meeting {
    private Integer meetingId;  //会议ID
    private Integer meetingRoomId;  //会议室ID
    private Integer status;   //会议状态
    private Integer employeeId; //员工ID
    private String record;   //会议记录
    private String remark;   //备注
    private String name;    //会议名称
    private Integer appoinmentStatus;   //会议预约状态
    @JsonIgnore
    private Date createTime;  //创建时间
    @JsonIgnore
    private Date updateTime;  //修改时间
    private Date startTime;   //会议开始时间
    private Date endTime; //会议结束时间
    private Integer peopleNum; //会议人数

    private String meetingRoomName;
}
