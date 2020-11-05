package com.sicnu.oasystem.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.oasystem.json.BackFrontMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName CustomAuthenticationEntryPoint
 * @Description 未登录情况下的处理类
 * @Author JohnTang
 * @LastChangeDate 2020/7/8 21:01
 * @Version v1.0
 **/

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        BackFrontMessage authMessage = new BackFrontMessage(500, "未登录", null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(authMessage);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(jsonData);
        out.flush();
        out.close();
    }
}
