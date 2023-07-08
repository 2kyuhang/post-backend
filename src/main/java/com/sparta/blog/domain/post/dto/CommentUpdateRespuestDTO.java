package com.sparta.blog.domain.post.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.Nullable;


@Getter
public class CommentUpdateRespuestDTO {

    @Column(nullable = false)
    private String content;
}
