package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.PasswordDto;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.handler.exceptionHandler.error.ErrorCode;
import com.example.blog.handler.exceptionHandler.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserRes::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserRes getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserRes(user);
    }

    @Transactional
    public User createUser(UserReq request) {
        User user = request.toUserEntity();

        return userRepository.save(user);
    }

    @Transactional
    public UserRes updateUser(Long userId, UserReq request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 여기는 save 안해주나요??
        user.update(request.getUsername(), request.getPassword());

        return new UserRes(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // 사용자 비밀번호 재설정
    @Transactional
    public UserRes resetUserPassword(Long userId, PasswordDto passwordDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updatePassword(passwordDto.getPassword());

        return new UserRes(user);
    }
}