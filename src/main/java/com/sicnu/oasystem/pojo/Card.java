package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName Card
 * @Description 名片实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:19
 * @Version v1.0
 */

@ApiModel(value = "Card", description = "名片")
@Data
@AllArgsConstructor
public class Card {
    @JsonIgnore
    private Integer cardId;
    @JsonIgnore
    private Integer ownerId;
    @NotNull(message = "名片夹id不能为空")
    private Integer cardHolderId;
    @NotBlank(message = "名片名称不能为空")
    private String name;
    @Length(min = 11, max = 11, message = "电话格式不正确")
    @NotBlank(message = "电话不能为空")
    private String phone;
    @Pattern(regexp = "\\w+@(\\w+\\.){1,3}\\w+", message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "公司不能为空")
    private String company;
    private String department;
    private String position;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    public Card(){}
}
