package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Inet4Address;
import java.util.Date;

/**
 * @ClassName CardHolder
 * @Description 名片夹实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:19
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class CardHolder {
    private Integer cardHolderId;
    private Integer cardHolderClassfyId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String qq;
    private String company;
    private String department;
    private String position;
    private Date createTime;
    private Date updateTime;
    public CardHolder(){}
}
