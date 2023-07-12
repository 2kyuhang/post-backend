package com.sparta.blog.domain.post.dto;


import com.sparta.blog.domain.post.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUsername();
    }
}
