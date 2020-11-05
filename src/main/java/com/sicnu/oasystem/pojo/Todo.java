package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:35
 * @Description:代办事项实体类
 */
@Data
public class Todo {
    private Integer todoId;
    private String name;
    private String content;
    private Date beginTime;
    private Date deadline;
    private Integer isUrgent;
    private Integer isFinish;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
