package com.sparta.blog.domain.post.service;

import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.auth.repository.UserRepository;
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
    private final UserRepository userRepository;

    public Post writePost(String userName, PostRequestDTO postRequestDTO){
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
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
    public Post updatePost(Long id, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

//        checkPassword(id, postRequestDTO.getPassword(), post.getPassword());
        post.changeOf(postRequestDTO, null);

        return post;
    }

    public void checkPassword(Long id, String requestPassword, String dbPassword){
        if(requestPassword == null){
            if(dbPassword != null) throw new PostUnAuthException(id);
        } else if(dbPassword == null){
            throw new PostUnAuthException(id);
        } else {
            if(!requestPassword.equals(dbPassword)) throw new PostUnAuthException(id);
        }
    }

    public void deletePost(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        checkPassword(id, password, post.getPassword());
        postRepository.delete(post);
    }
}
