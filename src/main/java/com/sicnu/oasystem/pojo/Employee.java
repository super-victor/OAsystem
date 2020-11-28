package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "employee",description = "职工信息")
@Data
public class Employee implements UserDetails {
    private Integer employeeId;
    private String username;
    @ApiModelProperty(required = true)
    private String name;
    @JsonIgnore
    private String password;
    @ApiModelProperty(required = true)
    private String phone;
    @ApiModelProperty(required = true)
    private String email;
    @ApiModelProperty(required = true)
    private String idCard;
    @ApiModelProperty(required = true)
    private String sex;
    private Date entryTime;
    @ApiModelProperty(required = true)
    private String departmentName;
    private String position;
    @ApiModelProperty(required = true)
    private String homeAddress;
    private int isAccountLocked;
    @JsonIgnore
    private Date passwordChangeDate;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private List<Role> authorities;
    private Department department;

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
        if (isAccountLocked == 1) {
            return false;
        } else {
            return true;
        }
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
