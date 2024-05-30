package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserCreateRequest;
import com.example.blog.domain.user.dto.UserPasswordRequest;
import com.example.blog.domain.user.dto.UserResponseDto;
import com.example.blog.domain.user.dto.UserUpdateRequest;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        log.info("SELECT : user include proxy");
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public User fetchByUserId(Long id) {
        log.info("SELECT : user, post include proxy");
        return userRepository.fetchByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }

    @Transactional
    public User create(UserCreateRequest request) {
        log.info("사용자 생성");
        User createUser = request.toEntity();
        log.info("INSERT : User");
        return userRepository.save(createUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto read(Long userId) {
        log.info("사용자 출력(사용자-게시물)");
        User foundUser = fetchByUserId(userId);
        return UserResponseDto.fromEntity(foundUser);
    }

    @Transactional
    public void update(Long userId, UserUpdateRequest request) {
        log.info("사용자 수정");
        User foundUser = verifyUserOwner(userId, request.userId());
        foundUser.update(request.email());
        log.info("UPDATE : email");
    }

    @Transactional
    public void delete(Long userId, UserUpdateRequest request) {
        log.info("사용자 삭제");
        User foundUser = verifyUserOwner(userId, request.userId());
        log.info("DELETE : User, cascade Post, Comment");
        userRepository.delete(foundUser);
    }

    @Transactional
    public void resetPassword(Long userId, UserPasswordRequest request) {
        log.info("비밀번호 수정");
        User foundUser = verifyUserOwner(userId, request.userId());
        foundUser.resetPassword(request.password());
        log.info("UPDATE : password");
    }

    @Transactional(readOnly = true)
    public User verifyUserOwner(Long ownerId, Long userId) {
        log.info("사용자 소유 검사");
        if (!ownerId.equals(userId)) throw new IllegalArgumentException("해당 계정의 소유주가 아닙니다.");
        return findById(ownerId);
    }
}
