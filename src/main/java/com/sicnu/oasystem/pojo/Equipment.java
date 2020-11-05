package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:26
 * @Description:设备实体类
 */
@Data
public class Equipment {
    private Integer equipmentId;
    private Integer equipmentClassifyId;
    private Integer meetingRoomId;
    private String name;
    private Integer isMaintain;
    private String remark;
    private Integer num;
    private Date createTime;
    private Date updateTime;
}
