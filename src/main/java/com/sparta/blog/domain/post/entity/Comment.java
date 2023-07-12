package com.sparta.blog.domain.post.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.blog.domain.post.dto.CommentResponseDTO;
import com.sparta.blog.domain.post.dto.CommentUpdateRespuestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @CurrentTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CurrentTimestamp
    @Column(updatable = true)
    private LocalDateTime modifiedAt;

    private String username;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String userName, Post post, String comment){
        this.username = userName;
        this.content = comment;
        this.post = post;
    }

    public void updateContent(CommentUpdateRespuestDTO commentUpdateRespuestDTO) {
        this.content = commentUpdateRespuestDTO.getContent();
    }

}
