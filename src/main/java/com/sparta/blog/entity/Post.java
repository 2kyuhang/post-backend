package com.sparta.blog.entity;

import com.sparta.blog.dto.PostRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
