package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:20
 * @Description:收文实体类
 */
@Data
public class ReceiveFile {
    private Integer receiveId;   //收文ID
    private Integer sendFileId;  //发文ID
    private Integer receiverId;  //收件人ID
    private Integer isRecceived; //是否收到
    private Date createTime;    //创建时间
    private Date updateTime;    //修改时间
}
