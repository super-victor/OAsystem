package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:34
 * @Description:消息实体类
 */
@Data
public class Message {
    private Integer messageId;
    private Integer employeeId;
    private Integer type;
    private String title;
    private String content;
    private Integer isRead;
    private Date createtime;
}
