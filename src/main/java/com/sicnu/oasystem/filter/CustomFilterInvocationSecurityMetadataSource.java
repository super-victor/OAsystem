package com.sicnu.oasystem.filter;

import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Role;
import com.sicnu.oasystem.util.NotLoginException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomFilterInvocationSecurityMetadataSource
 * @Description 根据requestUrl加载所需要的角色
 * @Author JohnTang
 * @LastChangeDate 2020/11/19 15:46
 * @Version v1.0
 */

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException,AuthenticationException {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            throw new NotLoginException("未登录");
        }

        // todo 读取全局url允许名单，并判断
        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();
        String urlPatten = request.getMethod() + " " + request.getRequestURI();
        System.out.println(urlPatten);
        List<String> needRoleIds = employeeMapper.findRoleIdsByUrlPattern(urlPatten);
        // 如果当前url需要的角色为空,返回空
        if (needRoleIds == null) {
            return null;
        }
        String[] needRoleIdsCopy = new String[needRoleIds.size()];
        return SecurityConfig.createList(needRoleIds.toArray(needRoleIdsCopy));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
