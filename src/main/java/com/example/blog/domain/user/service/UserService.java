package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserRequestDto;
import com.example.blog.domain.user.dto.UserResponseDto;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));
    }

    @Transactional(readOnly = true)
    public User fetchByUserId(Long id){
        return userRepository.fetchByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물 없음!"));
    }

    @Transactional
    public User create(UserRequestDto request) {
        User createUser = request.toEntity();
        return userRepository.save(createUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto read(Long userId) {
        User foundUser = fetchByUserId(userId);
        return UserResponseDto.fromEntity(foundUser);
    }
}
