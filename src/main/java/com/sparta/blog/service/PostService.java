package com.sparta.blog.service;

import com.sparta.blog.dto.PostRequestDTO;
import com.sparta.blog.entity.Post;
import com.sparta.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post writePost(PostRequestDTO postRequestDTO){
        Post newPost = Post.postOf(postRequestDTO);

        return postRepository.save(newPost);
    }

    public List<Post> getPostAll(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
