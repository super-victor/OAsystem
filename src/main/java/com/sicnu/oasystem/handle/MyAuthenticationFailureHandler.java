package com.sicnu.oasystem.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 用户认证（登录）失败的处理类，用于处理用户登录失败时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        BackFrontMessage authMessage;
        if (exception instanceof LockedException) {
            authMessage = new BackFrontMessage(300, "账户被锁定", null);
        } else {
            authMessage = new BackFrontMessage(500,"登录失败", null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(authMessage);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonData);
        out.flush();
        out.close();
    }
}
