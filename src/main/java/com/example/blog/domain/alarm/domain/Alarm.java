package com.example.blog.domain.alarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarmid")
    private Long alarmId;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "alarmtype")
    private String alarmType;

    @NotNull
    @Column(name = "alarm_date")
    private LocalDateTime alarmDate;

    @Builder

    public Alarm(String content, String alarmType) {
        this.content = content;
        this.alarmType = alarmType;
        this.alarmDate = LocalDateTime.now();
    }

    // 좋아요 알림
    public static Alarm likeAlarm(String likerName, String likedItem) {
        String content = likerName+"님이 "+likedItem+"의 게시물을 좋아합니다.";
        return new Alarm(content, "like");
    }
    // 팔로우 알림
    public static Alarm followAlarm(String followerName, String followingName) {
        String content = followerName+"님이 "+followingName+"님을 팔로우합니다.";
        return new Alarm(content, "follow");
    }
    // 댓글 알림
    public static Alarm commentAlarm(String commenterName, String commentedItem) {
        String content = commenterName+"님이 "+commentedItem+"에 댓글을 남겼습니다.";
        return new Alarm(content, "comment");
    }
    // 답글 알림
    public static Alarm replyAlarm(String replierName, String replyItem) {
        String content = replierName+"님이 "+replyItem+"에 댓글을 남겼습니다.";
        return new Alarm(content, "comment");
    }
    // 공지 알림
    public static Alarm noticeAlarm(String noticeContent) {
        return new Alarm(noticeContent, "notice");
    }
}