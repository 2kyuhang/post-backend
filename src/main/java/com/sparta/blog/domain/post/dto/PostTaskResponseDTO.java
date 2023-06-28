package com.sparta.blog.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostTaskResponseDTO {
    private final String Task;
    private final String msg;
}
