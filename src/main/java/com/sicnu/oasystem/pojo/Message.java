package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:34
 * @Description:留言实体类
 */
@Data
public class Message {
    private Integer messageId;
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private Integer isRead;
    private Date createTime;
    private Date updateTime;
}
