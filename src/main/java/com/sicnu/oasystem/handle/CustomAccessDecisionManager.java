package com.sicnu.oasystem.handle;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

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
        Iterator<ConfigAttribute> iterator = collection.iterator();
        String first = iterator.next().getAttribute();
        // 先判断标志，没有需要角色直接pass
        if (first.equals("0")) {
            return;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.contains(first)) {
            throw new AccessDeniedException("权限不足");
        }
        while (iterator.hasNext()) {
            if (!authorities.contains(iterator.next())) {
                throw new AccessDeniedException("权限不足");
            }
        }
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
