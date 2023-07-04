package com.sparta.blog.domain.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDTO {
    private String title;
    private String username;
    private String password;
    private String content;
}
