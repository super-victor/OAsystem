package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:32
 * @Description:设备分类实体类
 */
@Data
public class EquipmentClassify {
    private Integer equipmentClassifyId;
    private Integer name;
    private Date createTime;
    private Date updateTime;
}
