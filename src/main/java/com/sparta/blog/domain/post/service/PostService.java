package com.sparta.blog.domain.post.service;

import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.domain.auth.type.UserRole;
import com.sparta.blog.domain.post.dto.*;
import com.sparta.blog.domain.post.entity.Comment;
import com.sparta.blog.domain.post.entity.Post;
import com.sparta.blog.domain.post.repository.CommentRepository;
import com.sparta.blog.domain.post.repository.PostRepository;
import com.sparta.blog.global.exception.CommentUnAuthException;
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
    private final CommentRepository commentRepository;

    public Post writePost(String userName, PostRequestDTO postRequestDTO){
        User user = findByUserName(userName);
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
    public Post updatePost(Long id, String userName, String userRole, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        if(!userRole.equals(UserRole.ADMIN.toString())) {
            validateUser(post, userName);
        }

        post.changeOf(postRequestDTO);

        return post;
    }

    public void validateUser(Post post, String userName){
        if(!post.getUser().getUsername().equals(userName)){
            throw new PostUnAuthException("작성만 글을 삭제/수정할 수 있습니다.");
        }
    }

    public void deletePost(Long id, String userName, String userRole) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFoundException(id)
        );

        if(!userRole.equals(UserRole.ADMIN.toString())) {
            validateUser(post, userName);
        }

        postRepository.delete(post);
    }

    public CommentResponseDTO writeComment(String userName, CommentWriteRequestDTO commentRequestDTO) {
        Post post = postRepository.findById(commentRequestDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("없는 게시글입니다."));

        Comment comment = new Comment(userName, post, commentRequestDTO.getContent());
        commentRepository.save(comment);
        return new CommentResponseDTO(comment);
    }



    @Transactional
    public CommentResponseDTO updateComment(Long id, CommentUpdateRespuestDTO commentUpdateRespuestDTO, String userName, String userRole) {
        Comment comment = findById(id);

        if(userRole.equals(UserRole.ADMIN.toString()) || userName.equals(comment.getUsername())){
            comment.updateContent(commentUpdateRespuestDTO);
        } else {
            throw new CommentUnAuthException("작성만 글을 삭제/수정할 수 있습니다.");
        }

        return new CommentResponseDTO(comment);
    }

    public PostCommentResultDTO deleteComment(Long id, String userName, String userRole) {
        Comment comment = findById(id);
        if(userRole.equals(UserRole.ADMIN.toString()) || userName.equals(comment.getUsername())){
            commentRepository.delete(comment);
            return new PostCommentResultDTO("댓글 삭제 성공");
        }else {
            throw new CommentUnAuthException("작성만 글을 삭제/수정할 수 있습니다.");
        }
    }



    public User findByUserName(String userName){
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
    }

    public Comment findById(Long id){
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("없는 댓글입니다."));
    }

}
