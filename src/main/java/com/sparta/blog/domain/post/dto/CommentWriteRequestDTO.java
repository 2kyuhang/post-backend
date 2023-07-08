package com.sparta.blog.domain.post.dto;

import lombok.Getter;

@Getter
public class CommentWriteRequestDTO {
    private Long id;
    private String content;
}
