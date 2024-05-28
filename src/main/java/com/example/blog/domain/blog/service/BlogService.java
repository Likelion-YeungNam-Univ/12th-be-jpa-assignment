package com.example.blog.domain.blog.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public void createBlog(Blog blog) {
        User user = userRepository.findByNickname(blog.getName())
                .orElseThrow(() -> new IllegalStateException("user is not found."));
        blog.setUser(user);
        blogRepository.save(blog);
    }
}