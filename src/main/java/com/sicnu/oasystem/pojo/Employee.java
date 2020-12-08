package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
@Valid
public class Employee implements UserDetails {
    private Integer employeeId;
    @Size(max = 20,min = 5,groups = Add.class)
    @NotNull(groups = Add.class)
    private String username;
    @ApiModelProperty(required = true)
    @NotNull(groups = Add.class)
    private String name;
    private Date birthday;
    @JsonIgnore
    @NotNull(groups = Add.class)
    @Size(max = 20,min = 5)
    private String password;
    @ApiModelProperty(required = true)
    @Pattern(regexp = "^[0-9]*$")
    @NotNull(groups = Add.class)
    private String phone;
    @ApiModelProperty(required = true)
    @Email
    @NotNull(groups = Add.class)
    private String email;
    @ApiModelProperty(required = true)
    @Pattern(regexp = "^[0-9]*$")
    @NotNull(groups = Add.class)
    private String idCard;
    @ApiModelProperty(required = true)
    @Pattern(regexp = "m|f")
    @NotNull(groups = Add.class)
    private String sex;
    private Date entryTime;
    @ApiModelProperty(required = true)
    @Size(max = 10,min = 1)
    @NotNull(groups = Add.class)
    private String departmentName;
    @ApiModelProperty(required = true)
    @Size(max = 10,min = 1)
    @NotNull(groups = Add.class)
    private String position;
    @ApiModelProperty(required = true)
    @Size(max = 20,min = 1)
    @NotNull(groups = Add.class)
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

    public interface Add{
    }

    public interface Update{
    }

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
