package com.sparta.blog.domain.post.controller;

import com.sparta.blog.domain.post.dto.PostRequestDTO;
import com.sparta.blog.domain.post.dto.PostResponseDTO;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.domain.post.service.PostService;
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
    public PostResponseDTO writePost(@RequestBody PostRequestDTO postRequestDTO){
        Post newPost = postService.writePost(postRequestDTO);

        return PostResponseDTO.postResponseOf(newPost);
    }

    @GetMapping("/post/{id}")
    public PostResponseDTO getPost(@PathVariable Long id){
        System.out.println(id);
        Post newPost = postService.getPost(id);
        System.out.println(newPost);
        return PostResponseDTO.postResponseOf(newPost);
    }

    @PutMapping("/post/{id}")
    public PostResponseDTO updatePost(@RequestBody PostRequestDTO postRequestDTO){
        return null;
    }

    @DeleteMapping("/post/{id}")
    public PostResponseDTO deletePost(@RequestBody String password){
        return null;
    }
}
