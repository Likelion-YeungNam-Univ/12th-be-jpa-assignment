package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserCreateRequestDto;
import com.example.blog.domain.user.dto.UserPasswordRequestDto;
import com.example.blog.domain.user.dto.UserResponseDto;
import com.example.blog.domain.user.dto.UserUpdateRequestDto;
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
    public User create(UserCreateRequestDto request) {
        User createUser = request.toEntity();
        return userRepository.save(createUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto read(Long userId) {
        User foundUser = fetchByUserId(userId);
        return UserResponseDto.fromEntity(foundUser);
    }

    @Transactional
    public User update(Long userId, UserUpdateRequestDto request) {
        User foundUser = ownerCheck(userId, request.userId());
        foundUser.update(request.email());
        return foundUser;
    }

    @Transactional
    public void delete(Long userId, UserUpdateRequestDto request) {
        User foundUser = ownerCheck(userId, request.userId());
        userRepository.delete(foundUser);
    }

    @Transactional
    public User resetPassword(Long userId, UserPasswordRequestDto request) {
        User foundUser = ownerCheck(userId, request.userId());
        foundUser.resetPassword(request.password());
        return foundUser;
    }

    @Transactional(readOnly = true)
    public User ownerCheck(Long ownerId, Long currentId){
        if(!ownerId.equals(currentId)) throw new IllegalArgumentException("해당 계정의 주인이 아닙니다.");
        return findById(ownerId);
    }
}
