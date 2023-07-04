package com.sparta.blog.domain.post.service;

import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.domain.post.dto.PostRequestDTO;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.domain.post.repository.PostRepository;
import com.sparta.blog.global.exception.PostNotFoundException;
import com.sparta.blog.global.exception.PostUnAuthException;
import com.sparta.blog.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post writePost(String userName, PostRequestDTO postRequestDTO){
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
        Post newPost = Post.postOf(postRequestDTO, user);

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
    public Post updatePost(Long id, String userName, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        validateUser(post, userName);
        post.changeOf(postRequestDTO);

        return post;
    }

    public void validateUser(Post post, String userName){
        if(!post.getUser().getUsername().equals(userName)){
            throw new PostUnAuthException(post.getId());
        }
    }

    public void deletePost(Long id, String userName) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        validateUser(post, userName);

        postRepository.delete(post);
    }
}
