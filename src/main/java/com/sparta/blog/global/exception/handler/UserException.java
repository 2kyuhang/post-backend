package com.sparta.blog.global.exception.handler;

public abstract class UserException extends RuntimeException{
    public UserException(String msg) {
        super(msg);
    }
}
