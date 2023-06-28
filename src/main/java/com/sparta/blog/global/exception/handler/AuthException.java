package com.sparta.blog.global.exception.handler;

public abstract class AuthException extends RuntimeException {
    public AuthException(String msg) {
        super(msg);
    }

}