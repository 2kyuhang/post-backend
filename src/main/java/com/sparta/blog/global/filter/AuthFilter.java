package com.sparta.blog.global.filter;

import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.global.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) && url.startsWith("/api/auth")) {
            chain.doFilter(request, response);
        } else {
            String rawToken = jwtProvider.getTokenFromRequest(httpServletRequest);

            if(StringUtils.hasText(rawToken)) {
                String token = jwtProvider.substringToken(rawToken);

                if(!jwtProvider.validateToken(token)){
                    httpServletResponse.setStatus(400);
                    httpServletResponse.setContentType("text/plain;charset=UTF-8");
                    httpServletResponse.getWriter().write("토큰이 유효하지 않습니다.");
                    return;
                }

                Claims userInfo = jwtProvider.getUserInfoFromToken(token);
                String userName = userInfo.getSubject();
                String userRole = (String) userInfo.get(JwtProvider.USER_ROLE_KEY);

                request.setAttribute("username", userName);
                request.setAttribute("userRole", userRole);

                chain.doFilter(request, response);
            } else {
                httpServletResponse.setStatus(400);
                httpServletResponse.setContentType("text/plain;charset=UTF-8");
                httpServletResponse.getWriter().write("토큰이 유효하지 않습니다.");
            }
        }
    }

}
