package com.sicnu.oasystem.filter;

import com.sicnu.oasystem.config.GlobalSecurityConfig;
import com.sicnu.oasystem.util.DataUtil;
import com.sicnu.oasystem.util.DateUtil;
import com.sicnu.oasystem.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName JwtAuthorizationTokenFilter
 * @Description Token过滤器
 * @Author JohnTang
 * @LastChangeDate 2020/9/20 17:14
 * @Version v1.0
 **/

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    @Resource
    UserDetailsService userDetailsService;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    GlobalSecurityConfig globalSecurityConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // todo
        // 1.检查token在用户修改密码后签发
        // 2.如果token快要过期刷新token，

        // 访问量
        DataUtil.Data_Total_Views++;
        if ( !DateUtil.isSameDay(DataUtil.Data_Date,new Date())) {
            DataUtil.Data_Date = new Date();
            DataUtil.Data_Daily_Views = 0;
        }
        DataUtil.Data_Daily_Views++;

        // 判断系统全局允许名单
        boolean flag = true;
        for (String allowUrl: globalSecurityConfig.getAllowUrlList()) {
            if (new AntPathMatcher().match(allowUrl, httpServletRequest.getRequestURI())) {
                flag = false;
            }
        }

        String token = httpServletRequest.getHeader("token");
        if (token != null && flag) {
            try {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            // 捕捉过期和解析失败异常
            } catch (Exception e){

            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
