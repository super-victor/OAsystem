package com.sicnu.oasystem.pojo;

import com.sicnu.oasystem.pojo.limit.EmployeeLimitA;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    private String type; //发文类型
    private String subject;
    private int isPublic; //是否公开
    private String title;  //标题
    private String content;  //正文
    private String urgent;  //是否紧急
    private String remark; //备注
    private Integer senderId; //发件人ID
    private Integer censorId; //审查人ID
    private Integer status; //状态
    private String cersorOpinion; //审稿人意见
    private Date createTime; //创建时间
    private Date updateTime;  //修改时间
    private List<String> annuxUrlList; //附件url列表
    private List<EmployeeLimitA> accessEmployeeIdList;
    private Employee senderDetail;
    private Employee censorDetail;

}
