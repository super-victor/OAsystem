package com.sicnu.oasystem.pojo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "会议室名称不能为空")
    private String name;
    @NotBlank(message = "会议室位置不能为空")
    private String place;
    @NotNull(message = "会议室是否占用不能为空")
    private Integer isOccapy;
    @NotNull(message = "会议室最大人数不能为空")
    @Min(value = 1,message = "会议室最大人数为1")
    private Integer maxperson;
    private Date createTime;
    private Date updateTime;

    private Meeting meeting;
}
