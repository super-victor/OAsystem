package com.sicnu.oasystem.config;

import com.sicnu.oasystem.handle.*;
import com.sicnu.oasystem.filter.JwtAuthorizationTokenFilter;
import com.sicnu.oasystem.service.AnthenticateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyWebSecurityConfigurerAdapter
 * @Description spring security配置类
 * @Author JohnTang
 * @LastChangeDate 2020/2/1
 * @Version v1.0
 */

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Resource
    AnthenticateService anthenticateService;

    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    @Resource
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

//  启用密码加密
//	@Bean
//    PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
//	}


    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        return new DelegatingPasswordEncoder("noop", encoders);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anthenticateService).passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
    }


    //注意数据库中的权限表中的权限名称必须是ROLE_****的形式，不然无法自动匹配 !!!!
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                  .antMatchers("/swagger-ui/**","/v3/**","/swagger-resources/**","/profile/**")
                  .permitAll()
//                .antMatchers("/user/**")
//                .hasRole("User")
//                .antMatchers("/merchant/**")
//                .hasRole("Merchant")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(myAuthenticationAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler).permitAll();
    }
}
