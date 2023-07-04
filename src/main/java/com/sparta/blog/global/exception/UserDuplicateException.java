package com.sparta.blog.global.exception;

import com.sparta.blog.global.exception.handler.UserException;

public class UserDuplicateException extends UserException {

    public UserDuplicateException(String username) {
        super(String.format("User is duplicate - userName: %s", username));
    }
}
