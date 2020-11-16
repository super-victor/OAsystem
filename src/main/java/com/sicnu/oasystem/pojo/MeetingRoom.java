package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:30
 * @Description:会议室实体类
 */
@Data
public class MeetingRoom {
    private Integer meetingRoomId;
    private String name;
    private String place;
    private Integer isOccapy;
    private Integer maxperson;
    private Date createTime;
    private Date updateTime;

    private Meeting meeting;
}
