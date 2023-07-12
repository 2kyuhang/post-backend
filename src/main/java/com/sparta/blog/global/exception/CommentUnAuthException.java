package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.AuthException;

public class CommentUnAuthException extends AuthException {
    public CommentUnAuthException(String msg) {
        super(msg);
    }
}
