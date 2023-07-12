package com.sparta.blog.domain.post.repository;

import com.sparta.blog.domain.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
