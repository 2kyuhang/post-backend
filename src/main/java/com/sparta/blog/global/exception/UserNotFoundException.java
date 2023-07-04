package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String userName) {
        super(String.format("User is not found - userName: %s", userName));
    }
}
