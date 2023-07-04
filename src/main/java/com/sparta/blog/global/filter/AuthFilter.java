package com.sparta.blog.global.filter;

import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.global.jwt.JwtProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
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
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) && url.startsWith("/api/auth")) {
            chain.doFilter(request, response);
        } else {
            String rawToken = jwtProvider.getTokenFromRequest(httpServletRequest);

            if(StringUtils.hasText(rawToken)) {
                String token = jwtProvider.substringToken(rawToken);

                if(!jwtProvider.validateToken(token)){
                    throw new IllegalArgumentException("Token Error");
                }

                chain.doFilter(request, response);
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }

}
