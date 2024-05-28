package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Create
    public User createUser(String username, String email, String password){
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user);
    }


}
