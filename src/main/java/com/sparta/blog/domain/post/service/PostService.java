package com.sparta.blog.domain.post.service;

import com.sparta.blog.domain.post.dto.PostRequestDTO;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.domain.post.repository.PostRepository;
import com.sparta.blog.global.exception.PostNotFoundException;
import com.sparta.blog.global.exception.PostUnAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post writePost(PostRequestDTO postRequestDTO){
        Post newPost = Post.postOf(postRequestDTO);

        return postRepository.save(newPost);
    }

    @Transactional(readOnly= true)
    public List<Post> getPostAll(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly= true)
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );
    }

    @Transactional
    public Post updatePost(Long id, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        checkPassword(post, postRequestDTO);
        post.changeOf(postRequestDTO);

        return post;
    }

    public void checkPassword(Post post, PostRequestDTO postRequestDTO){
        String requestPassword = postRequestDTO.getPassword();
        String dbPassword = post.getPassword();

        if((requestPassword == null && dbPassword == null) || dbPassword.equals(requestPassword)) {
            return;
        } else {
            throw new PostUnAuthException(post.getId());
        }
    }
}
