package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.PasswordDto;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserRes::new)
                .collect(Collectors.toList());
    }

    public UserRes getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        return new UserRes(user);
    }

    public User createUser(UserReq request) {
        User user = request.toUserEntity();

        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserReq request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        // 여기는 save 안해주나요??
        user.update(request.getUsername(), request.getPassword());

        return user;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // 사용자 비밀번호 재설정
    public User resetUserPassword(Long userId, PasswordDto passwordDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        user.updatePassword(passwordDto.getPassword());

        return user;
    }
}