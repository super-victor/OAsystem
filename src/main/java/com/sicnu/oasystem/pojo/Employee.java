package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Employee
 * @Description 职工实体类
 * @Author JohnTang
 * @LastChangeDate 2020/11/4 22:54
 * @Version v1.0
 */

@Data
public class Employee implements UserDetails {
    @JsonIgnore
    private Integer employeeId;
    private String username;
    private String name;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private Date birthday;
    private String idCard;
    private String sex;
    private Date entryTime;
    private int departmentId;
    private String position;
    private String homeAddress;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
