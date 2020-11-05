package com.sicnu.oasystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CardHolderClassfy
 * @Description 名片夹分类实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:17
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class CardHolderClassfy {
    private Integer cardHolderClassfyId;
    private String name;
    private Integer ownerId;
    private Date createTime;
    private Date updateTime;
}
