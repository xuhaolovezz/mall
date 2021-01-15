package com.mall.common.hander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.vo.MallResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MallAuthenticationFailHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        log.error("认证信息错误：{}",authException.getMessage());

        String msg = "认证信息错误，拒绝访问";
        int status = 200;

        if (authException.getCause() instanceof InvalidTokenException) {
            msg = "token非法，拒绝访问";
            status = HttpServletResponse.SC_FORBIDDEN;
        } else if (authException instanceof InsufficientAuthenticationException) {
            msg = "未携带请求token，拒绝访问";
            status = HttpServletResponse.SC_UNAUTHORIZED;
        }
        MallResult<Void> mallResult = MallResult.fail("200",msg);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(mallResult));
    }
}
