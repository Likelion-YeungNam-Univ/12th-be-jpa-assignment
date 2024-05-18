package com.example.blog.domain.useralarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk, 중간테이블이라 임시로 설정
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "alarm_id", nullable = false)
    private Long alarmId;

    @Column(name = "read_status", nullable = false)
    @ColumnDefault("false")
    private boolean readStatus;

    @Builder
    public UserAlarm(Long userId, Long alarmId, Boolean readStatus) {
        this.userId = userId;
        this.alarmId = alarmId;
        this.readStatus=readStatus;
    }

    //읽음 상태
    public void readNotification() {
        if (!this.readStatus) {
            this.readStatus=true;
        }
    }
}
