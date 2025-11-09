package com.loedean.filter;


import com.loedean.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();

        if(url.contains("/login")){
            log.info("登录请求，直接放行");
            filterChain.doFilter(request,response);
            return;
        }
        String token = request.getHeader("token");

        if(token == null || token.isEmpty()){
            log.info("请求头中不存在token，返回错误信息");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return ;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        log.info("令牌校验通过，放行");
        filterChain.doFilter(request,response);
    }
}
