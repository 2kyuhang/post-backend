package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(Long id) {
        super(String.format("Post is not found - postId: %d", id));
    }
}
