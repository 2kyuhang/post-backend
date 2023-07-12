package com.sparta.blog.domain.post.dto;

import lombok.Getter;

@Getter
public class PostCommentResultDTO {
    private String msg;

    public PostCommentResultDTO() {
    }

    public PostCommentResultDTO(String msg) {
        this.msg = msg;
    }
}
