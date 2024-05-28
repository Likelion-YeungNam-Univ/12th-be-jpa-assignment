package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    // 중복 확인 메서드 (이메일)
    private void validateDuplicateEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("already exist email");
                });
    }

    // 중복 확인 메서드 (닉네임)
    private void validateDuplicateNickname(String nickname) {
        userRepository.findByNickname(nickname)
                .ifPresent(m -> {
                    throw new IllegalStateException("already exist nickname");
                });
    }

    // 유저 생성
    public void userSignUp(User user) {
        // 중복 확인
        validateDuplicateEmail(user.getEmail());
        validateDuplicateNickname(user.getNickname());

        userRepository.save(user);
    }

    // 비밀번호 변경
    public void changePassword(String nickname, String newPassword) {
        Optional<User> checkUser = userRepository.findByNickname(nickname);
        if (checkUser.isEmpty()) throw new IllegalStateException("No user exists.");

        User user = checkUser.get();
        if (user.getPassword().equals(newPassword))
            throw new IllegalStateException("The same password is not available.");

        user.updatePassword(newPassword);
    }

}
