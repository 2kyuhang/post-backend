package com.sparta.blog.global.exception.handler;

public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
