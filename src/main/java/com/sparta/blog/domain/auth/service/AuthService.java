package com.sparta.blog.domain.auth.service;

import com.sparta.blog.domain.auth.dto.AuthRequestDTO;
import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.global.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void signup(AuthRequestDTO authRequestDTO) {
        validateUser(authRequestDTO.getUsername(), authRequestDTO.getPassword());

        userRepository.save(User.of(authRequestDTO));
    }

    public void login(AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByUsername(authRequestDTO.getUsername()).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        if(!user.getPassword().equals(authRequestDTO.getPassword())) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }


    private void validateUser(String userName, String password){
        if(!validateUsername(userName)) throw new IllegalArgumentException("유저이름 규칙이 맞지 않습니다.");
        if(!validatePassword(password)) throw new IllegalArgumentException("패스워드 규칙이 맞지 않습니다.");
        if(checkDupUserName(userName)) throw new UserDuplicateException(userName);
    }

    private boolean validateUsername(String userName){
        return userName.matches("[a-z0-9]+") && userName.length() >= 4 && userName.length() <= 10;
    }

    private boolean validatePassword(String password){
        return password.matches("[a-zA-Z0-9]+") && password.length() >=8 && password.length() <=15;
    }

    private boolean checkDupUserName(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }


}
