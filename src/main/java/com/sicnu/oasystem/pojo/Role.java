package com.sicnu.oasystem.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * @ClassName Role
 * @Description 角色实体类
 * @Author JohnTang
 * @LastChangeDate 2020/11/5 18:46
 * @Version v1.0
 */

@Data
public class Role implements GrantedAuthority {
    private int roleId;
    private String name;
    private Date createTime;
    private Date updateTime;

    @Override
    public String getAuthority() {
        return name;
    }
}
