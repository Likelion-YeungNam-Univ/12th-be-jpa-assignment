package com.example.blog.domain;


import lombok.Getter;

@Getter
public enum NotificationType {
    NEW_POST("키워드 알림", "키워드 게시글이 생성되었습니다."),
    EDIT_POST("좋아요 알림", "좋아요한 게시글이 수정되었습니다."),
    DELETE_POST("좋아요 알림", "좋아요한 게시글이 삭제되었습니다."),
    NEW_COMMENT("댓글 알림", "게시글에 새 댓글이 생성되었습니다.");

    private String title;
    private String content;

    NotificationType(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
