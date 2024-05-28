package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserRequest;
import com.example.blog.domain.user.dto.UserResponse;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 모든 유저를 얻어온다.
     */
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 유저를 검색하여 얻어온다.
     * @param userId 유저아이디
     */
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        return UserResponse.fromEntity(user);
    }

    /**
     * 유저를 생성한다.
     * @param request 유저dto
     */
    public User createUser(UserRequest request) {
        User user = request.toEntity();
        return userRepository.save(user);
    }

    /**
     * 비밀번호를 변경한다.
     * @param userId 유저아이디
     * @param request 유저dto
     * */
    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        user.updatePwd(request.password());
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

