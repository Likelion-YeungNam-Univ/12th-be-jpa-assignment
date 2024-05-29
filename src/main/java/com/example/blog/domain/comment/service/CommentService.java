package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public Comment findById(Long id){
        log.info("SELECT : Comment include proxy");
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
    }

    @Transactional
    public Comment create(Long postId, CommentRequest request){
        log.info("댓글 생성");
        User user = userService.findById(request.userId());
        Post post = postService.findByPostId(postId);
        Comment createComment = request.toEntity(user, post);
        createComment.setUser(user);
        createComment.setPost(post);
        log.info("INSERT : Comment");
        return commentRepository.save(createComment);
    }

    @Transactional
    public void update(Long id, CommentRequest request){
        log.info("댓글 수정");
        Comment foundComment = verifyCommentAuthor(id, request.userId());
        foundComment.update(request.content());
        log.info("UPDATE : content");
    }

    @Transactional
    public void delete(Long id, CommentRequest request){
        log.info("댓글 삭제");
        Comment foundComment = verifyCommentAuthor(id, request.userId());
        commentRepository.delete(foundComment);
        log.info("DELETE : Comment");
    }

    @Transactional(readOnly = true)
    public Comment verifyCommentAuthor(Long commentId, Long userId){
        log.info("댓글 작성자 검사");
        Comment comment = findById(commentId);
        if(!comment.getUser().getId().equals(userId))
            throw new IllegalArgumentException("해당 댓글의 작성자가 아닙니다.");
        return comment;
    }
}
