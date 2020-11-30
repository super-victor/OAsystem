package com.sicnu.oasystem.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CArdHolder
 * @Description 名片夹实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:17
 * @Version v1.0
 */

@ApiModel(value = "CardHolder", description = "名片夹")
@Data
@AllArgsConstructor
public class CardHolder {
    private Integer cardHolderId;
    @ApiModelProperty(required = true)
    private String name;
    private Integer ownerId;
    private Date createTime;
    private Date updateTime;

    public CardHolder(){}
}
