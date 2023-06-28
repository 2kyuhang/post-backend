package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.AuthException;

public class PostUnAuthException extends AuthException {
    public PostUnAuthException(Long id) {
        super(String.format("UnAuthorized In Post  - postId: %d", id));
    }
}
