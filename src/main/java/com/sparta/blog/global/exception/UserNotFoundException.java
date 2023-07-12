package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String userName) {
        super(String.format("%s는 없는 회원입니다.", userName));
    }
}
