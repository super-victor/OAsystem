package com.sicnu.oasystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:23
 * @Description:发文表实体类
 */

@Data
public class SendFile {
    private Integer sendfileId;   //发文ID
    private Integer type; //发文类型
    private String title;  //标题
    private String annexUrl; //附件URL
    private String context;  //正文 
    private Integer sendPsrsonNum;  //分发人数
    private Integer isUrgent;  //是否紧急
    private String remark; //备注
    private Integer senderId; //发件人ID
    private Integer censorId; //审查人ID
    private Integer status; //是否通过审查
    private Date createTime; //创建时间
    private Date updateTime;  //修改时间
}
