package com.example.blog.domain.likes.service;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.likes.dto.LikeRequest;
import com.example.blog.domain.likes.repository.LikesRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserService userService;
    private final CommentService commentService;

    @Transactional
    public Likes create(LikeRequest request){
        User user = userService.findById(request.userId());
        Comment comment = commentService.findById(request.commentId());
        comment.increaseLike();
        Likes likes = request.toEntity(user, comment);
        return likesRepository.save(likes);
    }
}
