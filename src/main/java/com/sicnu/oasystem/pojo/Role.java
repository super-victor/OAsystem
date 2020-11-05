package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:17
 * @Description: 角色实体类
 */
@Data
public class Role {
    private Integer RoleId;  //角色ID
    private String Name; //角色名称
    private Date createTime;  //创建时间
    private Date updateTime;  //修改时间
}
