package com.sparta.blog.domain.post.dto;

import com.sparta.blog.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDTO {
    private final String title;
    private final String username;
    private final String content;
    private final LocalDateTime createdAt;

    public static PostResponseDTO postResponseOf(Post post){
       return PostResponseDTO.builder()
               .title(post.getTitle())
               .username(post.getUser().getUsername())
               .content(post.getContent())
               .createdAt(post.getCreatedAt())
               .build();
    }
}
