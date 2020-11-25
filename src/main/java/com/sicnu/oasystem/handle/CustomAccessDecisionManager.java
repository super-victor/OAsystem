package com.sicnu.oasystem.handle;

import com.sicnu.oasystem.pojo.Role;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName CustomAccessDecisionManager
 * @Description 验证用户是否拥有访问当前url所需的所有角色
 * @Author JohnTang
 * @LastChangeDate 2020/11/19 20:52
 * @Version v1.0
 */

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection == null) {
            return;
        }

        Iterator<ConfigAttribute> iterator = collection.iterator();

        List<Role> authorities = (List<Role>) authentication.getAuthorities();

        while (iterator.hasNext()) {
            String roleName = iterator.next().getAttribute();
            for(Role role:authorities){
                if(role.getAuthority().equals(roleName)){
                    return;
                }
            }
        }

        //需要的权限都没有抛出异常
        throw new AccessDeniedException("权限不足3");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
