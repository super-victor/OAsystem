package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    // 权限列表
    // 1 ROLE_Admin
    // 2 ROLE_Censor
    private int roleId;
    private String name;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    @Override
    public String getAuthority() {
        return name;
    }
}
