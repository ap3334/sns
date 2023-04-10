package com.example.fastcampusmysql.member.entity;

import com.example.fastcampusmysql.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {

    private Long id;

    private String nickname;

    private String email;

    private LocalDateTime birthDay;

    private LocalDateTime createdAt;

    private final static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickname, String email, LocalDateTime birthDay, LocalDateTime createdAt) {

        this.id = id;

        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.email = Objects.requireNonNull(email);
        this.birthDay = Objects.requireNonNull(birthDay);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 글자수를 초과하였습니다.");
    }

    public void changeNickname(String nickname) {
        Objects.requireNonNull(nickname);
        validateNickname(nickname);

        this.nickname = nickname;
    }

    public MemberDto toDto() {

        return new MemberDto(id, email, nickname, birthDay);
    }

}
