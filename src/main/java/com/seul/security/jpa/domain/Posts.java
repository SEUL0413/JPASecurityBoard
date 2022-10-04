package com.seul.security.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Posts  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String content;

    private String writer;

    private int count;

    private LocalDate createdDate;

    private LocalDate modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
    private Member member;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @PrePersist
    public void onPrePersist(){
        this.createdDate = LocalDate.now();
        this.modifiedDate = this.createdDate;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.modifiedDate = LocalDate.now();
    }
}
