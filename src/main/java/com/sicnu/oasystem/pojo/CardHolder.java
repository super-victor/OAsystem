package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Integer cardHolderId;
    private Integer employeeId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String company;
    private String department;
    private String position;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    public CardHolder(){}
}
