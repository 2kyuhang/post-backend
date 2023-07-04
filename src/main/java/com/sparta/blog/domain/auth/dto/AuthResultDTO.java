package com.sparta.blog.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResultDTO {
    private final String task;
    private final String msg;
}
