package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /* 댓글 생성. */
    @Transactional
    public CommentRes save(Long userId, Long postId, CommentReq commentReq) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));


        Comment comment = commentReq.toCommentEntity();
        comment.setUser(user);
        comment.setPost(post);

        return new CommentRes(commentRepository.save(comment));
    }

    /* 게시글 전체 댓글 찾기. */
    @Transactional(readOnly = true)
    public List<CommentRes> findAll(Long postId) {

        List<Comment> comments = commentRepository.findByPost_Id(postId);
        return comments.stream().map(CommentRes::new).collect(Collectors.toList());
    }


    /* 댓글 업데이트. */
    @Transactional
    public CommentRes update(Long commentId, CommentReq commentReq) {  // update

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        comment.updateContent(commentReq.getContent());

        return new CommentRes(comment);
    }

    /* 댓글 삭제. */
    @Transactional
    public void delete(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        commentRepository.delete(comment);
    }
}
