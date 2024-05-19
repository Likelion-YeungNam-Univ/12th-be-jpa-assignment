package com.example.blog.domain.file.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(name = "name", length = 30)
    @NotNull
    private String name;

    @Column(name = "storageUrl", length = 100)
    @NotNull
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
