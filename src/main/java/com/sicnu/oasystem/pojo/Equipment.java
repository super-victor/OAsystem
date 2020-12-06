package com.sicnu.oasystem.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "设备Id不能为空",groups = {update.class})
    private Integer equipmentId;
    @NotNull(message = "设备分类id不能为空",groups = {Add.class,update.class})
    private Integer equipmentClassifyId;
    @NotNull(message = "会议室Id不能为空",groups = {Add.class,update.class})
    private Integer meetingRoomId;
    @NotBlank(message = "设备名称不能为空",groups = {Add.class})
    private String name;
    @NotNull(message = "设备维护状态不能为空",groups = {Add.class})
    private Integer isMaintain;
    private String remark;
    @NotNull(message = "设备数量不能为空",groups = {Add.class})
    @Min(value = 0,message = "设备数量最小为0",groups = {Add.class,update.class})
    private Integer num;
    private Date createTime;
    private Date updateTime;

    private String meetingRoomName;
    private String equipmentClassifyName;

    public interface Add{

    }
    public interface update{

    }
}
