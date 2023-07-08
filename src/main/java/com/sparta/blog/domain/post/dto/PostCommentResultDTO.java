package com.sparta.blog.domain.post.dto;

import lombok.Getter;

@Getter
public class PostCommentResultDTO {
    private String msg;
    private int statusCode;

    public PostCommentResultDTO() {
    }

    public PostCommentResultDTO(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
