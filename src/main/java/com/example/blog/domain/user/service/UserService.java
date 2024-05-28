package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserRes::fromEntity)
                .collect(Collectors.toList());
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
    }

    public User createUser(UserReq request) {
        User user = request.toEntity();

        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserReq request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        // 여기는 save 안해주나요??
        user.update(request.username(), request.password());

        return user;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
