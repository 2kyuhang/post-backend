package com.sparta.blog.domain.post.entity;

import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.post.dto.PostRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @CurrentTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static Post postOf(PostRequestDTO postRequestDTO, User user) {
        return Post.builder()
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .user(user)
                .build();
    }

    public void changeOf(PostRequestDTO postRequestDTO) {
        this.title = postRequestDTO.getTitle();
        this.content = postRequestDTO.getContent();
    }
}
