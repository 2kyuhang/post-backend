package com.sparta.blog.domain.post.service;

import com.sparta.blog.domain.post.dto.PostRequestDTO;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.global.exception.PostNotFoundException;
import com.sparta.blog.domain.post.repository.PostRepository;
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

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );
    }
}
