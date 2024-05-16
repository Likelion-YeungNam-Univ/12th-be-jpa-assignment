package com.example.blog.domain.file.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "storageUrl", nullable = false, length = 100)
    private String storageUrl;

    @Builder
    public File(String name, String storageUrl) {
        this.name = name;
        this.storageUrl = storageUrl;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

}
