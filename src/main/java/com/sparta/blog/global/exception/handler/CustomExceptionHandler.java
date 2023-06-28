package com.sparta.blog.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException",e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
