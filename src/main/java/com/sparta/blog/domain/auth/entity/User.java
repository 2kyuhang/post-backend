package com.sparta.blog.domain.auth.entity;

import com.sparta.blog.domain.auth.type.UserRole;
import com.sparta.blog.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @CurrentTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserRole userRole;
}
