package com.example.blog.useralarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk
    @Column(name = "userid", nullable = false)
    private Long userId;
    @Column(name = "alarmid", nullable = false)
    private Long alarmId;

    @Column(name = "read_status", nullable = false)
    private boolean readStatus;

    @Builder
    public UserAlarm(Long userId, Long alarmId) {
        this.userId = userId;
        this.alarmId = alarmId;
        this.readStatus=false;
    }

    //읽음 상태
    public void readNotification() {
        if (!this.readStatus) {
            this.readStatus=true;
        }
    }
}
