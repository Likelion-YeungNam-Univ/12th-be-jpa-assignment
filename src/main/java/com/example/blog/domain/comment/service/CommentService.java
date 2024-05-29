package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    /**
     * 댓글을 생성한다.
     * @param postId 게시글아이디
     * @param commentRequest 댓글 dto
     */
    public CommentResponse create(Long postId, CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 작성 불가 : 유저 접근오류"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 작성 불가 : 게시글 접근오류"));
        Comment comment = commentRequest.toEntity();
        comment.addAssociate(user, post);
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }

    /**
     * 댓글을 전부 얻어온다.
     * @param postId 게시글아이디
     */
    public List<CommentResponse> getAll(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 접근 오류"));

        return comments.stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 댓글을 하나 얻어온다.
     * @param postId 게시글아이디
     * @param commentId 댓글아이디
     */
    public CommentResponse get(Long postId, Long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));
        return CommentResponse.fromEntity(comment);
    }

    /**
     * 댓글을 수정한다.
     * @param postId 게시글아이디
     * @param commentId 댓글아이디
     */
    public CommentResponse update(Long postId, Long commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));
        comment.update(comment.getContent());
        return CommentResponse.fromEntity(comment);
    }

    /**
     * 댓글을 삭제한다.
     * @param postId 게시글아이디
     * @param commentId 댓글아이디
     */
    public void delete(Long postId, Long commentId) {
        commentRepository.deleteByPostIdAndId(postId, commentId);
    }
}
