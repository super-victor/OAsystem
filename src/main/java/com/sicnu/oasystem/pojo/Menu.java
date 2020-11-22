package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Menu
 * @Description 菜单
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:55
 * @Version v1.0
 */

@Data
public class Menu {
     private int menuId;
     private String name;
     @JsonIgnore
     private String url;
     @JsonIgnore
     private String code;
     @JsonIgnore
     private Date createTime;
     @JsonIgnore
     private Date updateTime;
}
