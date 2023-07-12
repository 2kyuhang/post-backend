package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.UserException;

public class UserDuplicateException extends UserException {

    public UserDuplicateException(String username) {
        super(String.format("%s는 중복된 유저이름입니다.", username));
    }
}
