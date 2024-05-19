package com.example.blog.domain.like.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeId {
    private UUID userId;
    private UUID postId;
}
