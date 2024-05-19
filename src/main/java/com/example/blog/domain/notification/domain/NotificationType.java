package com.example.blog.domain.notification.domain;


import lombok.Getter;

@Getter
public enum NotificationType {
    NEW_POST("태그 게시글 알림", "태그가 포함된 새로운 게시글이 생성되었습니다."),
    EDIT_POST("좋아요 게시글 알림", "좋아요한 게시글이 수정되었습니다."),
    DELETE_POST("좋아요 게시글 알림", "좋아요한 게시글이 삭제되었습니다."),
    NEW_COMMENT("댓글 알림", "게시글에 새 댓글이 생성되었습니다.");

    private final String title;
    private final String content;

    NotificationType(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
