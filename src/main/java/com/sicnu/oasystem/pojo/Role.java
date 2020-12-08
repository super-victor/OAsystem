package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

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
    // 1 ROLE_SuperAdmin
    // 2 ROLE_Admin
    private Integer roleId;
    private String name;
    private int isAble;
    // 拥有此角色的用户列表
    private List<Employee> employeeList;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    @Override
    public String getAuthority() {
        return roleId.toString();
    }
}
