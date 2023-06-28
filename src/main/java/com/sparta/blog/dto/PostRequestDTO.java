package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDTO {
    private String title;
    private String writer;
    private String password;
    private String content;
}
