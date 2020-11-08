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
    private Integer ownerId;
    private Integer ownedId;
    private Integer cardHolderClassfyId;
    private Date createTime;
    private Date updateTime;

    public CardHolder(){}
}
