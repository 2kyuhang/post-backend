package com.sparta.blog.domain.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDTO {
    private String title;
    private String writer;
    private String password;
    private String content;
}
