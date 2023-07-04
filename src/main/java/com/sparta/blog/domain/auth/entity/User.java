package com.sparta.blog.domain.auth.entity;

import com.sparta.blog.domain.auth.dto.AuthRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

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

    @CurrentTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    public static User of(AuthRequestDTO authRequestDTO){
        return User.builder()
                .username(authRequestDTO.getUsername())
                .password(authRequestDTO.getPassword())
                .build();
    }
}
