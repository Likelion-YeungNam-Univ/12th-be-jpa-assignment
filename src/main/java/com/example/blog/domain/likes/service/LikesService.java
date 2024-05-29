package com.example.blog.domain.likes.service;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.likes.dto.LikeRequest;
import com.example.blog.domain.likes.repository.LikesRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserService userService;
    private final CommentService commentService;

    @Transactional(readOnly = true)
    public Likes findByUserAndComment(User user, Comment comment){
        return likesRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 존재하지 않습니다."));
    }

    @Transactional
    public Likes create(LikeRequest request){
        log.info("좋아요 생성");
        User user = userService.findById(request.userId());
        Comment comment = commentService.findById(request.commentId());
        log.info("UPDATE : Comment");
        comment.increaseLike();
        Likes likes = request.toEntity(user, comment);
        log.info("INSERT : Like");
        return likesRepository.save(likes);
    }

    @Transactional
    public void delete(LikeRequest request){
        log.info("좋아요 삭제");
        User user = userService.findById(request.userId());
        Comment comment = commentService.findById(request.commentId());
        Likes foundLikes = findByUserAndComment(user, comment);
        log.info("UPDATE : Comment");
        comment.decreaseLike();
        likesRepository.delete(foundLikes);
        log.info("DELETE : Like");
    }
}
