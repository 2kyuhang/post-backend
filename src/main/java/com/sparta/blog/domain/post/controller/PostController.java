package com.sparta.blog.domain.post.controller;

import com.sparta.blog.domain.post.dto.*;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.domain.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponseDTO> getPostAll(){
        List<Post> newPosts = postService.getPostAll();
        return newPosts.stream().map(PostResponseDTO::postResponseOf).toList();
    }

    @PostMapping("/post")
    public PostResponseDTO writePost(@RequestBody PostRequestDTO postRequestDTO, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        Post newPost = postService.writePost(userName, postRequestDTO);

        return PostResponseDTO.postResponseOf(newPost);
    }

    @GetMapping("/post/{id}")
    public PostResponseDTO getPost(@PathVariable Long id){
        Post post = postService.getPost(id);
        return PostResponseDTO.postResponseOf(post);
    }

    @PutMapping("/post/{id}")
    public PostResponseDTO updatePost(@RequestBody PostRequestDTO postRequestDTO, @PathVariable Long id, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        String userRole = getUserRoleOf(httpServletRequest);
        Post updatePost = postService.updatePost(id, userName, userRole,  postRequestDTO);

        return PostResponseDTO.postResponseOf(updatePost);
    }

    @DeleteMapping("/post/{id}")
    public PostTaskResponseDTO deletePost(@RequestBody PostRequestDTO postRequestDTO, @PathVariable Long id, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        String userRole = getUserRoleOf(httpServletRequest);
        postService.deletePost(id, userName, userRole);

        return PostTaskResponseDTO.builder()
                .Task("Delete")
                .msg("성공")
                .build();
    }

    @PostMapping("/comment")
    public CommentResponseDTO writeComment(@RequestBody CommentWriteRequestDTO commentRequestDTO, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        return postService.writeComment(userName, commentRequestDTO);
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDTO updateComment(@PathVariable Long id, @Valid @RequestBody CommentUpdateRespuestDTO commentUpdateRespuestDTO, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        String userRole = getUserRoleOf(httpServletRequest);
        return postService.updateComment(id, commentUpdateRespuestDTO, userName, userRole);
    }

    @DeleteMapping("/comment/{id}")
    public PostCommentResultDTO deleteComment(@PathVariable Long id, HttpServletRequest httpServletRequest){
        String userName = getUserNameOf(httpServletRequest);
        String userRole = getUserRoleOf(httpServletRequest);
        return postService.deleteComment(id, userName, userRole);
    }


    private String getUserNameOf(HttpServletRequest httpServletRequest){
        return (String) httpServletRequest.getAttribute("username");
    }

    private String getUserRoleOf(HttpServletRequest httpServletRequest){
        return (String) httpServletRequest.getAttribute("userRole");
    }

}
