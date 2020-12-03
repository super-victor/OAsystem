package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Log
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:51
 * @Version v1.0
 */
@Data
public class Log {

    @JsonIgnore
    private Integer logId;
    private String level;
    private String type;
    private String content;
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
}

