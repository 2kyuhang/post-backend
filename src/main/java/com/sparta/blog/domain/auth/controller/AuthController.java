package com.sparta.blog.domain.auth.controller;

import com.sparta.blog.domain.auth.dto.AuthRequestDTO;
import com.sparta.blog.domain.auth.dto.AuthResultDTO;
import com.sparta.blog.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public AuthResultDTO signup(@RequestBody AuthRequestDTO authRequestDTO){
        authService.signup(authRequestDTO);

        return AuthResultDTO
                .builder()
                .task("SignUp")
                .msg("Success")
                .build();
    }

    @PostMapping("/auth/login")
    public AuthResultDTO login(@RequestBody AuthRequestDTO authRequestDTO){
        authService.login(authRequestDTO);
        return AuthResultDTO
                .builder()
                .task("Login")
                .msg("로그인 성공")
                .build();
    }



}
