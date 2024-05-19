package com.example.blog.domain.useralarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk, 중간테이블이라 임시로 설정
    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "alarm_id")
    private Long alarmId;

    @NotNull
    @Column(name = "read_status")
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