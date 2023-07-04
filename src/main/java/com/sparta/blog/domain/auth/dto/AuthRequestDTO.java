package com.sparta.blog.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRequestDTO {
    private final String username;
    private final String password;
}
