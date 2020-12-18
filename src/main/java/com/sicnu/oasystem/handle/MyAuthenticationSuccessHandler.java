package com.sicnu.oasystem.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.util.JwtTokenUtil;
import com.sicnu.oasystem.util.LogUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用户认证（登录）成功时的处理类，用于处理用户登录成功时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    LogUtil logUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Employee currentEmployee = (Employee) authentication.getPrincipal();
        // response头中放值token
        response.setHeader("token",jwtTokenUtil.generateToken(currentEmployee.getUsername()));
        Map<String, Object> map = new HashMap<>(2);

        map.put("userinfo",currentEmployee);
        map.put("usershow",employeeMapper.findCodesByEmployeeId(currentEmployee.getEmployeeId()));

        logUtil.login("名字："+currentEmployee.getName()+"  登录成功");

        BackFrontMessage authMessage = new BackFrontMessage(200, "登录成功", map);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(authMessage);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonData);
        out.flush();
        out.close();
    }
}
