package com.sparta.blog.domain.post.entity;

import com.sparta.blog.domain.post.dto.PostRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;

    @CurrentTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static Post postOf(PostRequestDTO postRequestDTO) {
        return Post.builder()
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .writer(postRequestDTO.getWriter())
                .password(postRequestDTO.getPassword())
                .build();
    }

    public void changeOf(PostRequestDTO postRequestDTO) {
        this.title = postRequestDTO.getTitle();
        this.content = postRequestDTO.getContent();
        this.writer = postRequestDTO.getWriter();
        this.password = postRequestDTO.getPassword();
    }
}
