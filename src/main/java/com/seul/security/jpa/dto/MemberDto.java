package com.seul.security.jpa.dto;

import com.seul.security.jpa.domain.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;

        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String username;

        private String password;

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;

        public Member toEntity(){
            Member member = Member.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();

            return member;
        }
    }

    @Getter
    public static class Response implements Serializable{
        private final Long id;
        private final String username;
        private final String email;

        public Response(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.email = member.getEmail();
        }
    }
}
