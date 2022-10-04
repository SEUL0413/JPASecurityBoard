package com.seul.security.jpa.dto;

import com.seul.security.jpa.domain.Member;
import com.seul.security.jpa.domain.Posts;
import lombok.*;

import java.time.LocalDate;

public class PostsDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;
        private String title;
        private String writer;
        private String content;
        private LocalDate createdDate, modifiedDate;
        private int view;
        private Member member;

        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .count(0)
                    .member(member)
                    .build();
            return posts;
        }
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final LocalDate createdDate;
        private final LocalDate modifiedDate;
        private final int count;
        private final Long userId;

        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.count = posts.getCount();
            this.userId = posts.getMember().getId();
        }
    }
}
