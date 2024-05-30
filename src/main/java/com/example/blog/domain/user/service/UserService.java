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

    //패스워드 변경 메소드
    public User updatePassword(Long userId, UserReq request){
        String newPassword = request.password();
        checkPassword(newPassword);
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        user.update(user.getUsername(),newPassword);
        return userRepository.save(user);
    }

    //비밀번호 검증
    public void checkPassword(String password){
        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자리");
        }
    }
}
