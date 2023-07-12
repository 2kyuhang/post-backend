package com.sparta.blog.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AuthRequestDTO {
    private final String username;
    private final String password;
    private final boolean isAdmin;
    private final String adminToken;
}
