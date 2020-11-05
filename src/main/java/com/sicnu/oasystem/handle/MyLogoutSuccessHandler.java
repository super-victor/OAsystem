package com.sicnu.oasystem.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 用户退出登录的处理类，用于处理退出登录时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        BackFrontMessage authMessage = new BackFrontMessage(500, "退出登录成功", null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(authMessage);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonData);
        out.flush();
        out.close();
    }

}
