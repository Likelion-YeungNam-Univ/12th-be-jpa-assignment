package com.example.blog.domain.like.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {
    @EmbeddedId
    private LikeId likeId;

    @Column(name="like_count", nullable = false)
    private int likeCount;
}
