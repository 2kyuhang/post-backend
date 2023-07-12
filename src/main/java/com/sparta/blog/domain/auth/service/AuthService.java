package com.sparta.blog.domain.auth.service;

import com.sparta.blog.domain.auth.dto.AuthRequestDTO;
import com.sparta.blog.domain.auth.entity.User;
import com.sparta.blog.domain.auth.repository.UserRepository;
import com.sparta.blog.domain.auth.type.UserRole;
import com.sparta.blog.global.exception.UserDuplicateException;
import com.sparta.blog.global.exception.UserNotFoundException;
import com.sparta.blog.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private static final String ADMIN_TOKEN = "ADMIN_TOKEN";

    public void signup(AuthRequestDTO authRequestDTO) {
        String userName = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();
        UserRole userRole = UserRole.USER;

        validateUser(userName, password);

        if(authRequestDTO.isAdmin()){
            if(!ADMIN_TOKEN.equals(authRequestDTO.getAdminToken())){
                throw new IllegalArgumentException("Admin Token Not Valid");
            }
            userRole = UserRole.ADMIN;
        }

        userRepository.save(User.builder()
                .username(userName)
                .password(passwordEncoder.encode(password))
                .userRole(userRole)
                .build());
    }

    public String login(AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByUsername(authRequestDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(authRequestDTO.getUsername()));

        if(!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtProvider.createToken(user);
    }


    private void validateUser(String userName, String password){
        if(!validateUsername(userName)) {
            throw new IllegalArgumentException("유저이름 규칙이 맞지 않습니다.");
        }

        if(!validatePassword(password)) {
            throw new IllegalArgumentException("패스워드 규칙이 맞지 않습니다.");
        }

        if(checkDupUserName(userName)) {
            throw new UserDuplicateException(userName);
        }
    }

    private boolean validateUsername(String userName){
        return userName.matches("[a-z0-9]{4,10}");
    }

    private boolean validatePassword(String password){
        return password.matches("[a-zA-Z0-9`~!@#$%^&*()-_=+\\|;:'\",.<>/?]{8,15}");
    }

    private boolean checkDupUserName(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }


}
